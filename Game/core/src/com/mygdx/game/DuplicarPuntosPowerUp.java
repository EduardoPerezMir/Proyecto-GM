package com.mygdx.game;

public class DuplicarPuntosPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setAumentoPuntos(true);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setAumentoPuntos(false);
	}
}

