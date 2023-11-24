package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InmortalidadPowerUp implements PowerUp {
	private Texture textura; // Variable para almacenar la textura del power-up de inmortalidad
    
    // Constructor de la clase
    public InmortalidadPowerUp () {
        this.textura = new Texture(Gdx.files.internal("inmortabilidad.png")); // Carga la textura desde un archivo interno llamado inmortabilidad.png
    }
    
    @Override
	public void aplicarPowerUp(Tarro tarro) {
		tarro.setEsInmortal(true);
	}
    
	@Override
	public void quitarPowerUp(Tarro tarro) {
		tarro.setEsInmortal(false);
	}

	@Override
	public void dibujar(SpriteBatch batch, float x, float y) {
		batch.draw(textura, x, y);
	}
    
    public void destruir() {
        textura.dispose(); 
    }
}