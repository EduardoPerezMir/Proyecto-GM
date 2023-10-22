package com.mygdx.game;

public class InmortalidadPowerUp implements PowerUp {
	@Override
    public void aplicarPowerUp(Object tarro) {
		((Tarro) tarro).setInmortal(true);
    }
	public void quitarPowerUp(Object tarro) {
		((Tarro) tarro).setInmortal(false);
    }

}
