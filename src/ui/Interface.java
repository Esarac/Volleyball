package ui;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Tourney;
import model.Spectator;

public class Interface {

	private Tourney tourney;
	private Scanner scanner;
	
	public void menu(){
		
		boolean run=true;
		while(run){
			System.out.println("Torneo:\n 1.Buscar Espectador\n 2.Buscar Participante\n 3.Imprimir Espectadores Pais\n 4.Imprimir Participantes Pais\n 5.Cargar Archivo\n 6.Salir");
			int option=askInt(1,6);
			switch(option){
				case 1:
					System.out.println("Id del espectador:");
					String a1=scanner.next();
					scanner.nextLine();
					
					long aS=System.nanoTime();
					Spectator aT=tourney.searchSpectator(a1);
					long aE=System.nanoTime();
					if(aT!=null)System.out.println(aT);else System.out.println("No existe el espectador");//Message
					System.out.println((aE-aS)+"ns");//DeltaTime
				break;
				case 2:
					System.out.println("Id del participante:");
					String b1=scanner.next();
					scanner.nextLine();
					
					long bS=System.nanoTime();
					Spectator bT=tourney.searchCompetitor(b1);
					long bE=System.nanoTime();
					if(bT!=null)System.out.println(bT);else System.out.println("No existe el participante");//Message
					System.out.println((bE-bS)+"ns");//DeltaTime
				break;
				case 3:
					System.out.println("Pais de los espectadores:");
					String c1=scanner.nextLine();
					
					System.out.println(tourney.showCountrySpectators(c1));//Message
				break;
				case 4:
					System.out.println("Pais de los competidores:");
					String d1=scanner.nextLine();
					
					System.out.println(tourney.showCountryCompetitors(d1));//Message
				break;
				case 5:
					askFile();
				break;
				case 6:
					run=false;
					System.out.println("Hasta la proxima!");//Message
				break;
			}
		}
		
	}
	
	//Ask
	public void askFile(){
		
		boolean run=true;
		
		while(run){
			System.out.println("Ruta del archivo:");
			String path=scanner.next();
			scanner.nextLine();
			
			boolean possible=tourney.load(path);
			if(possible){
				System.out.println("Se han cargado los datos correctamente");
				run=false;
			}
			else
				System.out.println("No se ha encontrado el archivo");
		}
		
	}
	
	public int askInt(int start, int end){
		
		int ask=0;
		boolean run=true;
		
		while(run){
			try{
				ask=scanner.nextInt();
				scanner.nextLine();
				if((ask>=start)&&(ask<=end)){run=false;}
				else{System.out.println("Opcion invalida!");}
			}
			catch(InputMismatchException e){
				System.out.println("Opcion invalida!");
				scanner=new Scanner(System.in);
			}
		}
		
		return ask;

	}
	
	public Interface(){
		init();
	}
	
	public void init(){
		this.scanner=new Scanner(System.in);
		this.tourney=new Tourney();
		askFile();
	}
	
}
