package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		  this.game = game;
	      this.batch = game.getBatch();
	      this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  Texture tarroI = new Texture(Gdx.files.internal("bucket.png"));
		  Texture tarroGrande = new Texture(Gdx.files.internal("bucketgrande.png"));
		  tarro = new Tarro(tarroI,hurtSound,tarroGrande);
		 
		  // load the drop sound effect and the rain background "music" 
		 Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		 lluvia = new Lluvia(rainMusic);
		 
		 //PW
		 Texture inmortabilidad = new Texture(Gdx.files.internal("inmortabilidad.png"));
		 
		 Texture velocidad = new Texture(Gdx.files.internal("velocidad.png"));
		 Texture duplicarPuntos = new Texture(Gdx.files.internal("duplicarpuntos.png"));
		 Texture aumentarTarro = new Texture(Gdx.files.internal("tamañotarro.png"));
		 Texture aumentarVelocidad = new Texture(Gdx.files.internal("velocidadlluvia.png"));
		 
		 Sound inicioPower = Gdx.audio.newSound(Gdx.files.internal("soundinmortal.mp3"));
		 Sound finPower = Gdx.audio.newSound(Gdx.files.internal("endpower.mp3"));
		 
		 powerUps = new PowerUpManager(inmortabilidad,inicioPower,finPower,velocidad,duplicarPuntos,aumentarTarro,aumentarVelocidad);
		 
		  // camera
		  camera = new OrthographicCamera();
		  camera.setToOrtho(false, 800, 480);
		  batch = new SpriteBatch();
		  // creacion del tarro
		  tarro.crear();
		  
		  powerUps.crear();
		  
		  // creacion de la lluvia
		  lluvia.crear();
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
	        
	        //Caida de PW
	        powerUps.actualizarMovimiento(tarro,lluvia);
	        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(tarro)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		powerUps.actualizarDibujo(batch);
		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();
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
      tarro.destruir();
      lluvia.destruir();
      powerUps.destruir();

	}

}
