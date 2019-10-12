package testVolleyball;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Spectator;

class testSpectator {

	//Class
	private Spectator spectator;
	
	//Scene
	private void setUpSceneSpectatorEmpty(){
		this.spectator=new Spectator("6","Esteban", "Ariza","acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar(2000, 8, 9));
	}
	
	private void setUpSceneSpectatorNormal(){
		this.spectator=new Spectator("6","Esteban", "Ariza","acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar(2000, 8, 9));
		Spectator s1=new Spectator("5", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s2=new Spectator("7", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		spectator.setAlpha(s1);
		spectator.setOmega(s2);
	}
	
	private void setUpSceneCompetitorNormal(){
		this.spectator=new Spectator("6","Esteban", "Ariza","acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar(2000, 8, 9));
		Spectator s1=new Spectator("5", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s2=new Spectator("7", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		spectator.setOmega(s1);//s0->s1
		s1.setAlpha(spectator);//s0<-s1
		s1.setOmega(s2);//s1->s2
		s2.setAlpha(s1);//s1<-s2
		
	}
	
	//Test
	@Test
	void testAddSpectator(){
		setUpSceneSpectatorEmpty();
		Spectator s1=new Spectator("7", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s2=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		Spectator s3=new Spectator("6", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		
		assertTrue(spectator.addSpectator(s1));
		assertTrue(spectator.addSpectator(s2));
		assertFalse(spectator.addSpectator(s3));
		
		assertEquals(spectator.getOmega().getId(), "7");
		assertEquals(spectator.getAlpha().getId(), "5");
	}
	
	@Test
	void testSearchSpectator(){
		setUpSceneSpectatorNormal();
		
		assertNotNull(spectator.searchSpectator("6"));
		assertNotNull(spectator.searchSpectator("5"));
		assertNotNull(spectator.searchSpectator("7"));
		assertNull(spectator.searchSpectator("4"));
	}
	
	@Test
	void testSearchCompetitor(){
		setUpSceneCompetitorNormal();
		
		assertNotNull(spectator.searchCompetitor("6"));
		assertNotNull(spectator.searchCompetitor("5"));
		assertNotNull(spectator.searchCompetitor("7"));
		assertNull(spectator.searchCompetitor("4"));
	}

}
