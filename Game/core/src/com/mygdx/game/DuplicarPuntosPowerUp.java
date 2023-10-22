package com.mygdx.game;

public class DuplicarPuntosPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Tarro tarro) {
		tarro.setAumentoPuntos(true);
	}

	public void quitarPowerUp(Tarro tarro) {
		tarro.setAumentoPuntos(false);
	}
}

