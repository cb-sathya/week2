/**
This class implements an application that stores a simple phone directory that stores the name,address and the phone number of the person
*/
import java.util.Scanner;
import java.util.ArrayList;
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

	void searchByName(String name){			//checks if the name specified matches this particular person
		if(this.name.equalsIgnoreCase(name)){
			this.toDisplay();
		}
	}
	
	void searchByPartial(String name){		//checks if the partial name specified matches this particular person
		if(this.name.contains(name.toLowerCase())){
			this.toDisplay();
		}
	}
	
	void searchByNumber(String number){		//checks if the specified number specified matches this persons number
		if(number.equals(this.home)||number.equals(this.work)||number.equals(this.mobile)){
			this.toDisplay();
		}
	}

	void toDisplay(){						//displays the details of the person
		System.out.println("Name: "+this.name+"\tCity: "+this.city+"\tMobile: "+this.mobile+"\tHome: "+this.home+"\tWork: "+this.work);
	}
}

class PhoneDirectory{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int choice;
		String name;
		String number;
		
		ArrayList<Person> directory=new ArrayList<Person>();			//sample entries
		directory.add(new Person("sathya","madurai","7373531119","2383384","1234567890"));
		directory.add(new Person("sathya","dindigul","9443015542","2383382","3214567890"));
		directory.add(new Person("surya","madurai","9345432346","2273648","8234785567"));
		directory.add(new Person("vignesh","dindigul","9234800945","25232384","9673856432"));
		directory.add(new Person("sivahari","pattiveeranpatti","7673547322","23234484","9674567890"));	
		do{
			System.out.println("1.Search by name\n2.Search by partial name\n3.Search by phone number\n4.Exit");
			choice=scanner.nextInt();
			switch(choice){							//recieves users choice
				case 1:	
					System.out.println("Enter the name of the person whose number is to be searched");
					name=scanner.next();
					for(Person person:directory){
						person.searchByName(name);
					}
					break;
				case 2:
					System.out.println("Enter the partial name of the person whose details are needed");
					name=scanner.next();
					for(Person person:directory){
						person.searchByPartial(name);
					}
					break;
				case 3:
					System.out.println("Enter the phone number of the person to be searched");
					number=scanner.next();
					for(Person person:directory){
						person.searchByNumber(number);
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
		








			
		

		
