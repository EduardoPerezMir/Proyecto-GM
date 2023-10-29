package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase que implementa la interfaz PowerUp y representa un power-up
 * que aumenta la velocidad de un objeto Tarro en un juego de lluvia.
 */
public class AumentoVelocidadPowerUp implements PowerUp {
    private Texture textura;
    
    /**
     * Constructor de la clase que carga la textura del power-up.
     */
    public AumentoVelocidadPowerUp() {
        this.textura = new Texture(Gdx.files.internal("velocidad.png"));
    } 
    
    /**
     * Aplica el power-up al objeto Tarro, aumentando su velocidad.
     * @param tarro El objeto Tarro al que se aplica el power-up.
     * @param lluvia El objeto Lluvia en el juego.
     */
    @Override
    public void aplicarPowerUp(Tarro tarro, Lluvia lluvia) {
        tarro.setVelocidad(1.6);
    }

    /**
     * Quita el efecto del power-up, restaurando la velocidad normal del Tarro.
     * @param tarro El objeto Tarro al que se le quita el power-up.
     * @param lluvia El objeto Lluvia en el juego.
     */
    public void quitarPowerUp(Tarro tarro, Lluvia lluvia) {
        tarro.setVelocidad(0.625);
    }
    
    /**
     * Dibuja la textura del power-up en un objeto SpriteBatch en las coordenadas (x, y).
     * @param batch El objeto SpriteBatch en el que se dibuja.
     * @param x La coordenada X donde se dibuja el power-up.
     * @param y La coordenada Y donde se dibuja el power-up.
     */
    public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
    
    /**
     * Libera los recursos utilizados por la textura del power-up.
     */
    public void destruir() {
        textura.dispose();
    }
}