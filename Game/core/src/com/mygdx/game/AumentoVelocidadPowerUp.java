package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class AumentoVelocidadPowerUp implements PowerUp {
    private Texture textura;
    
    public AumentoVelocidadPowerUp() {
        this.textura = new Texture(Gdx.files.internal("velocidad.png"));
    } 
    
    @Override
	public void aplicarPowerUp(Tarro tarro) {
    	tarro.setMultiplicadorVel(1.6);
	}
    
	@Override
	public void quitarPowerUp(Tarro tarro) {
		tarro.setMultiplicadorVel(1);
	}

	@Override
	public void dibujar(SpriteBatch batch, float x, float y) {
		batch.draw(textura, x, y);
	}
	
    public void destruir() {
        textura.dispose();
    }
}