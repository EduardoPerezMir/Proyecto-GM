package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InvertirPuntosPowerDown implements PowerDown {

private Texture textura;
	
	public InvertirPuntosPowerDown() {
		this.textura = new Texture(Gdx.files.internal("restarPuntos.png"));
	}
	
	@Override
	public void aplicarPowerDown(Tarro tarro) {
		tarro.setAumentoPuntos(-1);
	}

	@Override
	public void quitarPowerDown(Tarro tarro) {
		tarro.setAumentoPuntos(1);
	}

	@Override
	public void dibujar(SpriteBatch batch, float x, float y) {
		batch.draw(textura, x, y);
	}

	@Override
	public void destruir() {
		textura.dispose();
		
	}
}
