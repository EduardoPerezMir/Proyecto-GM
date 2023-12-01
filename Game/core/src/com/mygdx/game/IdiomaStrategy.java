package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public interface IdiomaStrategy {
	public void idiomaGameScreen(SpriteBatch batch, BitmapFont font, int puntaje, int vidas, int highscore, float cameraW);
	public void idiomaTiempoPower(SpriteBatch batch, BitmapFont font, float seg);
	public void setDificultad(int num);
	public void botonesMenu(TextButton bienvenida, TextButton tutorial, TextButton niveles, TextButton b1, TextButton b2, TextButton b3, TextButton b4, TextButton exitButton);
	public void gameOverMensaje(SpriteBatch batch, BitmapFont font);
	public Texture setTutorial();
	public Texture setPausa();
}

