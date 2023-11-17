package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class creacionNivelTres implements ObjetosFactory {
	public Gota crearGota(int velInicialLluvia) {
		Gota nuevaGota = null;
		int azar = MathUtils.random(1,10);
		
	    if (azar < 7)
	    	if (azar > 1)
	    		nuevaGota = new GotaMala(velInicialLluvia);
	    	else
	    		nuevaGota = new GotaMortal(velInicialLluvia);
		else {
			if (azar < 9)
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
