package com.mygdx.game;

public class AumentoVelocidadPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Object tarro) {
		((Tarro) tarro).setVelocidad(1.5);
	}

	public void quitarPowerUp(Object tarro) {
		((Tarro) tarro).setVelocidad(0.6666666);
	}
}
