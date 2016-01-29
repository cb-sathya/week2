/**
*This class implements an application to obtain strings and store them in a map with the length of word as key and the list of words with that length as the value for that key
*/


import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class lengthMap{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		HashMap<Integer,List<String>> map=new HashMap<Integer,List<String>>();
		int choice;

		System.out.println("Enter the number of words");
		choice=scanner.nextInt();
		System.out.println("Enter the words");
		String word;

		for(int i=0;i<choice;i++){
			word=scanner.next();
			mapWord(map,word);
		}
	
		display(map);
	}

	static void mapWord(HashMap<Integer,List<String>> map,String word){		//method thats adds the word to the map,
		if(map.containsKey(word.length())){						//if the key is already present
			List<String> tempList=map.get(word.length());
			tempList.add(word);
			map.put(word.length(),tempList);
		}

		else{													//if the new key is to be created
			List<String> tempList=new ArrayList<String>();
			tempList.add(word);
			map.put(word.length(),tempList);
		}
		
	}

	static void display(HashMap<Integer,List<String>> map){				//method that prints the map
		System.out.println("Length   words");
		for(Map.Entry m:map.entrySet()){		
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
	}
}
		
		

