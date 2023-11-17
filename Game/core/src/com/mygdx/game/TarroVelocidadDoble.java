package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TarroVelocidadDoble extends Tarro {

	   
	   public TarroVelocidadDoble() {
		   super();
		   setTamañox(49);
		   setTamañoy(49);
		   setTextura(new Texture(Gdx.files.internal("bucket.png")));
		   setVelocidad(475);
		   setMultiplicadorVel(2);
		   setAumentoPuntos(1);
		   setBucket();
		   setEsInmortal(false);
		   
	   }
	   
		public void sumarPuntos(int pp) {
			super.sumarPuntos(pp);
		}
		

}
