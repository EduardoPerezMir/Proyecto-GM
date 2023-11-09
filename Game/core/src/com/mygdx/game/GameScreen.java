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
	private Lluvia lluvia;
	private PowerUpManager powerUps;
	private int dificultad;
	private String dificultadString;
	private Texture backgroundTexture;
	private Texture sonidoTexture;
	private Sprite sonidoSprite;
	private NivelDificultad nivel;

	public GameScreen(final GameLluviaMenu game, int dificultad) {
		  this.game = game;
	      this.batch = game.getBatch();
	      this.font = game.getFont();
	      this.dificultad = dificultad;
	      dificultadString = "";
	      
	      backgroundTexture = new Texture(Gdx.files.internal("fondoDefault.jpg"));
	      
	      if (dificultad == 1)
	    	  dificultadString = "Fácil";
	      if (dificultad == 2)
	    	  dificultadString = "Medio";
		  if (dificultad == 3)
			  dificultadString = "Difícil";
		  
		  if (dificultad == 1)
			  nivel = new NivelFacil();
		  else
		  {
			  if (dificultad == 2)
				  nivel = new NivelMedio();
			  else
				  nivel = new NivelDificil();
		  }
		  
		  
		  lluvia = Lluvia.getLluvia(nivel);
		  
		  
		  // textura sonido
		  sonidoTexture = new Texture(Gdx.files.internal("sonido.png"));
		  // sprite sonido
	      sonidoSprite = new Sprite(sonidoTexture);
		  // posiciono sprite sonido
	      //sonidoSprite.setPosition(150,440);
	      sonidoSprite.setPosition(0,0);
	      
		  tarro = Tarro.getTarro();
		 
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
		  lluvia.crear();
		  
		  nivel.setBackgroundTextureGame(this);
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
		       if(lluvia.hayMusica())
		    	   lluvia.pausar();
		        else
		        	lluvia.continuar();
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
	        powerUps.actualizarMovimiento(tarro, lluvia);
	        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(tarro)) {
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
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}

	public void setBackgroundTexture(Texture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
		lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	  backgroundTexture.dispose();
	  lluvia.destruir();
      powerUps.destruir();
      tarro.destruir(); 
	}

}
