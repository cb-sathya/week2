/** 
This program implements an application that parses a JSON file and creates a corresponding java object with the values filled form the JSON file
*/

import java.util.Scanner;
import java.io.*;
import java.util.Iterator;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class Student{				//This class stores the information about the student
	String dateOfJoining;	
	String id;
	ArrayList<Mark> marks=new ArrayList<Mark>();
	String name;
	String std;
	Student(String name,String id,String std,String dateOfJoining,ArrayList<Mark> marks){
		this.name=name;
		this.id=id;
		this.std=std;
		this.dateOfJoining=dateOfJoining;
		this.marks=marks;
	}
	void toDisplay(){		//Display the student details
		String result="Name: "+name+"\nID: "+id+"\nStd: "+std+"\nDate of Joining: "+dateOfJoining;
		System.out.println(result);
		for(Mark mark:marks){
			mark.toDisplay();
		}
	}
}


class Mark{					//Stores the mark information
	String subject;
	long mark;
	Mark(long mark,String subject){
		this.mark=mark;
		this.subject=subject;
	}
	void toDisplay(){
		System.out.println("Subject: "+subject+"\tMark: "+mark);
	}
}

class Teacher{				//Stores the teacher information
	String name;
	String id;
	long salary;
	String dateOfJoining;
	ArrayList<String> classesTakingCareOf=new ArrayList<String>();

	Teacher(String name,String id,String dateOfJoining,long salary,ArrayList<String> classesTakingCareOf){
		this.name=name;
		this.id=id;
		this.salary=salary;
		this.dateOfJoining=dateOfJoining;
		this.classesTakingCareOf=classesTakingCareOf;
	}

	void toDisplay(){		//Display information about the teacher
		System.out.println("Name: "+name+"\nId: "+id+"\nSalary: "+salary+"\nDate of joining: "+dateOfJoining+"\nClassesTakingCareOf: ");
		for(String classes:classesTakingCareOf){
			System.out.println(classes+"\t");
		}	
	}
}


public class jsonParser{			//loads the jsonfile and parses it
	public static void main(String args[]){
		JSONParser parser=new JSONParser();
		ArrayList<Mark> marksList=new ArrayList<Mark>();
		ArrayList<String> classesTakingCareOf=new ArrayList<String>();

		try{
			Object obj=parser.parse(new FileReader("/Users/cb-sathyanarayanan/Downloads/students-teachers.json"));
						
											//Get student object and its input
			JSONObject jsonObject=(JSONObject) obj;
			JSONObject studentObject=(JSONObject) jsonObject.get("Student");
			String dateOfJoining=(String) studentObject.get("Date Of Joining");
			String id=(String) studentObject.get("ID");

			
			JSONArray Marks=(JSONArray) studentObject.get("Marks");
			Iterator<JSONObject> markIterator=Marks.iterator();
			while(markIterator.hasNext()){
				JSONObject markObject=(JSONObject) markIterator.next();
				marksList.add(new Mark((long)markObject.get("Mark"),(String)markObject.get("Subject")));
			}

			
			
			String name=(String) studentObject.get("Name");
			String std=(String) studentObject.get("Std");
			Student student=new Student(name,id,std,dateOfJoining,marksList);


											//get the teacher object and its input
			JSONObject teacherObject=(JSONObject) jsonObject.get("Teacher");
			JSONArray classesList=(JSONArray) teacherObject.get("Classes Taking Care Of");
			Iterator<String> classIterator=classesList.iterator();
			while(classIterator.hasNext()){
				classesTakingCareOf.add((String) classIterator.next());
			}
			
			dateOfJoining =(String) teacherObject.get("Date Of Joining");
			id=(String) teacherObject.get("ID");
			name=(String) teacherObject.get("Name");
			long salary=(long) teacherObject.get("Salary");

			Teacher teacher=new Teacher(name,id,dateOfJoining,salary,classesTakingCareOf);

			student.toDisplay();
			teacher.toDisplay();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
			
			
			 












		

