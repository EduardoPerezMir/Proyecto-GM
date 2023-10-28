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
    Sound inicioPower;
    Sound finPower;
    private float tiempoEntrePowerUps = 15.0f; // Tiempo en segundos entre PowerUps
    private float tiempoActivadoPowerUp = 0; // Tiempo de activación del power-up
    private float tiempoTranscurrido = 0;
    private float duracionPowerUp = 7.0f;

    private PowerUpInfo powerUpInfo; // Almacena información sobre el power-up actualmente disponible
    private PowerUp powerUpActivo; // Almacena el power-up activo

    private BitmapFont font; // Fuente para mostrar información

    private List<PowerUp> powersDisponibles; // Lista de power-ups disponibles

    public PowerUpManager(Texture inmortalidad,Sound inicioPower,Sound finPower, Texture velocidad, Texture duplicarPuntos,Texture aumentarTarro,Texture aumentarVelocidad) {
    	this.inicioPower = inicioPower;
        this.finPower = finPower;
        
        powersDisponibles = new ArrayList<>();

        // Agrega los diferentes tipos de power-ups disponibles a la lista
        powersDisponibles.add(new InmortalidadPowerUp(inmortalidad));
        powersDisponibles.add(new AumentoVelocidadPowerUp(velocidad));
        powersDisponibles.add(new DuplicarPuntosPowerUp(duplicarPuntos));
        powersDisponibles.add(new AumentoTamañoTarroPowerDown(aumentarTarro));
        powersDisponibles.add(new AumentoVelocidadLluviaPowerDown(aumentarVelocidad));

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

        PowerUp randomPowerUp = powersDisponibles.get(MathUtils.random(powersDisponibles.size() - 1));
        powerUpInfo = new PowerUpInfo(newRectangle, randomPowerUp);
    }

    // Método para activar un power-up y aplicarlo 
    public void activarPowerUp(PowerUp powerUp, Tarro tarro,Lluvia lluvia,Sound inicioPower) {
    	inicioPower.play();
    	powerUp.aplicarPowerUp(tarro,lluvia);
        powerUpActivo = powerUp;
        tiempoActivadoPowerUp = TimeUtils.millis();
        tiempoTranscurrido = 0;
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
            powerUpInfo.getRectangle().y -= 300 * delta * lluvia.getVelocidadLluvia() ;
            //powerUpInfo.getRectangle().y -= gotaActual.getVelocidadCaida() * velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            
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
            	powerUpActivo.quitarPowerUp(tarro,lluvia);
                tiempoActivadoPowerUp = 0;
                tiempoTranscurrido = 0;
            }
        }
    }

    // Método para actualizar la representación gráfica de los power-ups y el tiempo restante de un power-up activo
    public void actualizarDibujo(SpriteBatch batch) {
        if (powerUpInfo != null) {
            // Dibuja el power-up actual si está disponible
        	powerUpInfo.getPowerUp().dibujar(batch, powerUpInfo.getRectangle().x, powerUpInfo.getRectangle().y);
        	
        }

        if (tiempoActivadoPowerUp > 0) {
            // Muestra el tiempo restante de un power-up activo
            float tiempoRestante = duracionPowerUp * 1000 - tiempoTranscurrido;
            if (tiempoRestante > 0) {
                String formattedTime = String.format("Tiempo restante de power up: %.0fs", (tiempoRestante / 1000));
                font.draw(batch, formattedTime, 10, 30);
            }
        }
    }
    
    public void destruir() {
    	inicioPower.dispose();
    	finPower.dispose();
    	
     }
}