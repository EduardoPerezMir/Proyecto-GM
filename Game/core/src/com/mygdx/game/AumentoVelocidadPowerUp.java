package com.mygdx.game;

public class AumentoVelocidadPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Object tarro) {
		((Tarro) tarro).setVelocidad(2);
	}

	public void quitarPowerUp(Object tarro) {
		((Tarro) tarro).setVelocidad(0.5);
	}
}
