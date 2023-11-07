package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class NivelFacil extends NivelDificultad{
	private int velInicial;
	
	public NivelFacil() {
		velInicial = 450;
		setVelInicial(velInicial);
		setBackgroundTexture(new Texture(Gdx.files.internal("fondoMedio.jpg")));
	}
	
	
	public void crearGotaDeLluvia() {
		Gota nuevaGota = null;
		int azar = MathUtils.random(1,10);

	    if (azar < 2)
	    	nuevaGota = new GotaMala(velInicial);
		else {
			if (azar < 7)
				nuevaGota = new GotaAzul(velInicial);
		    else
		    	nuevaGota = new GotaAmarilla(velInicial);
		}
	    if (nuevaGota != null)
	    	addArray(nuevaGota);
	    setLastDropTime(TimeUtils.nanoTime());
	}
	
	public void velTarroAcordeNivel(Tarro tarro) {
		tarro.setVelocidad(550);
	}
}
