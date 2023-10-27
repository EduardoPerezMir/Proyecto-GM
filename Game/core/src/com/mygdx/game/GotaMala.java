package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class GotaMala extends Gota {
	public GotaMala() {
		super(0, new Texture(Gdx.files.internal("dropBad.png")), 0, 300);
	}
	
	public boolean accionColisionTarro(Tarro tarro) {
		tarro.dañar();
		if (tarro.getVidas() <= 0)
			return false;  // Si el tarro se queda sin vidas retorna falso / Game Over.
		return true;
	}
}
