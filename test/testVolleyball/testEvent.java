package testVolleyball;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Event;
import model.Spectator;

class testEvent {

	//Class
	private Event event;
	
	//Scene
	private void setUpSceneEventEmpty(){
		this.event=new Event();
	}
	
	private void setUpSceneEventNormal(){
		this.event=new Event();
		
		Spectator s1=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s2=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s3=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		s1.setAlpha(s2);//s1->s2
		s1.setOmega(s3);//s1->s3
		event.setRootSpectator(s1);
		
		Spectator s4=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s5=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s6=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		s4.setOmega(s5);//s4->s5
		s5.setAlpha(s4);//s4<-s5
		s5.setOmega(s6);//s5->s6
		s6.setAlpha(s5);//s6<-s5
		event.setFirstCompetitor(s4);
	}
	
	//Test
	//BST(Spectator)--------------------------------------------------------------------------------------|
	@Test
	void testAddSpectator(){
		setUpSceneEventEmpty();
		Spectator s1=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s2=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s3=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		
		assertTrue(event.addSpectator(s1));
		assertTrue(event.addSpectator(s2));
		assertTrue(event.addSpectator(s3));
		assertFalse(event.addSpectator(s1));
		
		assertEquals(event.getRootSpectator().getId(), "3");
		assertEquals(event.getRootSpectator().getAlpha().getId(), "1");
		assertEquals(event.getRootSpectator().getOmega().getId(), "5");
	}
	
	@Test
	void testSearchSpectator(){
		setUpSceneEventEmpty();
		assertNull(event.searchSpectator("ola"));
		
		setUpSceneEventNormal();
		
		assertNotNull(event.searchSpectator("3"));
		assertNotNull(event.searchSpectator("1"));
		assertNotNull(event.searchSpectator("5"));
		assertNull(event.searchSpectator("2"));
	}
	
	@Test
	void testShowCountrySpectators(){
		setUpSceneEventNormal();
		
		String text=event.showCountrySpectators("Colombia");
		String[] spectators=text.split("\n");
		assertEquals(spectators[0].charAt(1), 'E');
		assertEquals(spectators[1].charAt(5), 'M');
		assertEquals(spectators[2].charAt(5), 'J');
	}
	
	@Test
	void testSpectatorSize(){
		setUpSceneEventEmpty();
		assertEquals(event.spectatorSize(), 0);
		
		setUpSceneEventNormal();
		assertEquals(event.spectatorSize(), 3);
	}
	
	@Test
	void testGetRandomSpectator(){
		setUpSceneEventNormal();
		Spectator s1=event.getRandomSpectator();
		Spectator s2=event.getRandomSpectator();
		assertNotEquals(s1, s2);
		
		if(s1.getId().equals("5")){
			assertNull(event.getRootSpectator().getAlpha());
		}
		else if(s1.getId().equals("7")){
			assertNull(event.getRootSpectator().getOmega());
		}
		else{
			if(event.getRootSpectator().getId().equals("5")){
				assertNull(event.getRootSpectator().getAlpha());
			}
			else{
				assertNull(event.getRootSpectator().getOmega());
			}
		}
		
	}
	
	@Test
	void testGetCountrySpectator(){
		setUpSceneEventNormal();
		Spectator s=event.getCountrySpectators("Colombia");
		
		assertEquals(event.getRootSpectator().getId(), "3");
		assertEquals(event.getRootSpectator().getAlpha().getId(), "1");
		assertEquals(event.getRootSpectator().getOmega().getId(), "5");
	}
	//----------------------------------------------------------------------------------------------------|
	
	//List(Competitor)------------------------------------------------------------------------------------|
	@Test
	void addCompetitor(){
		setUpSceneEventEmpty();
		event.addCompetitor(new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar()));
		event.addCompetitor(new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar()));
		event.addCompetitor(new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar()));

		
		assertEquals(event.getFirstCompetitor().getId(), "5");
		assertEquals(event.getFirstCompetitor().getOmega().getId(), "1");
		assertEquals(event.getFirstCompetitor().getOmega().getOmega().getId(), "3");
	}
	
	@Test
	void testSearchCompetitor(){
		setUpSceneEventNormal();
		
		assertNotNull(event.searchCompetitor("3"));
		assertNotNull(event.searchCompetitor("1"));
		assertNotNull(event.searchCompetitor("5"));
		assertNull(event.searchCompetitor("2"));
	}
	
	@Test
	void testShowCountryCompetitors(){
		setUpSceneEventNormal();
		
		String text=event.showCountryCompetitors("Colombia");
		String[] spectators=text.split("\n");
		assertEquals(spectators[0].charAt(1), 'E');
		assertEquals(spectators[1].charAt(1), 'M');
		assertEquals(spectators[2].charAt(1), 'J');
	}
	//----------------------------------------------------------------------------------------------------|
	
	@Test
	void testLoad(){//Que hago?
		setUpSceneEventEmpty();
		event.load("data/1.txt");
		assertEquals(event.getRootSpectator().getId(), "6");
		assertEquals(event.getRootSpectator().getOmega().getId(), "8");
	}

}
