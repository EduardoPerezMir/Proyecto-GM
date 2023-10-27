package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Stage stage;
    private Texture backgroundImage;
    private Skin basicSkinBotones;
    private Skin basicSkinTitulos;
    private Skin basicSkinTutorial;
    private TextButton bienvenida;
    private TextButton tutorial;
    private TextButton niveles;
    private TextButton optionButton1;
    private TextButton optionButton2;
    private TextButton optionButton3;
    private TextButton exitButton;
    private Table table;

    public MainMenuScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        setupCamera();
        createStage();
        setupBackground();
        createTableAndButtons();
    }

    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void createStage() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    private void setupBackground() {
        backgroundImage = new Texture(Gdx.files.internal("lluvia2.jpg"));
    }
    
    private void createTableAndButtons() {
        table = new Table();
        basicSkinBotones = createBasicSkin(1);
        basicSkinTitulos = createBasicSkin(2);
        basicSkinTutorial = createBasicSkin(3);
        bienvenida = new TextButton("Bienvenido a GameLluvia!!!", basicSkinTitulos);
        tutorial = new TextButton("Tutorial", basicSkinTutorial);
        niveles = new TextButton("Niveles", basicSkinTitulos);
        optionButton1 = new TextButton("Fácil", basicSkinBotones);
        optionButton2 = new TextButton("Medio", basicSkinBotones);
        optionButton3 = new TextButton("Difícil", basicSkinBotones);
        exitButton = new TextButton("Salir", basicSkinBotones);
        table.setFillParent(true);
        table.center();
        table.add(bienvenida).pad(10);
        table.row();
        table.add(tutorial).pad(10);
        table.row();
        table.add(niveles).pad(10);
        table.row();
        table.add(optionButton1).pad(10);
        table.row();
        table.add(optionButton2).pad(10);
        table.row();
        table.add(optionButton3).pad(10);
        table.row();
        table.add(exitButton).pad(10);
        stage.addActor(table);
      
        
        tutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	game.setScreen(new TutorialScreen(game)); 
    	        dispose();
            }
        });
        
        optionButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    			game.setScreen(new GameScreen(game, 1));
    			dispose();
            }
        });
        
        optionButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    			game.setScreen(new GameScreen(game, 2));
    			dispose();
            }
        });
        
        optionButton3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    			game.setScreen(new GameScreen(game, 3));
    			dispose();
            }
        });
        
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	dispose();
            	Gdx.app.exit();
            }
        });       
    }
    
	
	 private Skin createBasicSkin(int indicador) {
	        Skin skin = new Skin();
	        String nombreArchivo = "degrade.jpg";
	        if (indicador == 2)
		        nombreArchivo = "faded.jpg";	
	        if (indicador == 3)
	        	nombreArchivo = "colorTutorial.jpg";

	        // Configurar el estilo de botón
	        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
	        buttonStyle.font = font; // Usar la fuente que ya tienes
	        buttonStyle.up =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.down =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.over =  new TextureRegionDrawable(new Texture(Gdx.files.internal(nombreArchivo)));
	        buttonStyle.fontColor = Color.WHITE;
	        
	        if (indicador == 1 || indicador == 3) {
		        Texture backgroundTexture = new Texture(Gdx.files.internal(nombreArchivo));
		        skin.add("background", backgroundTexture);
		        buttonStyle.up = skin.newDrawable("background");
		        buttonStyle.over = skin.newDrawable("background", Color.GRAY);
	        }

	        // Agregar el estilo al skin
	        skin.add("default", buttonStyle);
	        
	        return skin;
	 }
	 
	 @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Dibujar el fondo
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Actuar y dibujar el Stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
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
		stage.dispose();
		// TODO Auto-generated method stub
		
	}

}
