package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Español implements IdiomaStrategy{
	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, String dificultad, float cameraW) {
		font.draw(batch, "Puntaje : " + puntaje, 5, 475);
		font.draw(batch, "Vidas : " + vidas, 670, 475);
		font.draw(batch, "Puntuación Máxima : " + highscore, cameraW, 475);
		font.draw(batch, "Modo : " + dificultad, 670, 30);
	}
}