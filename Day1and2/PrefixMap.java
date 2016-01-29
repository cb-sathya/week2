/**
*This class implements an application to map the words with their prefix (first three charcters) in a hash map where the prefix is the key and the words are the values
*/

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class PrefixMap{
	public static void main(String args[]){
	Scanner scanner=new Scanner(System.in);
		HashMap<String,List<String>> map=new HashMap<String,List<String>>();
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

	static void mapWord(HashMap<String,List<String>> map,String word){		//method thats adds the word to the map,
		String prefix=word.substring(0,3);
		if(map.containsKey(prefix)){						//if the key is already present
			List<String> tempList=map.get(prefix);
			tempList.add(word);
			map.put(prefix,tempList);
		}

		else{									//if the new key is to be created
			List<String> tempList=new ArrayList<String>();
			tempList.add(word);
			map.put(prefix,tempList);
		}
		
	}

	static void display(HashMap<String,List<String>> map){				//method that prints the map
		Map<String,List<String>> sortedMap=new TreeMap<String,List<String>>(map);
		System.out.println("Prefix   words");
		for(Map.Entry m:sortedMap.entrySet()){		
			System.out.println(m.getKey()+"\t"+m.getValue());
		}
	}
}
		
		

