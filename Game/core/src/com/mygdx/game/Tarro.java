package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public interface Tarro {

	public void dibujar(SpriteBatch batch);

    public boolean actualizarMovimiento();
    
    public void sumarPuntos(int puntos);
    
    public int getVidas();

    public int getPuntos();
    
    public void setAumentoPuntos(double multiplicadorAumentoPuntos);
    
    public void setMultiplicadorVel(double d);
    
    public void setEsInmortal(boolean inmortal);
    
    public Rectangle getArea();
    
    public void dañar(int daño);
    
    public void destruir();
    
    public boolean estaMuerto();
    
    public void reset();
    
    public void setVelocidad(int velx);

}
