package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Tarro tarro;
	private NivelDificultad nivel;
	private PowerUpManager powerUps;
	private int dificultad;
	private String dificultadString;
	private Texture backgroundTexture;
	private Texture sonidoTexture;
	private Sprite sonidoSprite;
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game, int dificultad) {
		  this.game = game;
	      this.batch = game.getBatch();
	      this.font = game.getFont();
	      this.dificultad = dificultad;
	      dificultadString = "";
	      
	      backgroundTexture = new Texture(Gdx.files.internal("fondoDefault.jpg"));
	      
	      if (dificultad == 1)
	      {
	    	  nivel = new NivelFacil();
	    	  dificultadString = "Fácil";
	      }
	      if (dificultad == 2)
	      {
	    	  nivel = new NivelMedio();
	    	  dificultadString = "Medio";
	      }
		  if (dificultad == 3)
		  {
			  nivel = new NivelDificil();
			  dificultadString = "Difícil";
		  }
		  
		  // textura sonido
		  sonidoTexture = new Texture(Gdx.files.internal("sonido.png"));
		  // sprite sonido
	      sonidoSprite = new Sprite(sonidoTexture);
		  // posiciono sprite sonido
	      //sonidoSprite.setPosition(150,440);
	      sonidoSprite.setPosition(0,0);
	      
		  tarro = new Tarro();
		 
		  // load the drop sound effect and the rain background "music" 
		 
		 //PW
		 powerUps = new PowerUpManager();
		 
		  // camera
		  camera = new OrthographicCamera();
		  camera.setToOrtho(false, 800, 480);
		  batch = new SpriteBatch();
		  // creacion del tarro
		  tarro.crear();
		  
		  //creacion de powers
		  powerUps.crear();
		  
		  // creacion de la lluvia
		  nivel.crear();
		  
		  Texture texturaX = nivel.getBackgroundTexture();
		  
		  if (texturaX != null)
			  backgroundTexture = texturaX;
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		//actualizar matrices de la cámara
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//dibujar textos
		font.draw(batch, "Puntaje: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		String mensajeDificultad = "Modo: " + dificultadString;
		font.draw(batch, mensajeDificultad, 670, 30);
		sonidoSprite.draw(batch);
		
		/*Comprobar si el clic está dentro de la textura
		if (Gdx.input.isTouched()) {
		    float touchX = Gdx.input.getX();
		    float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
		            if(!Intersector.overlaps(sonidoSprite.getBoundingRectangle(), new Rectangle(touchX, touchY, 1, 1))) {
		            	if(lluvia.hayMusica())
		            		lluvia.pausar();
		            	else
		            		lluvia.continuar();
		            }
		}*/ 

		if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
		       if(nivel.hayMusica())
		    	   nivel.pausar();
		        else
		        	nivel.continuar();
		}
		
        // La siguiente condición se encarga de verificar si se presionó la tecla P, en caso de
        // ser verdadero, se pausa el juego.
		if (Gdx.input.isKeyJustPressed(Keys.P)) {
			pause();
	    }	
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
	        
	        //Caida de PW
	        powerUps.actualizarMovimiento(tarro, nivel);
	        
			// caida de la lluvia 
	       if (!nivel.actualizarMovimiento(tarro)) {
	    	  game.setScreen(new GameOverScreen(game, dificultad));
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore() < tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  dispose();
	       }
		}
		powerUps.actualizarDibujo(batch);
		tarro.dibujar(batch);
		nivel.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
		nivel.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		nivel.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	  backgroundTexture.dispose();
      nivel.destruir();
      powerUps.destruir();
      tarro.destruir(); 
	}

}
