package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Gota> gotas;
    private long lastDropTime;
    private Music rainMusic;
    private float velY2;
	private float velYFuncionPuntaje;
	private int dificultad;
	
	public Lluvia(int dificultad) {
		gotas = new Array<Gota>();
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		velY2 = 1;
		velYFuncionPuntaje = 1;
		this.dificultad = dificultad;
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
		crearGotaDeLluvia();
	    // start the playback of the background music immediately
	    rainMusic.setLooping(true);
	    rainMusic.play();
	}
	
	public void crearGotaDeLluvia() {
		Gota nuevaGota = null;
	      int azar = MathUtils.random(1,10);
	      
	      if (dificultad == 1) {
		      if (azar < 2)
			    	 nuevaGota = new GotaMala();
			  else {
			    	if (azar < 7)
			    		 nuevaGota = new GotaAzul();
			    	else
			    		 nuevaGota = new GotaAmarilla();
			      }
	      } else {
	    	  if (dificultad == 2) {
			      if (azar < 4)
				    	 nuevaGota = new GotaMala();
				   else {
				    	if (azar < 8)
				    	     nuevaGota = new GotaAzul();
				    	else
				    		 nuevaGota = new GotaAmarilla();
				        }
		      } else {
			      if (azar < 7)
				    	 nuevaGota = new GotaMala();
				      else {
				    	 if (azar < 9)
				    		 nuevaGota = new GotaAzul();
				    	 else
				    		 nuevaGota = new GotaAmarilla();
				      }
		      }
	      }
	      
	      
	      if (nuevaGota != null)
	    	  gotas.add(nuevaGota);
	      lastDropTime = TimeUtils.nanoTime();
	}

	public boolean actualizarMovimiento(Tarro tarro) { 
	   // generar gotas de lluvia
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000 / velYFuncionPuntaje) crearGotaDeLluvia();
	   
	   // revisar si las gotas cayeron al suelo o chocaron con el tarro
	   for (int i = 0; i < gotas.size; i++ ) { 
		  Gota gotaActual = gotas.get(i);
		  float velocidadActual = gotaActual.getVelocidadCaida();
		  float posActual = gotaActual.getFormaPosY();
		  float nuevaPos = posActual - velocidadActual * velY2 * velYFuncionPuntaje * Gdx.graphics.getDeltaTime();
		  gotaActual.setFormaPosY(nuevaPos);
		  if(gotaActual.getFormaPosY() + 64 < 0) {
			  gotaActual.destruir();
		  	  gotas.removeIndex(i);
		  }
		  
		  int accionARealizar = gotaActual.verificarColisionTarro(tarro);
	      if(accionARealizar != 0){
	    	incrementoVelocidadFuncionPuntaje(tarro);
	      	gotaActual.destruir();
	      	gotas.removeIndex(i);
	      	if (accionARealizar == -1)
	      		return false;
	      }
	  }
	  return true;
   }
   
   public void actualizarDibujoLluvia(SpriteBatch batch) {
	  for (int i=0; i < gotas.size; i++ ) {
		  Gota gotaActual = gotas.get(i);
		  gotaActual.dibujarGota(batch);
	  }
   }
   
   public double getVelocidadLluvia() {
       return velY2 * velYFuncionPuntaje;
   }
   
   public boolean hayMusica() {
	   return rainMusic.isPlaying();
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
