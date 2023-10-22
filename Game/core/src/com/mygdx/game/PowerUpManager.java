package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class PowerUpManager {
    private long lastDropTime;
    private Texture inmortabilidad;
    Sound inicioPower;
    Sound finPower;
    private Texture velocidad;
    private Texture aumentarTarro;
    private Texture duplicarPuntos;
    private Texture aumentarVelocidad;
    private float tiempoEntrePowerUps = 15.0f; // Tiempo en segundos entre PowerUps
    private float tiempoActivadoPowerUp = 0; // Tiempo de activación del power-up
    private float tiempoTranscurrido = 0;
    private float duracionPowerUp = 7.0f;

    private PowerUpInfo powerUpInfo; // Almacena información sobre el power-up actualmente disponible
    private PowerUp powerUpActivo; // Almacena el power-up activo

    private BitmapFont font; // Fuente para mostrar información

    private List<PowerUp> availablePowerUps; // Lista de power-ups disponibles

    public PowerUpManager(Texture inmortabilidad,Sound inicioPower,Sound finPower, Texture velocidad, Texture duplicarPuntos,Texture aumentarTarro,Texture aumentarVelocidad) {
    	this.inicioPower = inicioPower;
        this.finPower = finPower;
        
    	this.inmortabilidad = inmortabilidad;
        this.velocidad = velocidad;
        this.duplicarPuntos = duplicarPuntos;
        this.aumentarTarro = aumentarTarro;
        this.aumentarVelocidad = aumentarVelocidad;
        availablePowerUps = new ArrayList<>();

        // Agrega los diferentes tipos de power-ups disponibles a la lista
        availablePowerUps.add(new InmortalidadPowerUp());
        availablePowerUps.add(new AumentoVelocidadPowerUp());
        availablePowerUps.add(new DuplicarPuntosPowerUp());
        availablePowerUps.add(new AumentoTamañoTarroPowerDown());
        availablePowerUps.add(new AumentoVelocidadLluviaPowerDown());

        font = new BitmapFont(); // Inicializa la fuente para mostrar información
    }

    // Método para crear un nuevo power-up
    public void crear() {
        crearPW();
        lastDropTime = TimeUtils.millis();
    }

    // Método privado para crear un nuevo power-up
    private void crearPW() {
        Rectangle newRectangle = new Rectangle();
        newRectangle.x = MathUtils.random(0, 800 - 42);
        newRectangle.y = 480;
        newRectangle.width = 42;
        newRectangle.height = 64;

        PowerUp randomPowerUp = availablePowerUps.get(MathUtils.random(availablePowerUps.size() - 1));
        powerUpInfo = new PowerUpInfo(newRectangle, randomPowerUp);
    }

    // Método para activar un power-up y aplicarlo 
    public void activarPowerUp(PowerUp powerUp, Tarro tarro,Lluvia lluvia,Sound inicioPower) {
    	inicioPower.play();
    	if (powerUp instanceof AumentoVelocidadLluviaPowerDown) {
    		powerUp.aplicarPowerUp(lluvia);
            powerUpActivo = powerUp;
            tiempoActivadoPowerUp = TimeUtils.millis();
            tiempoTranscurrido = 0;
    	}
    	else {
    		powerUp.aplicarPowerUp(tarro);
            powerUpActivo = powerUp;
            tiempoActivadoPowerUp = TimeUtils.millis();
            tiempoTranscurrido = 0;
    	}
    }

    // Método para actualizar el movimiento de los power-ups y gestionar su activación y desactivación
    public void actualizarMovimiento(Tarro tarro,Lluvia lluvia) {
        long currentTime = TimeUtils.millis();
        float delta = Gdx.graphics.getDeltaTime();
        
        // Si ha pasado suficiente tiempo, se crea un nuevo power-up
        if (currentTime - lastDropTime > (tiempoEntrePowerUps * 1000)) {
            crearPW();
            lastDropTime = currentTime;
        }

        if (powerUpInfo != null) {
            // Mueve el power-up hacia arriba
            powerUpInfo.getRectangle().y -= 300 * delta;
            
            // Si el power-up está fuera de la pantalla, se elimina
            if (powerUpInfo.getRectangle().y + 64 < 0) {
                powerUpInfo = null;
                return;
            }
            
            // Si el power-up se superpone con el tarro, se activa
            if (powerUpInfo.getRectangle().overlaps(tarro.getArea())) {
                PowerUp powerUpChoque = powerUpInfo.getPowerUp();
                activarPowerUp(powerUpChoque, tarro,lluvia,inicioPower);
                powerUpInfo = null;
            }
        }

        // Si hay un power-up activo, se controla su duración y efecto
        if (tiempoActivadoPowerUp > 0) {
            tiempoTranscurrido += delta * 1000;
            
            // Si ha pasado suficiente tiempo, se desactiva el power-up
            if (tiempoTranscurrido >= duracionPowerUp * 1000) {
            	finPower.play();
            	if (powerUpActivo instanceof AumentoVelocidadLluviaPowerDown) {
            		powerUpActivo.quitarPowerUp(lluvia);
                    tiempoActivadoPowerUp = 0;
                    tiempoTranscurrido = 0;
                }
            	else
            	{
            		powerUpActivo.quitarPowerUp(tarro);
                    tiempoActivadoPowerUp = 0;
                    tiempoTranscurrido = 0;
            	}
            	
            }
        }
    }

    // Método para actualizar la representación gráfica de los power-ups y el tiempo restante de un power-up activo
    public void actualizarDibujo(SpriteBatch batch) {
        if (powerUpInfo != null) {
            // Dibuja el power-up actual si está disponible
            if (powerUpInfo.getPowerUp() instanceof InmortalidadPowerUp) {
                batch.draw(inmortabilidad, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
            }
            if (powerUpInfo.getPowerUp() instanceof AumentoVelocidadPowerUp) {
                batch.draw(velocidad, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
            }
            if (powerUpInfo.getPowerUp() instanceof DuplicarPuntosPowerUp) {
                batch.draw(duplicarPuntos, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
            }
            if (powerUpInfo.getPowerUp() instanceof AumentoTamañoTarroPowerDown) {
                batch.draw(aumentarTarro, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
            }
            if (powerUpInfo.getPowerUp() instanceof AumentoVelocidadLluviaPowerDown) {
                batch.draw(aumentarVelocidad, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
            }
        }

        if (tiempoActivadoPowerUp > 0) {
            // Muestra el tiempo restante de un power-up activo
            float tiempoRestante = duracionPowerUp * 1000 - tiempoTranscurrido;
            if (tiempoRestante > 0) {
                String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
                font.draw(batch, formattedTime, 10, 20);
            }
        }
    }
    public void destruir() {
    	inmortabilidad.dispose();
    	velocidad.dispose();
    	duplicarPuntos.dispose();
    	aumentarTarro.dispose();
    	aumentarVelocidad.dispose();
    	inicioPower.dispose();
    	finPower.dispose();
    	
     }
}
