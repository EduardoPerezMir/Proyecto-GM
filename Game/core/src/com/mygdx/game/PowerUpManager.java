package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class PowerUpManager {
    private long lastDropTime;
    private Texture inmortabilidad;
    private float timeBetweenPowerUps = 5.0f; // Tiempo en segundos entre PowerUps
    
    private Rectangle powerUp;
    private int tipo;

    public PowerUpManager(Texture inmortabilidad) {
        this.inmortabilidad = inmortabilidad;
    }

    public void crear() {
        crearPW();
        lastDropTime = TimeUtils.millis();
    }

    private void crearPW() {
    	powerUp = new Rectangle();
        powerUp.x = MathUtils.random(0, 800 - 42);
        powerUp.y = 480;
        powerUp.width = 42;
        powerUp.height = 64;
        tipo=MathUtils.random(1,3);

    }

    public void actualizarMovimiento(Tarro tarro) {
        long currentTime = TimeUtils.millis();
        if (currentTime - lastDropTime > (timeBetweenPowerUps * 1000)) {
            crearPW();
            lastDropTime = currentTime;
        }

        // Revisar si los PowerUps cayeron al suelo o chocaron con el tarro
        if(powerUp != null) {
            powerUp.y -= 300 * Gdx.graphics.getDeltaTime();
            
            // Cae al suelo y se elimina
            if (powerUp.y + 64 < 0) {
            	powerUp = null;
            	return;
            }
            if (powerUp.overlaps(tarro.getArea())) { // El PowerUp choca con el tarro
                if (tipo == 1) { // PowerUp inmortabilidad
                	
                	powerUp = null;
                } 
                if(tipo == 2) { 
                	
                	powerUp = null;
                }
                else{
                	powerUp = null;
                	
                }
            }
        }
    }

    public void actualizarDibujo(SpriteBatch batch) {
    	if (powerUp != null) {
    		if (tipo == 1)batch.draw(inmortabilidad, powerUp.x, powerUp.y);
        }
    }
}