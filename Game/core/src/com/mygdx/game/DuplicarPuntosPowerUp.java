package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DuplicarPuntosPowerUp implements PowerUp {
    private Texture textura;

    // Constructor: inicializa la textura con la imagen del power-up
    public DuplicarPuntosPowerUp() {
        this.textura = new Texture(Gdx.files.internal("duplicarpuntos.png"));
    }
    
    @Override
	public void aplicarPowerUp(Tarro tarro) {
    	tarro.setAumentoPuntos(2);
	}
    
	@Override
	public void quitarPowerUp(Tarro tarro) {
		tarro.setAumentoPuntos(1);
	}

	@Override
	public void dibujar(SpriteBatch batch, float x, float y) {
		batch.draw(textura, x, y);
	}
	
    public void destruir() {
        textura.dispose();
    }

}
