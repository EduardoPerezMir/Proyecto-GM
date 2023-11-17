package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PowerUp {
	
	public Tarro aplicarPowerUp();
	
	public void quitarPowerUp(Tarro tarro, Lluvia lluvia);
	
	public void dibujar(SpriteBatch batch, float x, float y);

	public void destruir();	
}