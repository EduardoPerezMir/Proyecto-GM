package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ReducirVelocidadPowerDown implements PowerDown{

	private Texture textura;
	
	public ReducirVelocidadPowerDown() {
		this.textura = new Texture(Gdx.files.internal("reducirVelocidad.png"));
	}
	
	@Override
	public void aplicarPowerDown(Tarro tarro) {
		tarro.setMultiplicadorVel(0.625);
	}

	@Override
	public void quitarPowerDown(Tarro tarro) {
		tarro.setMultiplicadorVel(1.6);
	}

	@Override
	public void dibujar(SpriteBatch batch, float x, float y) {
		
	}

	@Override
	public void destruir() {
		textura.dispose();
		
	}
}
