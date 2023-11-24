package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class ObjetosNivelTres implements ObjetosFactory {
	public GotaBuena crearGotaBuena(int velInicialLluvia) {
		return new GotaMorada(velInicialLluvia);
	}
	
	public GotaMala crearGotaMala(int velInicialLluvia) {
		return new GotaRoja(velInicialLluvia);
	}
	
	public PowerUp crearPowerUp() {
		return new InmortalidadPowerUp();
	}
	
	public PowerDown crearPowerDown() {
		return new InvertirPuntosPowerDown();
	}
	
	public Tarro crearTarro() {
		return new TarroMadera();
	}
}
