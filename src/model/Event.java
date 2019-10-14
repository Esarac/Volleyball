package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {

	//Attribute
	private Spectator rootSpectator;
	private Spectator firstCompetitor;
	
	//Constructor
	public Event(){}
	
	//BST(Spectator)--------------------------------------------------------------------------------------|
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
	
		//Search
	public Spectator searchSpectator(String id){
		if(rootSpectator!=null)
			return rootSpectator.searchSpectator(id);
		return null;
	}
	
		//Show
	public String showCountrySpectators(String country){
		Spectator firstSpectator=getCountrySpectators(country);
		String tree="";
		if(firstSpectator!=null){
			tree+=firstSpectator.showSpectators(0);
		}
		return tree;
	}
	
		//Load
	public boolean loadSpectators(String path){
		boolean possible=true;
		
		try {
			String data=read(path);
			if(data!=null){
				String[] SpectatorsData=data.split("\n");
				for(String SpectatorData: SpectatorsData){
					String[] variables=SpectatorData.split(",");
					if(variables.length==8){
						String[] dates=variables[7].split("/");
						if(dates.length==3){
							Calendar birthdate=new GregorianCalendar(Integer.parseInt(dates[2]),Integer.parseInt(dates[0])-1,Integer.parseInt(dates[1]));
							addSpectator(new Spectator(variables[0],variables[1],variables[2],variables[3],variables[4],variables[5],variables[6],birthdate));
						}
					}
				}
			}
			else{
				try{
					throw new FileDontExistException();
				}
				catch(FileDontExistException e){
					possible=false;
				}
			}
		}
		catch(IOException e){
			possible=false;
		}
		
		return possible;
	}
	
		//Read
	private String read(String path) throws IOException{//[File]
		String text="";
		
		File clubs=new File(path);
		if(clubs.exists()){
			clubs.createNewFile();
			FileReader fileReader=new FileReader(clubs);
			BufferedReader reader=new BufferedReader(fileReader);
			String actualLine;
			while((actualLine=reader.readLine())!=null){
				text+=actualLine+"\n";
			}
			reader.close();
		}
		else{
			text=null;
		}
		
		return text;
	}
	
		//Set
	public void setRootSpectator(Spectator rootSpectator) {
		this.rootSpectator = rootSpectator;
	}
	
		//Get
	public int spectatorSize(){
		int size=0;
		if(rootSpectator!=null)
			size=rootSpectator.countSpectator();
		return size;
	}
	
	public Spectator getRandomSpectator(){
		Spectator random=null;
		if(rootSpectator!=null){
			Spectator s=rootSpectator.getRandomSpectator();
			if(s.compareTo(rootSpectator)==0){
				rootSpectator=rootSpectator.deleteSpectator();
			}
			random=new Spectator(s.getId(), s.getName(), s.getLastName(), s.getEmail(), s.getGender(), s.getCountry(), s.getPhoto(), s.getBirthdate());
		}
		return random;
	}
	
	public Spectator getCountrySpectators(String country){
		Spectator firstSpectator=null;
		
		if(rootSpectator!=null){
			ArrayList<Spectator> countrySpectators=rootSpectator.getCountrySpectators(country);
			if(countrySpectators.size()>0){
				firstSpectator=countrySpectators.get(0);
				for(int i=1; i<countrySpectators.size(); i++){
					firstSpectator.addSpectator(countrySpectators.get(i));
				}
			}
		}
		
		return firstSpectator;
	}
	
	public Spectator getRootSpectator() {
		return rootSpectator;
	}
	//----------------------------------------------------------------------------------------------------|
	
	//List(Competitor)------------------------------------------------------------------------------------|
		//Add
	public void addCompetitor(Spectator spectator){
		spectator.setOmega(firstCompetitor);
		if(firstCompetitor!=null)
			firstCompetitor.setAlpha(spectator);
		this.firstCompetitor=spectator;
	}
	
		//Search
	public Spectator searchCompetitor(String id){
		Spectator actual=firstCompetitor;
		boolean found=false;
		
		while((actual!=null) && !found){
			if(id.equals(actual.getId())){
				found=true;
			}
			else{
				actual=actual.getOmega();
			}
		}
		
		return actual;
	}
	
		//Show
	public String showCountryCompetitors(String country){
		String competitors="";
		Spectator actual=firstCompetitor;
		
		while(actual!=null){
			if(country.equals(actual.getCountry())){
				competitors+=actual+"\n";
			}
			actual=actual.getOmega();
		}

		return competitors;
	}
	
		//Load
	public void loadCompetitors(){
		int size=(spectatorSize()/2);
		for(int i=0;i<size;i++){
			Spectator s=getRandomSpectator();
			addCompetitor(s);
		}
	}
	
		//Set
	public void setFirstCompetitor(Spectator firstCompetitor){
		this.firstCompetitor = firstCompetitor;
	}
	
		//Get
	public Spectator getFirstCompetitor(){
		return firstCompetitor;
	}
	//----------------------------------------------------------------------------------------------------|
	
	//Load
	public boolean load(String path){//[Call]
		this.rootSpectator=null;
		this.firstCompetitor=null;
		boolean possible=loadSpectators(path);
		loadCompetitors();
		return possible;
	}
	
}
