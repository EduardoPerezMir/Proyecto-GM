package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TutorialScreen implements Screen {
	final GameLluviaMenu game;
	private SpriteBatch batch;
	private BitmapFont font;
	private BitmapFont font2;
	private BitmapFont lastFont;
	private OrthographicCamera camera;
	private Stage stage;
    private Texture backgroundImage;
    
    
    public TutorialScreen(final GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch2();
        this.font = game.getFont3(); // Color amarillo
        this.font2 = game.getFont2(); // Color magenta
        this.lastFont = game.getFont(); // color blanco
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
        backgroundImage = new Texture(Gdx.files.internal("backgroundTutorial.jpg"));
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
        int posYTexto = 680;
        posYTexto -= 20;
        font2.draw(batch, "Tutorial GameLluvia", 300, posYTexto); 
        posYTexto -= 20;
        font2.draw(batch, "Objetivo del Juego", 305, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Tu objetivo principal en GameLluvia es alcanzar la puntuación más alta posible.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Muestra tus habilidades estratégicas y reflejos para superar tus propios límites.", 100, posYTexto);
        posYTexto -= 20;
        font2.draw(batch, "Controles", 320, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Menú: En el menú del juego, puedes navegar y seleccionar opciones haciendo clic con el ratón.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Durante el Juego: Usa las teclas de flecha izquierda y derecha en tu teclado para", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "  mover la cubeta y recoger las gotas de lluvia. Además, pulsa la tecla m para silenciar o activar el sonido de fondo.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Game Over: Cuando llegues al Game Over, puedes hacer clic en cualquier parte de ", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "  la pantalla para seguir jugando en el mismo nivel o pulsar la tecla Esc para volver al menú inicial.", 100, posYTexto);
        posYTexto -= 20;
        font2.draw(batch, "Elementos del juego", 300, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Gotas Azules: Estas gotas otorgan 10 puntos estándar en el juego.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Gotas Amarillas: Las gotas amarillas otorgan una mayor puntuación, 30 puntos cada una.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Gotas Malas: Ten cuidado con las gotas malas, ya que restarán una vida.", 100, posYTexto);
        posYTexto -= 20;
        font2.draw(batch, "PowerUps", 320, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- PowerUp X2: El PowerUp X2 duplica el puntaje obtenido en cada gota acumulada durante el tiempo establecido.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- PowerUp Estrella: El PowerUp Estrella concede inmortildad al jugador durante el tiempo establecido.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- PowerUp Velocidad: El PowerUp Velocidad concede al jugador una mayor velocidad con el tarro en el tiempo establecido.", 100, posYTexto);
        posYTexto -= 20;
        font2.draw(batch, "PowerDowns", 315, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- PowerDown Tamaño: El PowerDown Tamaño aumenta de forma considerable el tamaño del tarro durante el tiempo establecido.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- PowerDown Velocidad: El PowerDown Velocidad aumenta de forma ", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "considerable la velocidad de la lluvia durante el tiempo establecido.", 100, posYTexto);
        posYTexto -= 20;
        font2.draw(batch, "Notas Importantes", 300, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Si decides volver al menú inicial desde la pantalla de Game Over,", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "  se eliminará el registro de tu puntuación más alta.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Al cerrar el juego, también se eliminará el registro de tu puntuación más alta.", 100, posYTexto);
        posYTexto -= 20;
        font.draw(batch, "- Al presionar la tecla m se silenciará el sonido de la lluvia.", 100, posYTexto);
        posYTexto -= 20;
        posYTexto -= 20;
        lastFont.draw(batch, "Presiona la tecla Esc para volver al menú inicial.", 100, posYTexto);
        
        batch.end();
        
        // La siguiente condición se encarga de verificar si se presionó la tecla Esc, en caso de
        // ser verdadero, se vuelve al menú inicial (se designa la pantalla a ver).
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	        // Cuando se presiona ESC, cambia a la pantalla del menú principal
	        game.setScreen(new MainMenuScreen(game)); // Reemplaza "MainMenuScreen" con el nombre de tu pantalla principal
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
