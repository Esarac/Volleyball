package model;

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
	//Do
	public int countSpectator(){
		int sum=0;
		if(alpha!=null)
			sum+=alpha.countSpectator();
		if(omega!=null)
			sum+=omega.countSpectator();
		return sum + 1;
	}
	
	public Spectator getRandomSpectator(){
		int probability=(int)(Math.random()*101);
		Spectator spectator=null;
		if(probability>=70)
			return this;
		else{
			int path=(int)(Math.random()*2);
			if(path==0){
				if(alpha!=null)
					spectator=alpha.getRandomSpectator();
				else
					spectator=this;
			}
			else{
				if(omega!=null)
					spectator=omega.getRandomSpectator();
				else
					spectator=this;
			}
		}
		return spectator;
			
	}
	
	//Add
	public boolean addSpectator(Spectator spectator){
		boolean possible=true;
		
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
		
		return possible;
	}
	
	public void addCompetitor(Spectator spectator){
		if(omega!=null){
			omega.addCompetitor(spectator);
		}
		else{
			omega=spectator;
			spectator.setAlpha(this);
		}
		
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
	
	public Spectator searchCompetitor(String id){
		Spectator competitor=null;
		
		if(id.equals(this.id)){
			competitor=this;
		}
		else{
			if(this.omega!=null)
				competitor=omega.searchCompetitor(id);
		}
		
		return competitor;
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
	
	//Show
	public String toString() {
		return "[" + name + " " + lastName +
				", " + id +
				", " + gender +
				//", " + birthdate +
				", " + country +
				", " + email + "]";
	}
	
}
