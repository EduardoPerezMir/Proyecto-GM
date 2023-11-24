package com.mygdx.game;

public interface ObjetosFactory {
	public GotaBuena crearGotaBuena(int velInicialLluvia);
	public GotaMala crearGotaMala(int velInicialLluvia);
	public PowerDown crearPowerDown();
	public PowerUp crearPowerUp();
	public Tarro crearTarro();
}
