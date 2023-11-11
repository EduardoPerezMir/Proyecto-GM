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
	   private int tamaño=49;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 475;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   private boolean esInmortal = false;
	   private boolean seDebeAumentar = false;
	   
	   private Texture tarroActivo;
	   
	   private static Tarro instance;
	   
	   private Tarro() {
		   
		   bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		   bucketImageGrande = new Texture(Gdx.files.internal("bucketgrande.png"));
		   tarroActivo = bucketImage;
		   sonidoHerido = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
	   }
	   
	   public static Tarro getTarro() {
	        if (instance == null) {
	            instance = new Tarro();
	        }
	        return instance;
	    }
	   
	   public void tamañoTarroGrande(boolean variable) {
		   if (variable) {
			   tamaño=80;
			   tarroActivo = bucketImageGrande;
		   }
		   else {
			   tamaño=49;
			   tarroActivo = bucketImage;
		   }
		   bucket.width = tamaño;
		   bucket.height = tamaño;
		   
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
		   if (!herido) {
			   batch.draw(tarroActivo, bucket.x, bucket.y);
		   }
		   else {
			   batch.draw(tarroActivo, bucket.x, bucket.y + MathUtils.random(-5,5));
			   tiempoHerido--;
			   if (tiempoHerido <= 0) herido = false;
		   }
	   }
	   
	   public void setInmortal(boolean inmortal) {
		   esInmortal = inmortal;
	   }
	   public void multiplicarVelocidad(double velo) {
		   velx*=velo;
	   }
	   
	   public void setVelocidad(int velx) {
		   this.velx = velx;
	   }
	   
	   public int getVelocidad() {
		   return velx;
	   }
	   
	   public void setAumentoPuntos(boolean variable) {
		   seDebeAumentar = variable;
	   }
	   public boolean actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - tamaño / 2;
			}*/
		   
		   //movimiento desde teclado
		   
		   if(!herido) {
			   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
			   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
			   // que no se salga de los bordes izq y der
			   if(bucket.x < 0) bucket.x = 0;
			   if(bucket.x > 800 - tamaño) bucket.x = 800 - tamaño;
			   return true;
		   }
		   return false; 
	   }
	    

	public void destruir() {
		    bucketImage.dispose();
		    bucketImageGrande.dispose();
		    tarroActivo.dispose();
		    instance = null;
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
