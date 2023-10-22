package com.mygdx.game;

public class DuplicarPuntosPowerUp implements PowerUp {

	@Override
	public void aplicarPowerUp(Object tarro) {
		((Tarro) tarro).setAumentoPuntos(true);
	}

	public void quitarPowerUp(Object tarro) {
		((Tarro) tarro).setAumentoPuntos(false);
	}
}

