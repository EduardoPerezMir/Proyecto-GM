package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TutorialScreen implements Screen {
	final GameLluviaMenu game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Stage stage;
    private Texture backgroundImage;
    
    private IdiomaStrategy idioma;
    
    
    public TutorialScreen(final GameLluviaMenu game, IdiomaStrategy idioma) {
        this.game = game;
        this.batch = game.getBatch2();
        this.idioma = idioma;
        setupCamera();
        createStage();
        setupBackground();
    }
    
    
    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.zoom = 1.0f;
    }

    private void createStage() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    // La imagen de fondo del tutorial fue realizada a través de prueba y error, hasta lograr
    // que calzara y que se viera la explicación del juego visualmente legible.
    private void setupBackground() {
        backgroundImage = new Texture(Gdx.files.internal("backgroundTutorial.png"));
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
        // Limpiar la pantalla
		
		ScreenUtils.clear(0, 0, 0, 1);
		
        // Texto explicativo del juego (Tutorial)
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        
        // La siguiente condición se encarga de verificar si se presionó la tecla Esc, en caso de
        // ser verdadero, se vuelve al menú inicial (se designa la pantalla a ver).
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	        // Cuando se presiona ESC, cambia a la pantalla del menú principal
	        game.setScreen(new MainMenuScreen(game,idioma)); // Reemplaza "MainMenuScreen" con el nombre de tu pantalla principal
	        dispose(); // Limpia los recursos de la pantalla actual si es necesario
	    }
        
        dispose();
		
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
