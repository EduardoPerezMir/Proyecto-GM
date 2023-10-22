package com.mygdx.game;

public class InmortalidadPowerUp implements PowerUp {
	@Override
    public void aplicarPowerUp(Tarro tarro) {
        tarro.setInmortal(true);
    }
	public void quitarPowerUp(Tarro tarro) {
        tarro.setInmortal(false);
    }

}
