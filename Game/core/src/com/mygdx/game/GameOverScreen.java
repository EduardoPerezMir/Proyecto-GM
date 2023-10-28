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
	
	public GameOverScreen(final GameLluviaMenu game, int dificultad) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.dificultad = dificultad;
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		font.draw(batch, "GAME OVER ", 350, 300);
		font.draw(batch, "Toca en cualquier lado para reiniciar.", 100, 200);
		font.draw(batch, "Presione ESC para volver al menú inicial.", 100, 100);
		batch.end();

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	        // Cuando se presiona ESC, cambia a la pantalla del menú principal
	        game.setScreen(new MainMenuScreen(game)); // Reemplaza "MainMenuScreen" con el nombre de tu pantalla principal
	        game.setHigherScore(0);
	        dispose(); // Limpia los recursos de la pantalla actual si es necesario
	    }		
		
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game, dificultad));
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
