package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface PowerDown {
	
	public void aplicarPowerDown(Tarro tarro);
	
	public void quitarPowerDown(Tarro tarro);
	
	public void dibujar(SpriteBatch batch, float x, float y);

	public void destruir();	
}
