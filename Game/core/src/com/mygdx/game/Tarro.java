package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public abstract class Tarro {
	   private Rectangle bucket;
	   private Texture bucketImage;
	   private int tamañox;
	   private int tamañoy;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int multiplicadorAumentoPuntos;
	   private int velx;
	   private int multiplicadorVel;
	   
	   //private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   private boolean herido = false;
	   private boolean esInmortal;
	  
	   
	   public Tarro() {

		   sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	   }
	   

	   public void setBucket() {
		   bucket = new Rectangle();
		   bucket.x = 800 / 2 - tamañox / 2;
		   bucket.y = 20;
		   bucket.width = tamañox;
		   bucket.height = tamañoy;  
	   }
	   
	   
	   public void setMultiplicadorVel(int multiplicadorVel) {
		   this.multiplicadorVel = multiplicadorVel;
	   }
	   
	   public void setTamañox(int tamañox) {
		   this.tamañox = tamañox;
	   }
	   
	   public void setTamañoy(int tamañoy) {
		   this.tamañoy = tamañoy;
	   }
	   
	   public void setTextura(Texture textura) {
		   bucketImage = textura;
	   }
	   
	   public void setVidas(int vidas) {
		   this.vidas = vidas;
	   }
	   
	   public void setEsInmortal(boolean esInmortal) {
		   this.esInmortal = esInmortal;
		   
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
		
		public void sumarPuntos(int pp) {
			puntos += (pp * multiplicadorAumentoPuntos);
		}
		
		public void setAumentoPuntos(int multiplicadorAumentoPuntos) {
			this.multiplicadorAumentoPuntos = multiplicadorAumentoPuntos;
		}
		
		
	   //public abstract void dañar();
	   
	   public void dibujar(SpriteBatch batch) {
		   if (!herido) {
			   batch.draw(bucketImage, bucket.x, bucket.y);
		   }
		   else {
			   batch.draw(bucketImage, bucket.x, bucket.y + MathUtils.random(-5,5));
			   tiempoHerido--;
			   if (tiempoHerido <= 0) herido = false;
		   }
		   
	   }
	   
	   public void setVelocidad(int velx) {
		   this.velx = velx;
	   }
	   
	   public int getVelocidad() {
		   return velx;
	   }
	   
	   public void setTiempoHerido(int tiempoHerido) {
		   this.tiempoHerido = tiempoHerido;
	   }
	   
	   public void setHerido(boolean herido) {
		   this.herido = herido;
	   }
	   
	   public boolean actualizarMovimiento() {  
		   if(!herido) {
			   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= (velx * multiplicadorVel ) * Gdx.graphics.getDeltaTime();
			   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += (velx * multiplicadorVel) * Gdx.graphics.getDeltaTime();
			   
			   // que no se salga de los bordes izq y der
			   if(bucket.x < 0) bucket.x = 0;
			   if(bucket.x > 800 - tamañox) bucket.x = 800 - tamañox;
			   return true;
		   }
		   return false; 
	 }
	   
	  public void dañar() {
		  if(!esInmortal) {
			  sumarVidas(-1);
			  setHerido(true);
			  setTiempoHerido(50);
			  //tiempoHerido=tiempoHeridoMax;
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

	public void reset() {
		vidas = 3;
		puntos = 0;
		velx = 475;
		herido = false;
		
	}
}
