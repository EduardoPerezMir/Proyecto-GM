package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class GotaMala extends Gota {
	public GotaMala() {
		// Se asigna la textura de la gota mala desde los assets.
		setTextura(new Texture(Gdx.files.internal("dropBad.png")));
	}
	
	// Implementación concreta del método abstracto.
	public int accionColisionTarro(Tarro tarro) {
		tarro.dañar();
		if (tarro.getVidas() <= 0)
			return -1;  // Si el tarro se queda sin vidas retorna falso / Game Over.
		return 1;
	}
}
