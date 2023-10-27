package com.mygdx.game;

public class AumentoVelocidadPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setVelocidad(1.5);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setVelocidad(0.6666666);
	}
}
