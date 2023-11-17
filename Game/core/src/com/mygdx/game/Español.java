package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Español implements IdiomaStrategy{
	private String dificultad;
	

	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, float cameraW) {
		font.draw(batch, "Puntaje : " + puntaje, 5, 475);
		font.draw(batch, "Vidas : " + vidas, 670, 475);
		font.draw(batch, "Puntuación Máxima : " + highscore, cameraW, 475);
		font.draw(batch, "Modo : " + dificultad, 670, 30);
	}
	
	public void idiomaTiempoPower(SpriteBatch batch, BitmapFont font, float seg) {
		//String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
        font.draw(batch, String.format("Tiempo restante power up: %.2fs",seg), 10, 30);
	}

	public void setDificultad(int num) {
		switch(num) {
		
			case 1:
				dificultad = "facil";
				break;
				
			case 2:
				dificultad = "medio";
				break;
				
			case 3:
				dificultad = "dificil";
				break;
		}
	}
	
	public void botonesMenu(TextButton bienvenida, TextButton tutorial, TextButton niveles, TextButton b1, TextButton b2, TextButton b3, TextButton b4, TextButton exitButton) {
		bienvenida.setText("Bienvenido a GameLluvia!!!");
		tutorial.setText("Tutorial");
		niveles.setText("Niveles");
		b1.setText("Facil");
	    b2.setText("Medio");
	    b3.setText("Dificil");   
	    b4.setText("Idiomas");
	    exitButton.setText("Salir");
	}
}