package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ingles implements IdiomaStrategy{
	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, String dificultad, float cameraW) {
		font.draw(batch, "Score : " + puntaje, 5, 475);
		font.draw(batch, "Lifes : " + vidas, 670, 475);
		font.draw(batch, "HighScore : " + highscore, 200, 475);
		font.draw(batch, "Mode : " + dificultad, 670, 30);
	}
}