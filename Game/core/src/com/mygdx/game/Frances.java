package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Frances implements IdiomaStrategy{
	
	private String dificultad;
	
	
	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, float cameraW) {
		font.draw(batch, "Score : " + puntaje, 5, 475);
		font.draw(batch, "Vies : " + vidas, 670, 475);
		font.draw(batch, "Note Maximale : " + highscore, 200, 475);
		font.draw(batch, "Mode : " + dificultad, 670, 30);
	}
	
	public void idiomaTiempoPower(SpriteBatch batch, BitmapFont font, float seg) {
		//String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
        font.draw(batch, String.format("Temps de mise sous tension restant: %.2fs",seg), 10, 30);
	}
	
	public void setDificultad(int num) {
		switch(num) {
			case 1:
				dificultad = "facile";
				break;
				
			case 2:
				dificultad = "Milieu";
				break;
				
			case 3:
				dificultad = "difficile";
				break;
		}
	}
	
	public void botonesMenu(TextButton bienvenida, TextButton tutorial, TextButton niveles, TextButton b1, TextButton b2, TextButton b3, TextButton b4, TextButton exitButton) {
		bienvenida.setText("Bienvenue sur GameLluvia!!!");
		tutorial.setText("Didacticiel");
		niveles.setText("Les niveaux");
		b1.setText("Facile");
	    b2.setText("Milieu");
	    b3.setText("difficile");  
	    b4.setText("langues");
	    exitButton.setText("sortir");
	}
	
	public void gameOverMensaje(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, "JEU TERMINÉ ", 350, 300);
		font.draw(batch, "Appuyez n'importe où pour redémarrer.", 100, 200);
		font.draw(batch, "Appuyez sur ESC pour revenir au menu initial.", 100, 100);
	}
	
	public Texture setTutorial() {
		return new Texture(Gdx.files.internal("tutorialFrances.png"));	
	}
	
	public Texture setPausa() {
		return new Texture(Gdx.files.internal("pausaFrances.png"));	
	}

}