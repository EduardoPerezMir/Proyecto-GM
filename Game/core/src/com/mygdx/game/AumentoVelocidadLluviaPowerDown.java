package com.mygdx.game;

public class AumentoVelocidadLluviaPowerDown implements PowerUp {

	@Override
	public void aplicarPowerUp(Object lluvia) {
		((Lluvia) lluvia).setVelocidad(2);
	}

	public void quitarPowerUp(Object lluvia) {
		((Lluvia) lluvia).setVelocidad(0.5);
	}
}
