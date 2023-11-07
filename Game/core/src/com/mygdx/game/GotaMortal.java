package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GotaMortal extends Gota {
	public GotaMortal(int velocidadCaida) {
		super(velocidadCaida);
		// Se asigna la textura de la gota mala desde los assets.
		setTextura(new Texture(Gdx.files.internal("dropDeath.png")));
	}
	
	// Implementación concreta del método abstracto.
	public int accionColisionTarro(Tarro tarro) {
		int vidaAntes = tarro.getVidas();
		tarro.dañar();
		int vidaDespues = tarro.getVidas();
		if (vidaAntes != vidaDespues)
			return -1;  // Si el tarro se queda sin vidas retorna falso / Game Over.
		
		return 1;
	}
}