package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Tarro {
		
	   private Rectangle bucket;
	   private Texture bucketImage;
	   
	   private Texture bucketImageGrande;
	   private int tamaño=64;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   private boolean esInmortal=false;
	   private boolean seDebeAumentar=false;
	   private boolean esTarroGrande=false;
	   
	   public Tarro(Texture tarro, Sound ss,Texture tarroGrande) {
		   bucketImage = tarro;
		   bucketImageGrande = tarroGrande;
		   sonidoHerido = ss;
	   }
	   
	   public void tamañoTarroGrande(boolean variable) {
		   esTarroGrande = variable;
		   if (variable) {
			   tamaño=100;
			   bucket.x = 800 / 2 - tamaño / 2;
			   bucket.y = 20;
			   bucket.width = tamaño;
			   bucket.height = tamaño;
		   }
		   else {
			   tamaño=64;
			   bucket.x = 800 / 2 - tamaño / 2;
			   bucket.y = 20;
			   bucket.width = tamaño;
			   bucket.height = tamaño;
		   }
		   
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
			if (seDebeAumentar)puntos+=(pp*2);
			else puntos+=pp;
		}
		
	   public void crear() {
		      bucket = new Rectangle();
		      bucket.x = 800 / 2 - tamaño / 2;
		      bucket.y = 20;
		      bucket.width = tamaño;
		      bucket.height = tamaño;
		      
		      
	   }
	   public void dañar() {
		   if (!esInmortal) {
			   vidas--;
			   herido = true;
			   tiempoHerido=tiempoHeridoMax;
			   sonidoHerido.play(); 
		   }
	   }
	   public void dibujar(SpriteBatch batch) {
		   if(!esTarroGrande) {
			   if (!herido)  
				   batch.draw(bucketImage, bucket.x, bucket.y);
			   else {
				   batch.draw(bucketImage, bucket.x, bucket.y+ MathUtils.random(-5,5));
				   tiempoHerido--;
				   if (tiempoHerido<=0) herido = false;
			   }
		   }
		   else {
			   if (!herido)  
				   batch.draw(bucketImageGrande, bucket.x, bucket.y);
			   else {
				   batch.draw(bucketImageGrande, bucket.x, bucket.y+ MathUtils.random(-5,5));
				   tiempoHerido--;
				   if (tiempoHerido<=0) herido = false;
			   }
		   }
		
	   } 
	   
	   public void setInmortal(boolean inmortal) {
		   esInmortal = inmortal;
	   }
	   public void setVelocidad(double velo) {
		   velx*=velo;
	   }
	   
	   public void setAumentoPuntos(boolean variable) {
		   seDebeAumentar = variable;
	   }
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - tamaño / 2;
			}*/
		   //movimiento desde teclado
		   
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(bucket.x < 0) bucket.x = 0;
		   if(bucket.x > 800 - tamaño) bucket.x = 800 - tamaño;
	   }
	    

	public void destruir() {
		    bucketImage.dispose();
		    bucketImageGrande.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
