package com.mygdx.game;

public class ObjetosNivelDos implements ObjetosFactory {
	public GotaBuena crearGotaBuena(int velInicialLluvia) {
		return new GotaMorada(velInicialLluvia);
	}
	
	public GotaMala crearGotaMala(int velInicialLluvia) {
		return new GotaNaranja(velInicialLluvia);
	}
	
	public PowerUp crearPowerUp() {
		return new AumentoVelocidadPowerUp();
	}
	
	public PowerDown crearPowerDown() {
		return new InvertirPuntosPowerDown();
	}
	
	public Tarro crearTarro() {
		return new TarroPlastico();
	}
}
