package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class IdiomaScreen implements Screen {
	final GameLluviaMenu game;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Stage stage;
    private Texture backgroundImage;
    
    private Skin basicSkinBotones;
    
    private TextButton optionButton1;
    private TextButton optionButton2;
    //private TextButton optionButton3;
    
    private BitmapFont font;
    
    private Table table;
    private IdiomaStrategy idioma;
    
    
    
    public IdiomaScreen(final GameLluviaMenu game, IdiomaStrategy idioma) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        this.idioma = idioma;
        setupCamera();
        createStage();
        setupBackground();
        createTableAndButtons();
    }
    
    private void createTableAndButtons() {
        table = new Table();
        
        basicSkinBotones = createBasicSkin();
        
        optionButton1 = new TextButton("Español", basicSkinBotones);
        optionButton2 = new TextButton("Ingles", basicSkinBotones);

        table.setFillParent(true);
        table.center();
        table.add(optionButton1).pad(10);
        table.row();
        table.add(optionButton2).pad(10);
        stage.addActor(table);
        
        
        optionButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	game.setScreen(new MainMenuScreen(game,new Español()));
    	        dispose();
            }
        }); 
        
        optionButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	game.setScreen(new MainMenuScreen(game,new Ingles()));
    			dispose();
            }
        });
        
    }
    
	 private Skin createBasicSkin() {
	        Skin skin = new Skin();
	        String nombreArchivo = "degrade.jpg";

	        // Configurar el estilo de botón
	        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
	        buttonStyle.font = font; // Usar la fuente que ya tienes
	        buttonStyle.up =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.down =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.over =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.fontColor = Color.WHITE;
	      
		    Texture backgroundTexture = new Texture(Gdx.files.internal(nombreArchivo));
		    skin.add("background", backgroundTexture);
		    buttonStyle.up = skin.newDrawable("background");
		    buttonStyle.over = skin.newDrawable("background", Color.GRAY);

	        // Agregar el estilo al skin
	        skin.add("default", buttonStyle);
	        
	        return skin;
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
        backgroundImage = new Texture(Gdx.files.internal("lluvia2.jpg"));
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
        
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        //dispose();
		
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
