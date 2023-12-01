package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class NivelDificultad implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Tarro tarro;
	private Lluvia lluvia;
	private Texture backgroundTexture;
	private Texture sonidoTexture;
	private Sprite sonidoSprite;
	
	private IdiomaStrategy idioma;
    public NivelDificultad(final GameLluviaMenu game,int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
    	this.game = game;
	    this.batch = game.getBatch();
	    this.font = game.getFont();
	    
	    this.idioma = idioma;
	    
	    idioma.setDificultad(dificultad);
		  
		lluvia = Lluvia.getLluvia(crear);

		  
		  // textura sonido
		sonidoTexture = new Texture(Gdx.files.internal("sonido.png"));
		  // sprite sonido
	    sonidoSprite = new Sprite(sonidoTexture);
		  // posiciono sprite sonido
	      //sonidoSprite.setPosition(150,440);
	    sonidoSprite.setPosition(0,0);
	      
		 
		  // load the drop sound effect and the rain background "music"
		 
		  // camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		// creacion del tarro
		tarro = crear.crearTarro();
		  
		inicializarFondo();
		inicializarVelocidadLluvia(lluvia);
		inicializarVelocidadTarro(tarro);

    }
    
	
	
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		
		//actualizar matrices de la cámara
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//dibujar textos
		idioma.idiomaGameScreen(batch,font,tarro.getPuntos(),tarro.getVidas(),game.getHigherScore(), camera.viewportWidth/2-50);

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
			// caida de la lluvia 
			
			if (tarro.estaMuerto()) {
				game.setScreen(new GameOverScreen(game,this,idioma));
	    	    //actualizar HigherScore
	    	    if (game.getHigherScore() < tarro.getPuntos())
	    		    game.setHigherScore(tarro.getPuntos());  
	    	    //ir a la ventana de finde juego y destruir la actual
	    	    lluvia.pausar();
			}
			lluvia.actualizarMovimiento(tarro);
			
		}
	        
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch,font,idioma);
		
		batch.end();
	}

	public abstract void inicializarFondo();
	public abstract void inicializarVelocidadLluvia(Lluvia lluvia) ;
	public abstract void inicializarVelocidadTarro(Tarro tarro);
	
	 public void setTarro(Tarro tarro) {
	    	this.tarro = tarro;
	  }
	
	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}

	public void setBackgroundTexture(Texture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}

	public void resize(int width, int height) {
	}

	public void show() {
	  // continuar con sonido de lluvia
		lluvia.continuar();
	}

	public void hide() {

	}

	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this, idioma)); 
	}

	public void resume() {

	}

	public void dispose() {
	  backgroundTexture.dispose();
	  lluvia.destruir();
      tarro.destruir(); 
	}

	public void reset() {
		lluvia.reset(tarro);
		tarro.reset();
		
	}
}