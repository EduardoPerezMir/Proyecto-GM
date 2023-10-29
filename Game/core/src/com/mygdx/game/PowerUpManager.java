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
    
    private float tiempoEntrePowerUps = 12.0f; // Tiempo en segundos entre PowerUps
    private float tiempoActivadoPowerUp = 0; // Tiempo de activación del power-up
    private float tiempoTranscurrido = 0;
    private float duracionPowerUp = 7.0f;

    private PowerUp powerUpActivo; // Almacena el power-up activo
    private PowerUp powerUpCurrent;// Almacena el power-up mostrado en pantalla
    Rectangle rectangulo;

    private BitmapFont font; // Fuente para mostrar información

    private List<PowerUp> powersDisponibles; // Lista de power-ups disponibles

    public PowerUpManager() {
    	this.inicioPower = Gdx.audio.newSound(Gdx.files.internal("soundinmortal.mp3"));
        this.finPower = Gdx.audio.newSound(Gdx.files.internal("endpower.mp3"));
        
        powersDisponibles = new ArrayList<>();

        // Agrega los diferentes tipos de power-ups disponibles a la lista
        powersDisponibles.add(new InmortalidadPowerUp());
        powersDisponibles.add(new AumentoVelocidadPowerUp());
        powersDisponibles.add(new DuplicarPuntosPowerUp());
        powersDisponibles.add(new AumentoTamañoTarroPowerDown());
        powersDisponibles.add(new AumentoVelocidadLluviaPowerDown());
        
        font = new BitmapFont(); // Inicializa la fuente para mostrar información
    }

    public void crear() {
    	rectangulo = new Rectangle();
    	rectangulo.width = 42;
    	rectangulo.height = 64;
        crearPW();
        lastDropTime = TimeUtils.millis();
    }

    // Método privado para crear un nuevo power-up
    private void crearPW() {
        rectangulo.x = MathUtils.random(0, 800 - 42);
        rectangulo.y = 480;
        powerUpCurrent = powersDisponibles.get(MathUtils.random(powersDisponibles.size() - 1));
    }

    // Método para activar un power-up y aplicarlo 
    public void activarPowerUp(PowerUp powerUp, Tarro tarro,Lluvia lluvia,Sound inicioPower) {
    	inicioPower.play();
    	powerUp.aplicarPowerUp(tarro,lluvia);
        powerUpActivo = powerUp;
        tiempoActivadoPowerUp = TimeUtils.millis();
        tiempoTranscurrido = 0;
        powerUpCurrent = null;
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

        if (powerUpCurrent != null) {
            // Mueve el power-up hacia arriba
            rectangulo.y -= 300 * delta * lluvia.getVelocidadLluvia() ;
            
            // Si el power-up está fuera de la pantalla, se elimina
            if (rectangulo.y + 64 < 0) {
            	powerUpCurrent = null;
                return;
            }
            
            // Si el power-up se superpone con el tarro, se activa
            if (rectangulo.overlaps(tarro.getArea())) {
                activarPowerUp(powerUpCurrent, tarro,lluvia,inicioPower);
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
        if (powerUpCurrent != null) {
            // Dibuja el power-up actual si está disponible
        	powerUpCurrent.dibujar(batch, rectangulo.x, rectangulo.y);
        	
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
    	for (PowerUp powerUp : powersDisponibles) {
            powerUp.destruir();
        }
     }
}