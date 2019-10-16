package testVolleyball;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Spectator;

class TestSpectator {

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
	
	//Test
	//BST(Spectator)--------------------------------------------------------------------------------------|
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
	void testDeleteSpectator(){
		setUpSceneSpectatorEmpty();
		assertNull(spectator.deleteSpectator());
		
		setUpSceneSpectatorNormal();
		Spectator s=spectator.deleteSpectator();
		assertTrue((s.getId().equals("5") && s.getOmega()!=null) || (s.getId().equals("7") && s.getAlpha()!=null));
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
	void testShowSpectators(){
		setUpSceneSpectatorNormal();
		
		String text=spectator.showSpectators(0);
		String[] spectators=text.split("\n");
		assertEquals(spectators[0].charAt(1), 'E');
		assertEquals(spectators[1].charAt(5), 'M');
		assertEquals(spectators[2].charAt(5), 'J');
	}
	
	@Test
	void testCountSpectator(){
		setUpSceneSpectatorEmpty();
		assertEquals(spectator.countSpectator(), 1);
		
		setUpSceneSpectatorNormal();
		assertEquals(spectator.countSpectator(), 3);
	}
	
	@Test
	void testGetRandomSpectator(){
		setUpSceneSpectatorNormal();
		Spectator s1=spectator.getRandomSpectator();
		Spectator s2=spectator.getRandomSpectator();
		assertNotEquals(s1, s2);
		
	}
	
	@Test
	void testGetCountrySpectators(){
		setUpSceneSpectatorNormal();
		
		assertEquals(spectator.getCountrySpectators("Colombia").get(0).getId(), "6");
		assertEquals(spectator.getCountrySpectators("Colombia").get(1).getId(), "5");
		assertEquals(spectator.getCountrySpectators("Colombia").get(2).getId(), "7");
		
		assertEquals(spectator.getCountrySpectators("Japon").size(), 0);
	}
	//----------------------------------------------------------------------------------------------------|
	
}
