package com.mygdx.game;

public class ObjetosNivelUno implements ObjetosFactory {
	public GotaBuena crearGotaBuena(int velInicialLluvia) {
		return new GotaAmarilla(velInicialLluvia);
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
		return new TarroMetal();
	}
}
