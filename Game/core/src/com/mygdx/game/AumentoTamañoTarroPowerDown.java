package com.mygdx.game;

public class AumentoTamañoTarroPowerDown implements PowerUp {
	
	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.tamañoTarroGrande(true);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.tamañoTarroGrande(false);
	}

}
