package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<GotaBuena> gotasB; // Array para almacenar las gotas buenas de lluvia 
	private Array<GotaMala> gotasM; // Array para almacenar las gotas buenas de lluvia 
    private long lastDropTime; // Registro del tiempo de la última gota creada
    private Music rainMusic; // Música de fondo para la lluvia
    private float velC;
    private float velY2; // Velocidad de caída de las gotas
    private float velYFuncionPuntaje; // Variable de velocidad modificada por el puntaje
    private int velInicial;
    
    private ObjetosFactory crear;
    
    private static Lluvia instance;
    
    //powers
    private long lastDropTimePower;
    
    private Sound inicioPower;
    private Sound finPower;
    
    private float tiempoEntrePowerUps = 12.0f; // Tiempo en segundos entre PowerUps
    private float tiempoActivadoPowerUp = 0; // Tiempo de activación del power-up
    private float tiempoTranscurrido = 0;
    private float duracionPowerUp = 7.0f;
    
    //private PowerUp powerUpActivo; // Almacena el power-up activo
    //private PowerUp powerUpCurrent;// Almacena el power-up mostrado en pantalla
    private Rectangle rectangulo;
    
    private PowerUp powerUp;
    private PowerDown powerDown;
    private int activoP = -1;
    private boolean dibujarPower = false;
    //
    
    // Constructor que inicializa parámetros iniciales
    private Lluvia(ObjetosFactory crear) {
		gotasB = new Array<GotaBuena>();
		gotasM = new Array<GotaMala>();
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		
		this.velY2 = 1;
		velYFuncionPuntaje = 1;
		this.crear = crear;
		
		//PW
		this.inicioPower = Gdx.audio.newSound(Gdx.files.internal("soundinmortal.mp3"));
        this.finPower = Gdx.audio.newSound(Gdx.files.internal("endpower.mp3"));
        
        crearPowers();
			
	}
    private void crearPowers() {
        rectangulo = new Rectangle();
    	rectangulo.width = 49;
    	rectangulo.height = 49;
    	powerUp = crear.crearPowerUp();
    	powerDown = crear.crearPowerDown();
    	
        crearPW();
        lastDropTimePower = TimeUtils.millis(); 
    }
    
    private void crearPW() {
        rectangulo.x = MathUtils.random(0, 800 - 42);
        rectangulo.y = 480;
        if (MathUtils.random(0,10)>5) activoP = 1;
        else activoP = 0;
        dibujarPower = true;
    }
    
    public static Lluvia getLluvia(ObjetosFactory crear) {
        if (instance == null) {
            instance = new Lluvia(crear);
        }
        return instance;
    }

	public int getVelInicial() {
		return velInicial;
	}
	
	public long getLastDropTime() {
		return lastDropTime;
	}

	public void setVelInicial(int velInicial) {
		this.velInicial = velInicial;
	}

	public void setLastDropTime(long lastDropTime) {
		this.lastDropTime = lastDropTime;
	}

	public void setVelocidad(double velY2) {
        this.velY2 *= velY2;
    }
	
    // Método para incrementar la velocidad en función del puntaje
    public void incrementoVelocidadFuncionPuntaje(Tarro tarro) {
        velYFuncionPuntaje += 0.01 * 0.75;
    }

    // Método para iniciar la lluvia y reproducir la música
    public void crear(int crear) {
    	velC = crear;
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
		if(MathUtils.random(0,10) < 5) 
			gotasB.add(crear.crearGotaBuena((int)velC));
		else 
			gotasM.add(crear.crearGotaMala((int)velC));
		
	    
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
    	long currentTimePW = TimeUtils.millis();
        float delta = Gdx.graphics.getDeltaTime();
        
        // Si ha pasado suficiente tiempo, se crea un nuevo power-up
        if (currentTimePW - lastDropTimePower > (tiempoEntrePowerUps * 1000)) {
            crearPW();
            lastDropTimePower = currentTimePW;
        }

        if (dibujarPower) {
            // Mueve el power-up hacia arriba
            rectangulo.y -= velC * delta;
            // Si el power-up está fuera de la pantalla, se elimina
            if (rectangulo.y + 64 < 0) {
                dibujarPower = false;
                //return null;
            }
            // Si el power-up se superpone con el tarro, se activa
            if (rectangulo.overlaps(tarro.getArea()) && dibujarPower) {
                if (activoP == 0) powerDown.aplicarPowerDown(tarro);
                else powerUp.aplicarPowerUp(tarro);

                inicioPower.play();
                tiempoActivadoPowerUp = TimeUtils.millis();
                tiempoTranscurrido = 0;
                dibujarPower = false;
                //cambio = powerUpCurrent.aplicarPowerUp();
                //powerUpActivo = powerUpCurrent;
            }
        }

        // Si hay un power-up activo, se controla su duración y efecto
        if (tiempoActivadoPowerUp > 0) {
            tiempoTranscurrido += delta * 1000;
            
            // Si ha pasado suficiente tiempo, se desactiva el power-up
            if (tiempoTranscurrido >= duracionPowerUp * 1000) {
            	finPower.play();
            	if (activoP == 0) powerDown.quitarPowerDown(tarro);
            	else powerUp.quitarPowerUp(tarro);
                tiempoActivadoPowerUp = 0;
                tiempoTranscurrido = 0;
            }
        }
    	
    	
        // generar gotas de lluvia
        if (TimeUtils.nanoTime() - lastDropTime > 100000000 / velYFuncionPuntaje) crearGotaDeLluvia();
        
        // revisar si las gotas cayeron al suelo o chocaron con el tarro
        for (int i = 0; i < gotasB.size; i++) {
            GotaBuena gotaActual = gotasB.get(i);

            float ponderadorVelocidad = velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            int accionARealizar = gotaActual.verificarColisionTarro(tarro, ponderadorVelocidad);

            if (accionARealizar != 0) {
                if (accionARealizar == 1)
                    incrementoVelocidadFuncionPuntaje(tarro);

                gotaActual.destruir();
                gotasB.removeIndex(i);

                if (accionARealizar == -1)
                    return false;
            }
        }
        
        for (int i = 0; i < gotasM.size; i++) {
            GotaMala gotaActual = gotasM.get(i);

            float ponderadorVelocidad = velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            int accionARealizar = gotaActual.verificarColisionTarro(tarro, ponderadorVelocidad);

            if (accionARealizar != 0) {
                if (accionARealizar == 1)
                    incrementoVelocidadFuncionPuntaje(tarro);

                gotaActual.destruir();
                gotasM.removeIndex(i);

                if (accionARealizar == -1)
                    return false;
            }
        }
        
        return true;
    }
	
   /*Método para actualizar el dibujo de la lluvia
   Dibuja todas las gotas de lluvia presentes en el conjunto de gotas
   utilizando un objeto SpriteBatch para el dibujo*/
    public void actualizarDibujoLluvia(SpriteBatch batch,BitmapFont font,IdiomaStrategy idioma) {
        for (int i = 0; i < gotasB.size; i++) {
            GotaBuena gotaActual = gotasB.get(i);
            gotaActual.dibujarGota(batch);
        }
        
        for (int i = 0; i < gotasM.size; i++) {
            GotaMala gotaActual = gotasM.get(i);
            gotaActual.dibujarGota(batch);
        }
        
        if (dibujarPower) {
        	
        	if (activoP == 0) powerDown.dibujar(batch, rectangulo.x, rectangulo.y);
        	else powerUp.dibujar(batch, rectangulo.x, rectangulo.y);
        	
        }

        if (tiempoActivadoPowerUp > 0) {
            // Muestra el tiempo restante de un power-up activo
            float tiempoRestante = duracionPowerUp * 1000 - tiempoTranscurrido;
            if (tiempoRestante > 0) idioma.idiomaTiempoPower(batch, font, (tiempoRestante / 1000));
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
        powerUp.destruir();
        powerDown.destruir();
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    
    }

	public void reset(Tarro tarro) {
		velY2 = 1;
		velYFuncionPuntaje = 1;
		gotasB.clear(); 
		gotasM.clear();
		
		if (tiempoTranscurrido != 0) {
        	if (activoP == 0) powerDown.quitarPowerDown(tarro);
        	else powerUp.quitarPowerUp(tarro);
            tiempoActivadoPowerUp = 0;
            tiempoTranscurrido = 0;
        }
		
	}
}