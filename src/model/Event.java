package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {

	//Attribute
	private Spectator rootSpectator;
	private Spectator firstCompetitor;
	
	//Constructor
	public Event(String path){
		load(path);
		addCompetitors();
	}
	
	//Do
	public int spectatorSize(){
		if(rootSpectator!=null)
			return rootSpectator.countSpectator();
		return 0;
	}
	
	public Spectator getRandomSpectator(){
		if(rootSpectator!=null){
			Spectator s=rootSpectator.getRandomSpectator();
			return new Spectator(s.getId(), s.getName(), s.getLastName(), s.getEmail(), s.getGender(), s.getCountry(), s.getPhoto(), s.getBirthdate());
		}
		return null;
	}
	
	//Add
	public boolean addSpectator(Spectator spectator){
		boolean possible=true;
		
		if(rootSpectator!=null){
			possible=this.rootSpectator.addSpectator(spectator);
		}
		else{
			this.rootSpectator=spectator;
		}
		
		return possible;
	}
	
	public void addCompetitor(Spectator spectator){
		if(firstCompetitor!=null)
			firstCompetitor.addCompetitor(spectator);
		else
			firstCompetitor=spectator;
	}
	
	public void addCompetitors(){
		
		int size=(spectatorSize()/2);
		
		while(size>0){
			Spectator s=getRandomSpectator();
			Spectator exist=searchCompetitor(s.getId());
			if(exist==null){
				addCompetitor(s);
				size--;
			}
		}
		
	}
	
	//Search
	public Spectator searchSpectator(String id){
		if(rootSpectator!=null)
			return rootSpectator.searchSpectator(id);
		return null;
	}
	
	public Spectator searchCompetitor(String id){
		if(firstCompetitor!=null)
			return firstCompetitor.searchCompetitor(id);
		return null;
	}
	
	//Load
	public boolean load(String path){
		boolean possible=true;
		
		try {
			String data=read(path);
			String[] SpectatorsData=data.split("\n");
			for(String SpectatorData: SpectatorsData){
				String[] variables=SpectatorData.split(",");
				if(variables.length==8){
					String[] dates=variables[7].split("/");
					Calendar birthdate=new GregorianCalendar(Integer.parseInt(dates[2]),Integer.parseInt(dates[1])-1,Integer.parseInt(dates[0]));
					addSpectator(new Spectator(variables[0],variables[1],variables[2],variables[3],variables[4],variables[5],variables[6],birthdate));
				}
			}
		}
		catch (IOException e) {
			possible=false;
		}
		
		return possible;
	}
	
	//Read
	private String read(String path) throws IOException{//[File]
		
		File clubs=new File(path);
		clubs.createNewFile();
		FileReader fileReader=new FileReader(clubs);
		BufferedReader reader=new BufferedReader(fileReader);
		String actualLine;
		String text="";
		while((actualLine=reader.readLine())!=null){
			text+=actualLine+"\n";
		}
		reader.close();
		return text;
		
	}
	
	//Set
	public void setRootSpectator(Spectator rootSpectator) {
		this.rootSpectator = rootSpectator;
	}

	public void setFirstCompetitor(Spectator firstCompetitor) {
		this.firstCompetitor = firstCompetitor;
	}
	
	//Get
	public Spectator getRootSpectator() {
		return rootSpectator;
	}

	public Spectator getFirstCompetitor() {
		return firstCompetitor;
	}
	
}
