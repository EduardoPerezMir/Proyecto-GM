package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

/*La clase PowerUpInfo se utiliza para
vincular la posición en pantalla del power-up con el tipo
para mantener ambos en un mismo arreglo*/

public class PowerUpInfo {
    private Rectangle rectangle;
    private PowerUp powerUp;

    public PowerUpInfo(Rectangle rectangle, PowerUp powerUp) {
        this.rectangle = rectangle;
        this.powerUp = powerUp;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}