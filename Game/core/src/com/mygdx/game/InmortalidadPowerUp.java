package com.mygdx.game;

public class InmortalidadPowerUp implements PowerUp {
	@Override
    public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setInmortal(true);
    }
	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setInmortal(false);
    }

}
