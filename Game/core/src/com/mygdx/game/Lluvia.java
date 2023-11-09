package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Gota> gotas; // Array para almacenar las gotas de lluvia
    private long lastDropTime; // Registro del tiempo de la última gota creada
    private Music rainMusic; // Música de fondo para la lluvia
    private float velY2; // Velocidad de caída de las gotas
    private float velYFuncionPuntaje; // Variable de velocidad modificada por el puntaje
    private int velInicial;
    private NivelDificultad nivel;
    
    private static Lluvia instance;
    
    // Constructor que inicializa parámetros iniciales
    private Lluvia(NivelDificultad nivel) {
		gotas = new Array<Gota>();
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		velY2 = 1;
		velYFuncionPuntaje = 1;
		this.nivel = nivel;
		
		nivel.setVelLluviaInicialAcordeNivel(this);
	}
    
    public static Lluvia getLluvia(NivelDificultad nivel) {
        if (instance == null) {
            instance = new Lluvia(nivel);
        }
        return instance;
    }
	
	// Método para ajustar la velocidad de las gotas

	public int getVelInicial() {
		return velInicial;
	}

	public void setVelInicial(int velInicial) {
		this.velInicial = velInicial;
	}

	public long getLastDropTime() {
		return lastDropTime;
	}

	public void setLastDropTime(long lastDropTime) {
		this.lastDropTime = lastDropTime;
	}

	public void setVelocidad(double velY2) {
        this.velY2 *= velY2;
    }

	public void addArray(Gota nuevaGota) {
		gotas.add(nuevaGota);
	}
	
    // Método para incrementar la velocidad en función del puntaje
    public void incrementoVelocidadFuncionPuntaje(Tarro tarro) {
        velYFuncionPuntaje += 0.01 * 0.75;
    }

    // Método para iniciar la lluvia y reproducir la música
    public void crear() {
        crearGotaDeLluvia();
        // start the playback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();
    }
	
	/*Método para crear una nueva gota de lluvia
	Generación de diferentes tipos de gotas en función de la dificultad y números aleatorios
    Basado en la dificultad, se generan diferentes tipos de gotas
    con diferentes probabilidades
    Luego se agrega la nueva gota al conjunto de gotas
    y se registra el tiempo asociado a la creación de la gota*/
	public void crearGotaDeLluvia() {
		Gota nuevaGota = nivel.crearGotaDeLluvia();
	    if (nuevaGota != null)
	    	gotas.add(nuevaGota);
	    
	    lastDropTime = TimeUtils.nanoTime();
	}
	
	/*Método para actualizar el movimiento de las gotas de lluvia
    Generar gotas de lluvia a intervalos regulares
    Comprueba el tiempo transcurrido para crear nuevas gotas
    y las añade al conjunto de gotas
    Luego verifica si alguna de las gotas ha llegado al suelo
    o ha chocado con el objeto "Tarro"
    Si se detecta una colisión, se lleva a cabo la acción correspondiente
    según el tipo de colisión*/

    public boolean actualizarMovimiento(Tarro tarro) {
        // generar gotas de lluvia
        if (TimeUtils.nanoTime() - lastDropTime > 100000000 / velYFuncionPuntaje) crearGotaDeLluvia();
        
        // revisar si las gotas cayeron al suelo o chocaron con el tarro
        for (int i = 0; i < gotas.size; i++) {
            Gota gotaActual = gotas.get(i);

            float ponderadorVelocidad = velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            int accionARealizar = gotaActual.verificarColisionTarro(tarro, ponderadorVelocidad);

            if (accionARealizar != 0) {
                if (accionARealizar == 1)
                    incrementoVelocidadFuncionPuntaje(tarro);

                gotaActual.destruir();
                gotas.removeIndex(i);

                if (accionARealizar == -1)
                    return false;
            }
        }
        return true;
    }
	
   /*Método para actualizar el dibujo de la lluvia
   Dibuja todas las gotas de lluvia presentes en el conjunto de gotas
   utilizando un objeto SpriteBatch para el dibujo*/
    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (int i = 0; i < gotas.size; i++) {
            Gota gotaActual = gotas.get(i);
            gotaActual.dibujarGota(batch);
        }
    }

    public double getVelocidadLluvia() {
        return velY2 * velYFuncionPuntaje;
    }

    public boolean hayMusica() {
        return rainMusic.isPlaying();
    }

    public void destruir() {
        rainMusic.dispose();
        instance = null;
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    
    }
}