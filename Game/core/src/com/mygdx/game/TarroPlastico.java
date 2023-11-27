package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input;

public class TarroPlastico implements Tarro {

    private Rectangle bucket;
    private Texture bucketImage;
    private Sound sonidoHerido;
    
    private int tamañox;
    private int tamañoy;
    private int vidas;
    private int puntos = 0;
    private double multiplicadorAumentoPuntos;
    private int velx;
    private double multiplicadorVel;
    private int tiempoHerido;

    private boolean herido;
    private boolean esInmortal;

    public TarroPlastico() {
        tamañox = 49;
        tamañoy = 49;
        herido = false;
        
        bucketImage = new Texture(Gdx.files.internal("cubetaPlastico.png"));
        velx = 475;
        multiplicadorVel = 1;
        multiplicadorAumentoPuntos = 1;
        
        sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
        
        vidas = 4;
      
        crearTarro();
    }

    public void sumarPuntos(int pp) {
        puntos += (pp * multiplicadorAumentoPuntos);
    }

    public void crearTarro() {
        bucket = new Rectangle();
        bucket.x = 800 / 2 - tamañox / 2;
        bucket.y = 20;
        bucket.width = tamañox;
        bucket.height = tamañoy;
    }

    public void setMultiplicadorVel(double multiplicadorVel) {
        this.multiplicadorVel = multiplicadorVel;
    }

    public int getVelocidad() {
        return velx;
    }

    public void setTamañox(int tamañox) {
        this.tamañox = tamañox;
    }

    public void setTamañoy(int tamañoy) {
        this.tamañoy = tamañoy;
    }

    public void setAumentoPuntos(double multiplicadorAumentoPuntos) {
        this.multiplicadorAumentoPuntos = multiplicadorAumentoPuntos;
    }
    
    public void setEsInmortal(boolean esInmortal) {
    	this.esInmortal = esInmortal;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return bucket;
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido) {
            batch.draw(bucketImage, bucket.x, bucket.y);
        } else {
            batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5, 5));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    public void setVelocidad(int velx) {
        this.velx = velx;
    }

    public void setTiempoHerido(int tiempoHerido) {
        this.tiempoHerido = tiempoHerido;
    }

    public void setHerido(boolean herido) {
        this.herido = herido;
    }

    public boolean actualizarMovimiento() {
        if (!herido) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= (velx * multiplicadorVel) * Gdx.graphics.getDeltaTime();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += (velx * multiplicadorVel) * Gdx.graphics.getDeltaTime();

            if (bucket.x < 0) bucket.x = 0;
            if (bucket.x > 800 - tamañox) bucket.x = 800 - tamañox;
            return true;
        }
        return false;
    }

    public void dañar(int daño) {
    	if(!esInmortal) {
	    	sumarVidas(-daño);
	        setHerido(true);
	        setTiempoHerido(50);
	        sonidoHeridoPlay();
    	}
    }

    public void sonidoHeridoPlay() {
        sonidoHerido.play();
    }

    public void sumarVidas(int vidas) {
        this.vidas += vidas;
    }

    public void destruir() {
        bucketImage.dispose();
    }

    public boolean estaHerido() {
        return herido;
    }
    
    public boolean estaMuerto() {
    	if (vidas <= 0)return true;
    	return false;
    }

    public void reset() {
        vidas = 4;
        puntos = 0;
        velx = 475;
        herido = false;
    }
}
