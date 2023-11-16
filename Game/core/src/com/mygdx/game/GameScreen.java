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
	private IdiomaStrategy idioma;

	public GameScreen(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma) {
		  this.game = game;
	      this.batch = game.getBatch();
	      this.font = game.getFont();
	      this.dificultad = dificultad;
	      this.idioma = idioma;
	      dificultadString = "";
	      
	      
	      backgroundTexture = new Texture(Gdx.files.internal("fondoDefault.jpg"));
	      
		  if (dificultad == 1) {
			  dificultadString = "Fácil";
			  nivel = new NivelFacil(); 
		  }	
		  else
		  {
			  if (dificultad == 2) {
				  dificultadString = "Medio";
				  nivel = new NivelMedio();
			  }
			  else {
				  dificultadString = "Difícil";
				  nivel = new NivelDificil();
			  }
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
		idioma.idiomaGameScreen(batch,font,tarro.getPuntos(),tarro.getVidas(),game.getHigherScore(),dificultadString, camera.viewportWidth/2-50);

		sonidoSprite.draw(batch);

		// La siguiente condición se encarga de verificar si se presionó la tecla M, en caso de
        // ser verdadero, se pausa la musica de la lluvia.
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
		
		// movimiento del tarro desde teclado
		if (tarro.actualizarMovimiento()) {
	        //Caida de PW
	        powerUps.actualizarMovimiento(tarro, lluvia);
			// caida de la lluvia 
	        if (!lluvia.actualizarMovimiento(tarro)) {
	    	    game.setScreen(new GameOverScreen(game, dificultad,this));
	    	    //actualizar HigherScore
	    	    if (game.getHigherScore() < tarro.getPuntos())
	    		    game.setHigherScore(tarro.getPuntos());  
	    	    //ir a la ventana de finde juego y destruir la actual
	    	    lluvia.pausar();
	    	    //dispose();
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

	public void reset() {
		powerUps.reset(lluvia, tarro);
		lluvia.reset();
		tarro.reset();
		
	}

}
