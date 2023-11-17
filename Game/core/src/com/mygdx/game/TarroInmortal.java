package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TarroInmortal extends Tarro {

	   
	   public TarroInmortal() {
		   super();
		   setTamañox(49);
		   setTamañoy(49);
		   setTextura(new Texture(Gdx.files.internal("bucket.png")));
		   setVelocidad(475);
		   setMultiplicadorVel(1);
		   setAumentoPuntos(1);
		   setBucket();
		   setEsInmortal(true);
		   
	   }
	   
		public void sumarPuntos(int pp) {
			super.sumarPuntos(pp);
		}
		
}
