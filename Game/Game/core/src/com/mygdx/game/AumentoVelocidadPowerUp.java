package com.mygdx.game;

public class AumentoVelocidadPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Tarro tarro) {
		tarro.setVelocidad(2);
	}

	public void quitarPowerUp(Tarro tarro) {
		tarro.setVelocidad(0.5);
	}
}
