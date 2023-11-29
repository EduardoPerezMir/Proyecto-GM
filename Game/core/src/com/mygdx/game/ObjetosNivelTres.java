package com.mygdx.game;

public class ObjetosNivelTres implements ObjetosFactory {
	public GotaBuena crearGotaBuena(int velInicialLluvia) {
		return new GotaAzul(velInicialLluvia);
	}
	
	public GotaMala crearGotaMala(int velInicialLluvia) {
		return new GotaMortal(velInicialLluvia);
	}
	
	public PowerUp crearPowerUp() {
		return new DuplicarPuntosPowerUp();
	}
	
	public PowerDown crearPowerDown() {
		return new CongelarTarroPowerDown();
	}
	
	public Tarro crearTarro() {
		return new TarroMadera();
	}
}
