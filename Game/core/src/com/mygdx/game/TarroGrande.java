package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TarroGrande extends Tarro {

	   
	   public TarroGrande() {
		   super();
		   setTamañox(80);
		   setTamañoy(80);
		   setTextura(new Texture(Gdx.files.internal("bucketGrande.png")));
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
