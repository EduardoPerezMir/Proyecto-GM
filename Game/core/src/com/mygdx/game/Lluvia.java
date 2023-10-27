package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Gota> gotas;
    private long lastDropTime;
    private Music rainMusic;
    private double velY2;
	private double velYFuncionPuntaje;
   
	public Lluvia(Music mm) {
		rainMusic = mm;
		velY2 = 1;
		velYFuncionPuntaje = 1;
	}
	
	public void setVelocidad(double velY2)
	{
		this.velY2 *= velY2;
	}
	
	public void incrementoVelocidadFuncionPuntaje(Tarro tarro)
	{
		velYFuncionPuntaje += 0.01 * 0.75;
	}
	
	public void crear() {
		gotas = new Array<Gota>();
		crearGotaDeLluvia();
	    // start the playback of the background music immediately
	    rainMusic.setLooping(true);
	    rainMusic.play();
	}
	
	private void crearGotaDeLluvia() {
	      Gota nuevaGota;
	      int azar = MathUtils.random(1,10);
	      
	      if (azar < 4)
	    	 nuevaGota = new GotaMala();
	      else {
	    	 if (azar < 9)
	    		 nuevaGota = new GotaAzul();
	    	 else
	    		 nuevaGota = new GotaAmarilla();
	      }
	      
	      gotas.add(nuevaGota);
	      lastDropTime = TimeUtils.nanoTime();
	   }

public boolean actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();
	   
	   boolean flag = true;
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i = 0; i < gotas.size; i++ ) {
		  Gota gotaActual = gotas.get(i);
		  Rectangle raindrop = gotaActual.getForma();
	      raindrop.y -= gotaActual.getVelocidadCaida() * velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
	      gotaActual.setForma(raindrop);
	      gotas.set(i, gotaActual);
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
	    	  gotas.removeIndex(i);
	      }
	      
	      if(raindrop.overlaps(tarro.getArea())){//la gota choca con el tarro
	    	flag = gotaActual.accionColisionTarro(tarro);
	    	incrementoVelocidadFuncionPuntaje(tarro);
	      	if (flag == false)
	      		return false;
	    	gotas.removeIndex(i);
	      }
	    }
	  return true;
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) {
	  for (int i=0; i < gotas.size; i++ ) {
		  Gota gotaActual = gotas.get(i);
		  Rectangle raindrop = gotaActual.getForma();
		  Texture textura = gotaActual.getTextura();
		  batch.draw(textura, raindrop.x, raindrop.y);
	  }
   }
   
   public void destruir() {
      rainMusic.dispose();
   }
   
   public void pausar() {
	  rainMusic.stop();
   }
   
   public void continuar() {
	  rainMusic.play();
   }
}
