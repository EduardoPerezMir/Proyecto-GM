package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class creacionNivelUno implements ObjetosFactory {
	public Gota crearGota(int velInicialLluvia) {
		Gota nuevaGota = null;
		int azar = MathUtils.random(1,10);

	    if (azar < 2)
	    	nuevaGota = new GotaMala(velInicialLluvia);
		else {
			if (azar < 7)
				nuevaGota = new GotaAzul(velInicialLluvia);
		    else
		    	nuevaGota = new GotaAmarilla(velInicialLluvia);
		}

	    return nuevaGota;
	}
	
	public Tarro crearTarro() {
		return new TarroNormal();
	}
}
