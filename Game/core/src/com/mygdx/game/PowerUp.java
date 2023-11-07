package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PowerUp {
	
	void aplicarPowerUp(Tarro tarro, NivelDificultad nivel);
	
	void quitarPowerUp(Tarro tarro, NivelDificultad nivel);
	
	void dibujar(SpriteBatch batch, float x, float y);

	void destruir();	
}