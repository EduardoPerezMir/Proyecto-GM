package com.mygdx.game;

public class AumentoTamañoTarroPowerDown implements PowerUp {
	
	@Override
	public void aplicarPowerUp(Object tarro) {
		((Tarro) tarro).tamañoTarroGrande(true);
	}

	public void quitarPowerUp(Object tarro) {
		((Tarro) tarro).tamañoTarroGrande(false);
	}

}
