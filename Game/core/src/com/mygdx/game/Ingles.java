package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Ingles implements IdiomaStrategy{
	
	private String dificultad;
	
	
	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, float cameraW) {
		font.draw(batch, "Score : " + puntaje, 5, 475);
		font.draw(batch, "Lifes : " + vidas, 670, 475);
		font.draw(batch, "HighScore : " + highscore, 200, 475);
		font.draw(batch, "Mode : " + dificultad, 670, 30);
	}
	
	public void idiomaTiempoPower(SpriteBatch batch, BitmapFont font, float seg) {
		//String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
        font.draw(batch, String.format("Remaining power up time: %.2fs",seg), 10, 30);
	}
	
	public void setDificultad(int num) {
		switch(num) {
			case 1:
				dificultad = "easy";
				break;
				
			case 2:
				dificultad = "midle";
				break;
				
			case 3:
				dificultad = "hard";
				break;
		}
	}
	
	public void botonesMenu(TextButton bienvenida, TextButton tutorial, TextButton niveles, TextButton b1, TextButton b2, TextButton b3, TextButton b4, TextButton exitButton) {
		bienvenida.setText("Welcome to GameLluvia!!!");
		tutorial.setText("Tutorial");
		niveles.setText("Levels");
		b1.setText("Easy");
	    b2.setText("Middle");
	    b3.setText("Hard");  
	    b4.setText("Languages");
	    exitButton.setText("Exit");
	}
	
	public void gameOverMensaje(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, "GAME OVER", 350, 300);
		font.draw(batch, "Tap anywhere to restart.", 100, 200);
		font.draw(batch, "Press ESC to return to the initial menu.", 100, 100);
	}
	
	public Texture setTutorial() {
		return new Texture(Gdx.files.internal("tutorialIngles.png"));	
	}
	
	public Texture setPausa() {
		return new Texture(Gdx.files.internal("pausaIngles.png"));	
	}

}