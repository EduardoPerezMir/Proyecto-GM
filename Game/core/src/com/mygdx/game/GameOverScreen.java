package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

public class GameOverScreen implements Screen {
	private final GameLluviaMenu game;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
	private int dificultad;
	private GameScreen gameScreen;
	
	private IdiomaStrategy idioma;
	
	public GameOverScreen(final GameLluviaMenu game, int dificultad,GameScreen gameScreen, IdiomaStrategy idioma) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.dificultad = dificultad;
		this.gameScreen = gameScreen;
		this.idioma = idioma;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		idioma.gameOverMensaje(batch, font);
		batch.end();

        // La siguiente condición se encarga de verificar si se presionó la tecla Esc, en caso de
        // ser verdadero, se vuelve al menú inicial (se designa la pantalla a ver).
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	        // Cuando se presiona ESC, cambia a la pantalla del menú principal
	        game.setScreen(new MainMenuScreen(game,idioma)); // Reemplaza "MainMenuScreen" con el nombre de tu pantalla principal
	        game.setHigherScore(0);
	        gameScreen.dispose();
	        dispose(); // Limpia los recursos de la pantalla actual si es necesario
	    }		
		
		if (Gdx.input.isTouched()) {
			gameScreen.reset();
			game.setScreen(gameScreen);
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
