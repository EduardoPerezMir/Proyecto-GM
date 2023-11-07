package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class NivelMedio extends NivelDificultad {
	private int velInicial;
	
	public NivelMedio() {
		velInicial = 425;
		setVelInicial(velInicial);
		setBackgroundTexture(new Texture(Gdx.files.internal("fondoMedio.jpg")));
	}
	
	
	public void crearGotaDeLluvia() {
		Gota nuevaGota = null;
		int azar = MathUtils.random(1,10);

	    if (azar < 4)
	    	nuevaGota = new GotaMala(velInicial);
		else {
			if (azar < 8)
				nuevaGota = new GotaAzul(velInicial);
		    else
		    	nuevaGota = new GotaAmarilla(velInicial);
		}
	    if (nuevaGota != null)
	    	addArray(nuevaGota);
	    setLastDropTime(TimeUtils.nanoTime());
	}
	
	public void velTarroAcordeNivel(Tarro tarro) {
		tarro.setVelocidad(500);
	}
}