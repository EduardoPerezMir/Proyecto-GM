package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PowerUp {
	
	public void aplicarPowerUp(Tarro tarro);
	
	public void quitarPowerUp(Tarro tarro);
	
	public void dibujar(SpriteBatch batch, float x, float y);

	public void destruir();	
}