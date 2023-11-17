package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AumentoTamañoTarroPowerDown implements PowerUp {
    private Texture textura; // Textura para el power-up

    // Constructor: inicializa la textura con la imagen del power-up
    public AumentoTamañoTarroPowerDown() {
        this.textura = new Texture(Gdx.files.internal("tamañotarro.png"));
    }

    // Aplica el power-up para aumentar el tamaño del tarro
    @Override
    public Tarro aplicarPowerUp() {
        //tarro.tamañoTarroGrande(true);
    	return null;
    }

    // Quita el efecto del power-up, restaurando el tamaño del tarro
    public void quitarPowerUp(Tarro tarro, Lluvia lluvia) {
        //tarro.tamañoTarroGrande(false);
    }

    // Dibuja el power-up en pantalla en la posición (x, y)
    public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }

    // Libera los recursos al destruir el power-up
    public void destruir() {
        textura.dispose();
    }
}
