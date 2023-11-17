package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class creacionNivelDos implements ObjetosFactory {
	public Gota crearGota(int velInicialLluvia) {
		Gota nuevaGota = null;
		int azar = MathUtils.random(1,10);

	    if (azar < 4)
	    	nuevaGota = new GotaMala(velInicialLluvia);
		else {
			if (azar < 8)
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
