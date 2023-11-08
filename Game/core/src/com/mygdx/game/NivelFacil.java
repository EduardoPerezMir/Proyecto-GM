package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class NivelFacil implements NivelDificultad{
	private int velInicialLluvia;
	private int velTarro;
	private Texture backgroundTexture;
	
	public NivelFacil() {
		velTarro = 550;
		velInicialLluvia = 400;
		backgroundTexture = new Texture(Gdx.files.internal("fondoFacil.jpg"));
	}
	
	public Gota crearGotaDeLluvia() {
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
