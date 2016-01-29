/**
This program implements a phone directory that takes a json file that contains the phone details as an input and do searches based on name and phone number
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Person{									//this class contains the details of the person
	String name;
	String home;
	String mobile;
	String work;
	String city;
	
	Person(String name,String city,String mobile,String home,String work){
		this.name=name;
		this.home=home;
		this.mobile=mobile;
		this.work=work;
		this.city=city;
	}

	boolean searchByName(String name){			//checks if the name specified matches this particular person
		if(this.name.equalsIgnoreCase(name)){
			this.toDisplay();
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean searchByPartial(String name){		//checks if the partial name specified matches this particular person
		if(this.name.contains(name.toLowerCase())){
			this.toDisplay();
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean searchByNumber(String number){		//checks if the specified number specified matches this persons number
		if(number.equals(this.home)||number.equals(this.work)||number.equals(this.mobile)){
			this.toDisplay();
			return true;
		}
		else{
			return false;
		}
	}

	void toDisplay(){						//displays the details of the person
		System.out.println("Name: "+this.name+"\tCity: "+this.city+"\tMobile: "+this.mobile+"\tHome: "+this.home+"\tWork: "+this.work);
	}
}


public class jsonPhoneDirectory{				//gets the data from the json file, parses it and creates instances of person from it
	static List<Person> getjson(File source){
		List<Person> person=new ArrayList<Person>();
		try{
			JSONParser parser=new JSONParser();
			Object obj=parser.parse(new FileReader(source));
			JSONObject jsonObject=(JSONObject) obj;
			JSONArray personArray=(JSONArray) jsonObject.get("Person");
			Iterator<JSONObject> personIterator=personArray.iterator();
			while(personIterator.hasNext()){
				JSONObject po=(JSONObject) personIterator.next();
				person.add(new Person((String)po.get("Name"),(String)po.get("City"),(String)po.get("Mobile"),(String)po.get("Home"),(String)po.get("Work")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return person;
	}

	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int choice,count;
		String name;
		String number;
		File source=new File("/Users/cb-sathyanarayanan/subfolder1/subfolder2/subfolder3/subfolder4/training/week2/directory.json");
		
		List<Person> directory;
		directory=getjson(source);
		do{
			System.out.println("1.Search by name\n2.Search by partial name\n3.Search by phone number\n4.Exit");
			choice=scanner.nextInt();
			switch(choice){							//recieves users choice
				case 1:								//search based on name
					System.out.println("Enter the name of the person whose number is to be searched");
					name=scanner.next();
					count=0;
					for(Person person:directory){
						if(person.searchByName(name)){
							count++;
						}
					}
					if(count==0){
						System.out.println("Sorry, the name was not found");
					}
					break;
				case 2:								//search based on partial name
					System.out.println("Enter the partial name of the person whose details are needed");
					name=scanner.next();
					count=0;
					for(Person person:directory){
						if(person.searchByPartial(name)){
							count++;
						}
					}
					if(count==0){
						System.out.println("Sorry, the name was not found");
					}
					break;
				case 3:								//search based on number
					System.out.println("Enter the phone number of the person to be searched");
					number=scanner.next();
					count=0;
					for(Person person:directory){
						if(person.searchByNumber(number)){
							count++;
						}
					}
					if(count==0){
						System.out.println("Sorry, the number was not found");
					}
					break;
				case 4:								
					System.out.println("Thank You");
					break;
				default:
					System.out.println("Enter a valid choice");
					break;
			}
		}while(choice<4);
	}
}

			
			






















