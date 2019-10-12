package ui;

import java.util.GregorianCalendar;
import java.util.Scanner;
import model.Event;
import model.Spectator;

public class Interface {

	private Event event;
	private Scanner scanner;
	
	public void menu(){
		
		System.out.println(event.getRandomSpectator());
		System.out.println("---------------------------");
		System.out.println(event.searchCompetitor("3"));
		System.out.println(event.searchCompetitor("4"));
		System.out.println(event.searchCompetitor("5"));
		System.out.println(event.searchCompetitor("6"));
		System.out.println(event.searchCompetitor("7"));
		System.out.println(event.searchCompetitor("8"));
		System.out.println(event.searchCompetitor("9"));
		
	}
	
	public Interface(){
		init();
	}
	
	public void init(){
		this.event=new Event("data/1.txt");
		this.scanner=new Scanner(System.in);
	}
	
}
