package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Spectator implements Comparable<Spectator>{

	//Constant
	public static final String MALE="Male";
	public static final String FEMALE="Female";
	
	//Attribute
	private String id;
	private String name;
	private String lastName;
	private String email;
	private String gender;
	private String country;
	private String photo;
	private Calendar birthdate;
	
	private Spectator alpha;
	private Spectator omega;
	
	//Constructor
	public Spectator(String id, String name, String lastName, String email, String gender, String country, String photo, Calendar birthdate){
		
		this.id=id;
		this.name=name;
		this.lastName=lastName;
		this.email=email;
		this.gender=gender;
		this.country=country;
		this.photo=photo;
		this.birthdate=birthdate;
		
	}
	
	//BST(Spectator)--------------------------------------------------------------------------------------|
		//Add
	public boolean addSpectator(Spectator spectator){
		boolean possible=true;
		
		if(spectator!=null){
			if(spectator.compareTo(this)>0){
				if(this.omega==null){
					this.omega=spectator;
				}
				else{
					possible=this.omega.addSpectator(spectator);
				}
			}
			else if(spectator.compareTo(this)<0){
				if(this.alpha==null){
					this.alpha=spectator;
				}
				else{
					possible=this.alpha.addSpectator(spectator);
				}
			}
			else{
				possible=false;
			}
		}
		else{
			possible=false;
		}
		
		return possible;
	}
	
		//Delete
	public Spectator deleteSpectator() {
		Spectator spectator=null;
		
		int path=(int)(Math.random()*2);
		if(path==0){
			if(alpha!=null){
				alpha.addSpectator(omega);
				spectator=alpha;
			}
			else if(omega!=null){
				omega.addSpectator(alpha);
				spectator=omega;
			}
		}
		else{
			if(omega!=null){
				omega.addSpectator(alpha);
				spectator=omega;
			}
			else if(alpha!=null){
				alpha.addSpectator(omega);
				spectator=alpha;
			}
		}
		
		return spectator;
	}
	
		//Search
	public Spectator searchSpectator(String id){
		Spectator spectator=null;
		
		if(id.compareTo(this.id)>0){
			if(this.omega!=null)
				spectator=omega.searchSpectator(id);
		}
		else if(id.compareTo(this.id)<0){
			if(this.alpha!=null)
				spectator=alpha.searchSpectator(id);
		}
		else{
			spectator=this;
		}
		
		return spectator;
	}
	
		//Show
	public String showSpectators(int level){
		String spectators="";
		for(int i=0; i<level; i++){
			spectators+="	";
		}
		if(level>0){
			spectators+="\\->";
		}
		spectators+=this+"\n";
		if(alpha!=null)
			spectators+=alpha.showSpectators(level+1);
		if(omega!=null)
			spectators+=omega.showSpectators(level+1);
		return spectators;
		
	}
	
		//Calculate
	public int countSpectator(){
		int sum=0;
		if(alpha!=null)
			sum+=alpha.countSpectator();
		if(omega!=null)
			sum+=omega.countSpectator();
		return sum + 1;
	}
	
		//Get
	public Spectator getRandomSpectator(){
		int probability=(int)(Math.random()*101);
		Spectator spectator=null;
		if(probability<=10){
			spectator=this;
		}
		else{
			int path=(int)(Math.random()*2);
			if(path==0){
				if(alpha!=null){
					spectator=alpha.getRandomSpectator();
					if(spectator.compareTo(alpha)==0){
						alpha=alpha.deleteSpectator();
					}
				}	
				else{
					spectator=this;
				}
			}
			else{
				if(omega!=null){
					spectator=omega.getRandomSpectator();
					if(spectator.compareTo(omega)==0){
						omega=omega.deleteSpectator();
					}
				}
				else{
					spectator=this;
				}
			}
		}
		return spectator;
			
	}
	
	public ArrayList<Spectator> getCountrySpectators(String country){
		ArrayList<Spectator> spectators=new ArrayList<Spectator>();
		if(country.equals(this.country))
			spectators.add(new Spectator(this.id, this.name, this.lastName, this.email, this.gender, this.country, this.photo, this.birthdate));
		if(alpha!=null)
			spectators.addAll(alpha.getCountrySpectators(country));
		if(omega!=null)
			spectators.addAll(omega.getCountrySpectators(country));
		return spectators;
	}
	//----------------------------------------------------------------------------------------------------|
	
	//Show
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return "[" + name + " " + lastName +
				", " + id +
				", " + gender +
				", " + f.format(birthdate.getTime()) +
				", " + country +
				", " + email + "]";
	}
	
	//Compare
	public int compareTo(Spectator spectator){
		return this.id.compareTo(spectator.id);
	}
	
	//Set
	public void setAlpha(Spectator alpha){
		this.alpha = alpha;
	}

	public void setOmega(Spectator omega){
		this.omega = omega;
	}
	
	//Get
	public String getId(){
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public Calendar getBirthdate() {
		return birthdate;
	}
	
	public Spectator getAlpha(){
		return alpha;
	}
	
	public Spectator getOmega(){
		return omega;
	}
	
}
