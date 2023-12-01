package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class PausaScreen implements Screen {

	private final GameLluviaMenu game;
	private NivelDificultad juego;
	private SpriteBatch batch;	   
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	private IdiomaStrategy idioma;

	public PausaScreen (final GameLluviaMenu game, NivelDificultad juego, IdiomaStrategy idioma) {
		this.game = game;
        this.juego = juego;
        this.batch = game.getBatch2();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.idioma = idioma;
		backgroundTexture = idioma.setPausa();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	        // Cuando se presiona ESC, cambia a la pantalla del menú principal
	        game.setScreen(new MainMenuScreen(game,idioma)); // Reemplaza "MainMenuScreen" con el nombre de tu pantalla principal
	        game.setHigherScore(0);
	        juego.dispose();
	        dispose(); // Limpia los recursos de la pantalla actual si es necesario
	    }		
		
		
		if (Gdx.input.isTouched()) {
			game.setScreen(juego);
			dispose();
		}
		batch.end();
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

