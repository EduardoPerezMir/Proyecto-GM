package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Rectangle> rainDropsPos;
	private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Texture gotaBuena;
    private Texture gotaMala;
    private Texture gotaAmarilla;
    private Sound dropSound;
    private Music rainMusic;
    private int velY=300;
	   
	public Lluvia(Texture gotaBuena, Texture gotaAmarilla, Texture gotaMala, Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
		this.gotaBuena = gotaBuena;
		this.gotaAmarilla = gotaAmarilla;
		this.gotaMala = gotaMala;
	}
	
	public void setVelocidad(double velo) {
		   velY*=velo;
	   }
	
	public void crear() {
		rainDropsPos = new Array<Rectangle>();
		rainDropsType = new Array<Integer>();
		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}
	
	private void crearGotaDeLluvia() {
	      Rectangle raindrop = new Rectangle();
	      raindrop.x = MathUtils.random(0, 800-42);
	      raindrop.y = 480;
	      raindrop.width = 42;
	      raindrop.height = 64;
	      rainDropsPos.add(raindrop);
	      // ver el tipo de gota
	      int azar = MathUtils.random(1,10);
	      if (azar < 4)	    	  
	         rainDropsType.add(1);
	      else {
	    	 if (azar < 9)
	    		 rainDropsType.add(2);
	    	 else
	    		 rainDropsType.add(3);
	      }
	      lastDropTime = TimeUtils.nanoTime();
	   }
	
   public boolean actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia 
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	  
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.y -= velY * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  rainDropsPos.removeIndex(i); 
	    	  rainDropsType.removeIndex(i);
	      }
	      if(raindrop.overlaps(tarro.getArea())) { //la gota choca con el tarro
	    	int tipo = rainDropsType.get(i);
	    	if(rainDropsType.get(i)==1) { // gota dañina
	    	  tarro.dañar();
	    	  if (tarro.getVidas()<=0)
	    		 return false; // si se queda sin vidas retorna falso /game over
	    	  rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}else { // gota a recolectar
	      	  if (tipo == 2)
	      		  tarro.sumarPuntos(10);
	      	  else
	      		tarro.sumarPuntos(30);
	          dropSound.play();
	          rainDropsPos.removeIndex(i);
	          rainDropsType.removeIndex(i);
	      	}
	      }
	   } 
	  return true; 
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) { 
	   
	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  int tipo = rainDropsType.get(i);
		  if(tipo==1) // gota dañina
	         batch.draw(gotaMala, raindrop.x, raindrop.y); 
		  else {
			 if(tipo == 2)
				 batch.draw(gotaBuena, raindrop.x, raindrop.y); 
			 else
				 batch.draw(gotaAmarilla, raindrop.x, raindrop.y); 
		  }
	   }
   }
   public void destruir() {
      dropSound.dispose();
      rainMusic.dispose();
   }
   public void pausar() {
	  rainMusic.stop();
   }
   public void continuar() {
	  rainMusic.play();
   }
   
}
