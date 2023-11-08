package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class NivelMedio implements NivelDificultad {
	private int velInicialLluvia;
	private int velTarro;
	private Texture backgroundTexture;
	
	public NivelMedio() {
		velTarro = 500;
		velInicialLluvia = 425;
		backgroundTexture = new Texture(Gdx.files.internal("fondoMedio.jpg"));
	}

	public Gota crearGotaDeLluvia() {
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
	
	public void setVelTarroAcordeNivel(Tarro tarro) {
		tarro.setVelocidad(velTarro);
	}
	
	public void setVelLluviaInicialAcordeNivel(Lluvia lluvia) {
		lluvia.setVelInicial(velInicialLluvia);
	}
	
	public void setBackgroundTextureGame(GameScreen gameScreen) {
		gameScreen.setBackgroundTexture(backgroundTexture);
	}
}