package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public abstract class NivelDificultad {
    private long lastDropTime; // Registro del tiempo de la última gota creada
    private Music rainMusic; // Música de fondo para la lluvia
    private float velY2; // Velocidad de caída de las gotas
    private float velYFuncionPuntaje; // Variable de velocidad modificada por el puntaje
    private int velInicial;
    private ObjetosFactory crear;
    private Tarro tarro;
    
    //powers
    private long lastDropTimePower;
    private Sound inicioPower;
    private Sound finPower;
    
    private float tiempoEntrePowerUps; // Tiempo en segundos entre PowerUps
    private float tiempoActivadoPowerUp; // Tiempo de activación del power-up
    private float tiempoTranscurrido;
    private float duracionPowerUp;
    
    //private PowerUp powerUpActivo; // Almacena el power-up activo
    //private PowerUp powerUpCurrent;// Almacena el power-up mostrado en pantalla
    private Rectangle rectangulo;
    
    private PowerUp powerUp;
    private PowerDown powerDown;
    private int activoP;
    private boolean dibujarPower = false;
    
    // Constructor
	public NivelDificultad(ObjetosFactory crear, Tarro tarro) {
		velY2 = 1;
		velYFuncionPuntaje = 1;
		duracionPowerUp = 7.0f;
		tiempoTranscurrido = 0;
		tiempoEntrePowerUps = 12.0f;
		tiempoActivadoPowerUp = 0;
		activoP = -1;
		this.crear = crear;
		this.tarro = tarro;
	}
	
	// Getters & Setters
	public int getVelInicial() {
		return velInicial;
	}
	
	public long getLastDropTime() {
		return lastDropTime;
	}
	
    public Tarro getTarro() {
    	return tarro;
    }
    
	public double getVelocidadLluvia() {
        return velY2 * velYFuncionPuntaje;
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
	
	public void incrementoVelocidadFuncionPuntaje(Tarro tarro) {
        velYFuncionPuntaje += 0.01 * 0.75;
    }
	
	// Template Method 1
	public void crearNivel(GameScreen gameScreen){
		crearArrayGotas();
		disponerSonidos();
		crearPowers();
		setVelTarroAcordeNivel(tarro);
		setBackgroundTextureGame(gameScreen);
	}
	
	public void disponerSonidos() {
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		this.inicioPower = Gdx.audio.newSound(Gdx.files.internal("soundinmortal.mp3"));
        this.finPower = Gdx.audio.newSound(Gdx.files.internal("endpower.mp3"));
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
    
    public abstract void setVelTarroAcordeNivel(Tarro tarro);
	public abstract void setBackgroundTextureGame(GameScreen gameScreen);
	
	// Template Method 2
	public boolean disponerNivel(SpriteBatch batch, BitmapFont font, IdiomaStrategy idioma)
	{
		tarro.actualizarMovimiento();
		long currentTimePW = TimeUtils.millis();
        float delta = Gdx.graphics.getDeltaTime();
        
        // Si ha pasado suficiente tiempo, se crea un nuevo power-up
        if (currentTimePW - lastDropTimePower > (tiempoEntrePowerUps * 1000)) {
            crearPW();
            lastDropTimePower = currentTimePW;
        }
        
        if (dibujarPower){
        	if (activoP != 0)
        		powerUp.dibujar(batch, rectangulo.x, rectangulo.y);
    		else
        		powerDown.dibujar(batch, rectangulo.x, rectangulo.y);
            // Mueve el power-up hacia arriba
            rectangulo.y -= 200 * delta;
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
        	float tiempoRestante = duracionPowerUp * 1000 - tiempoTranscurrido;
            if (tiempoRestante > 0) idioma.idiomaTiempoPower(batch, font, (tiempoRestante / 1000));
            
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
        if (TimeUtils.nanoTime() - lastDropTime > 100000000 / velYFuncionPuntaje) 
        {
        	crearGotaDeLluvia(crear);
        	lastDropTime = TimeUtils.nanoTime();
       	}
        
        int i = 0;
        int cantGotasBuenas = getCantGotasBuenas();
        while (i < cantGotasBuenas)
        {
            GotaBuena gotaActual = getGotaBuenaActual(i);
            gotaActual.dibujarGota(batch);
            float ponderadorVelocidad = velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            int accionARealizar = gotaActual.verificarColisionTarro(tarro, ponderadorVelocidad);

            if (accionARealizar != 0) {
            	borrarGotaBuenaActual(gotaActual);
            	gotaActual.destruir();
                if (accionARealizar == 1)
                    incrementoVelocidadFuncionPuntaje(tarro);

                if (accionARealizar == -1)
                    return false;
            }
            i++;
            cantGotasBuenas = getCantGotasBuenas();
        }
        
        i = 0;
        int cantGotasMalas = getCantGotasMalas();
        while (i < cantGotasMalas)
        {
        	GotaMala gotaActual = getGotaMalaActual(i);
        	gotaActual.dibujarGota(batch);
            float ponderadorVelocidad = velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
            int accionARealizar = gotaActual.verificarColisionTarro(tarro, ponderadorVelocidad);

            if (accionARealizar != 0) {
            	borrarGotaMalaActual(gotaActual);
                if (accionARealizar == 1)
                    incrementoVelocidadFuncionPuntaje(tarro);
                
                if (accionARealizar == -1)
                    return false;
                
            }
            i++;
            cantGotasMalas = getCantGotasMalas();
        }
        
        return true;
	}
	

	
    public boolean hayMusica() {
        return rainMusic.isPlaying();
    }

    public void destruir() {
        rainMusic.dispose();
        powerUp.destruir();
        powerDown.destruir();
        borrarArrayGotas();
    }

    public void pausar() {
        rainMusic.stop();
    }

    public void continuar() {
        rainMusic.play();
    }

	public void reset() {
		velY2 = 1;
		velYFuncionPuntaje = 1;
		tarro.reset();
		if (tiempoTranscurrido != 0) {
        	if (activoP == 0) powerDown.quitarPowerDown(tarro);
        	else powerUp.quitarPowerUp(tarro);
            tiempoActivadoPowerUp = 0;
            tiempoTranscurrido = 0;
        }
	}
	
	public abstract void crearGotaDeLluvia(ObjetosFactory crear);
	public abstract void crearArrayGotas();
	public abstract GotaBuena getGotaBuenaActual(int i);
	public abstract GotaMala getGotaMalaActual(int i);
	public abstract int getCantGotasBuenas();
	public abstract int getCantGotasMalas();
	public abstract void borrarGotaBuenaActual(GotaBuena gota);
	public abstract void borrarGotaMalaActual(GotaMala gota);
	public abstract void borrarArrayGotas();
}
