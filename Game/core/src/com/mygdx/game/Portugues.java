package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Portugues implements IdiomaStrategy{
	private String dificultad;
	

	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, float cameraW) {
		font.draw(batch, "Pontuação : " + puntaje, 5, 475);
		font.draw(batch, "Vidas : " + vidas, 670, 475);
		font.draw(batch, "Pontuação máxima : " + highscore, cameraW, 475);
		font.draw(batch, "Modo : " + dificultad, 670, 30);
	}
	
	public void idiomaTiempoPower(SpriteBatch batch, BitmapFont font, float seg) {
		//String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
        font.draw(batch, String.format("Tempo restante de ativação: %.2fs",seg), 10, 30);
	}

	public void setDificultad(int num) {
		switch(num) {
		
			case 1:
				dificultad = "fácil";
				break;
				
			case 2:
				dificultad = "metade";
				break;
				
			case 3:
				dificultad = "difícil";
				break;
		}
	}
	
	public void botonesMenu(TextButton bienvenida, TextButton tutorial, TextButton niveles, TextButton b1, TextButton b2, TextButton b3, TextButton b4, TextButton exitButton) {
		bienvenida.setText("Bem vindo ao GameLluvia!!!");
		tutorial.setText("Tutorial");
		niveles.setText("Níveis");
		b1.setText("fácil");
	    b2.setText("metade");
	    b3.setText("difícil");   
	    b4.setText("línguas");
	    exitButton.setText("Sair");
	}
	
	public void gameOverMensaje(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, "FIM DE JOGO ", 350, 300);
		font.draw(batch, "Toque em qualquer lugar para reiniciar.", 100, 200);
		font.draw(batch, "Pressione ESC para retornar ao menu inicial.", 100, 100);
	}

	public Texture setTutorial() {
		return new Texture(Gdx.files.internal("tutorialPortugues.png"));	
	}
	
	public Texture setPausa() {
		return new Texture(Gdx.files.internal("pausaPortugues.png"));	
	}
}