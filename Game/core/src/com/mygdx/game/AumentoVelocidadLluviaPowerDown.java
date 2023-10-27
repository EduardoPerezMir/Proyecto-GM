package com.mygdx.game;

public class AumentoVelocidadLluviaPowerDown implements PowerUp {

	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		lluvia.setVelocidad(2);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		lluvia.setVelocidad(0.5);
	}
}
