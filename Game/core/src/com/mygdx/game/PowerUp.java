package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PowerUp {
	
	void aplicarPowerUp(Tarro tarro, Lluvia lluvia);
	
	void quitarPowerUp(Tarro tarro, Lluvia lluvia);
	
	void dibujar(SpriteBatch batch, float x, float y);

	void destruir();	
}