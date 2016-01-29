/** 
This program implements a phone directory that has a csv file that contains the phone details, as the input and can do searches based on name and phone numbers
**/

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.*;


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



class csvPhoneDirectory{
											//Gets the input from the source file
	static List<Person> csvGet(File source){
		List<Person> persons=new ArrayList<Person>();
		try{
			CSVParser parser=new CSVParser(new FileReader(source),CSVFormat.DEFAULT.withRecordSeparator("\n").withHeader("name","address","mobile","home","work"));
			List<CSVRecord> records=parser.getRecords();
			for (int i=1;i<records.size();i++){
				CSVRecord record=records.get(i);
				Person person=new Person(record.get("name"),record.get("address"),record.get("mobile"),record.get("home"),record.get("work"));
				persons.add(person);
			}	
		}catch(Exception e){				//handles exceptions
			e.printStackTrace();
		}
		finally{
			parser.close();
		}
		return persons;
	}

	
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int choice,count;
		String name;
		String number;
		File source=new File("/Users/cb-sathyanarayanan/subfolder1/subfolder2/subfolder3/subfolder4/training/week2/directory.csv");
		
		List<Person> directory;
		directory=csvGet(source);
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


