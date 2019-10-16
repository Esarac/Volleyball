package testVolleyball;

import static org.junit.jupiter.api.Assertions.*;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import model.Tourney;
import model.Spectator;

class TestTourney {

	//Class
	private Tourney tourney;
	
	//Scene
	private void setUpSceneTourneyEmpty(){
		this.tourney=new Tourney();
	}
	
	private void setUpSceneTourneyNormal(){
		this.tourney=new Tourney();
		
		Spectator s1=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s2=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s3=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		s1.setAlpha(s2);//s1->s2
		s1.setOmega(s3);//s1->s3
		tourney.setRootSpectator(s1);
		
		Spectator s4=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s5=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s6=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		s4.setOmega(s5);//s4->s5
		s5.setAlpha(s4);//s4<-s5
		s5.setOmega(s6);//s5->s6
		s6.setAlpha(s5);//s6<-s5
		tourney.setFirstCompetitor(s4);
	}
	
	private void setUpSceneTourneyWithoutCompetitors(){
		this.tourney=new Tourney();
		
		Spectator s1=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s2=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s3=new Spectator("2", "Samuel", "Satizabal", "samu.s@hotmail.com", "Male", "Colombia", "ss.png", new GregorianCalendar());
		Spectator s4=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		Spectator s5=new Spectator("4", "Juan", "Sabogal", "epico@yahoo.com", "Male", "Colombia", "js.png", new GregorianCalendar());
		s2.setOmega(s3);//s2->s3
		s1.setAlpha(s2);//s1->s2
		
		s4.setAlpha(s5);//s4->s5
		s1.setOmega(s4);//s1->s3
		tourney.setRootSpectator(s1);
		
	}
	
	//Test
	//BST(Spectator)--------------------------------------------------------------------------------------|
	@Test
	void testAddSpectator(){
		setUpSceneTourneyEmpty();
		Spectator s1=new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar());
		Spectator s2=new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar());
		Spectator s3=new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar());
		
		assertTrue(tourney.addSpectator(s1));
		assertTrue(tourney.addSpectator(s2));
		assertTrue(tourney.addSpectator(s3));
		assertFalse(tourney.addSpectator(s1));
		
		assertEquals(tourney.getRootSpectator().getId(), "3");
		assertEquals(tourney.getRootSpectator().getAlpha().getId(), "1");
		assertEquals(tourney.getRootSpectator().getOmega().getId(), "5");
	}
	
	@Test
	void testSearchSpectator(){
		setUpSceneTourneyEmpty();
		assertNull(tourney.searchSpectator("ola"));
		
		setUpSceneTourneyNormal();
		
		assertNotNull(tourney.searchSpectator("3"));
		assertNotNull(tourney.searchSpectator("1"));
		assertNotNull(tourney.searchSpectator("5"));
		assertNull(tourney.searchSpectator("2"));
	}
	
	@Test
	void testShowCountrySpectators(){
		setUpSceneTourneyNormal();
		
		String text=tourney.showCountrySpectators("Colombia");
		String[] spectators=text.split("\n");
		assertEquals(spectators[0].charAt(1), 'E');
		assertEquals(spectators[1].charAt(5), 'M');
		assertEquals(spectators[2].charAt(5), 'J');
	}
	
	@Test
	void testLoadSpectators(){
		setUpSceneTourneyEmpty();
		assertFalse(tourney.loadSpectators("LoLXd"));
		tourney.loadSpectators("dat/test.txt");
		assertEquals(tourney.getRootSpectator().getId(), "6");
		assertEquals(tourney.spectatorSize(), 7);
	}
	
	@Test
	void testSpectatorSize(){
		setUpSceneTourneyEmpty();
		assertEquals(tourney.spectatorSize(), 0);
		
		setUpSceneTourneyNormal();
		assertEquals(tourney.spectatorSize(), 3);
	}
	
	@Test
	void testGetRandomSpectator(){
		setUpSceneTourneyNormal();
		Spectator s1=tourney.getRandomSpectator();
		Spectator s2=tourney.getRandomSpectator();
		assertNotEquals(s1, s2);
		assertEquals(tourney.spectatorSize(), 1);
		
	}
	
	@Test
	void testGetCountrySpectators(){
		setUpSceneTourneyNormal();
		Spectator s=tourney.getCountrySpectators("Colombia");
		
		assertEquals(tourney.getRootSpectator().getId(), "3");
		assertEquals(tourney.getRootSpectator().getAlpha().getId(), "1");
		assertEquals(tourney.getRootSpectator().getOmega().getId(), "5");
	}
	//----------------------------------------------------------------------------------------------------|
	
	//List(Competitor)------------------------------------------------------------------------------------|
	@Test
	void testAddCompetitor(){
		setUpSceneTourneyEmpty();
		tourney.addCompetitor(new Spectator("3", "Esteban", "Ariza", "acosta57esteban@gmail.com", "Male", "Colombia", "ea.png", new GregorianCalendar()));
		tourney.addCompetitor(new Spectator("1", "Mateo", "Valdes", "mxyz@hotmail.com", "Male", "Colombia", "mv.png", new GregorianCalendar()));
		tourney.addCompetitor(new Spectator("5", "Johan", "Giraldo", "voodlyc@yahoo.com", "Male", "Colombia", "jg.png", new GregorianCalendar()));

		assertEquals(tourney.getFirstCompetitor().getId(), "5");
		assertEquals(tourney.getFirstCompetitor().getOmega().getId(), "1");
		assertEquals(tourney.getFirstCompetitor().getOmega().getOmega().getId(), "3");
	}
	
	@Test
	void testSearchCompetitor(){
		setUpSceneTourneyNormal();
		
		assertNotNull(tourney.searchCompetitor("3"));
		assertNotNull(tourney.searchCompetitor("1"));
		assertNotNull(tourney.searchCompetitor("5"));
		assertNull(tourney.searchCompetitor("2"));
	}
	
	@Test
	void testShowCountryCompetitors(){
		setUpSceneTourneyNormal();
		
		String text=tourney.showCountryCompetitors("Colombia");
		String[] spectators=text.split("\n");
		assertEquals(spectators[0].charAt(1), 'E');
		assertEquals(spectators[1].charAt(1), 'M');
		assertEquals(spectators[2].charAt(1), 'J');
	}
	
	@Test
	void testLoadCompetitors(){
		setUpSceneTourneyWithoutCompetitors();
		tourney.loadCompetitors();
		assertNotNull(tourney.getFirstCompetitor());
		assertNotNull(tourney.getFirstCompetitor().getOmega());
		assertEquals(tourney.spectatorSize(), 3);
	}
	//----------------------------------------------------------------------------------------------------|

}
