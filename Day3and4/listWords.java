/**
This program implements a class that reads a the text files and lits all the words in alphabetical order with their number of occurances
*/

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class listWords{
										//Reads the words present in the file and adds it to the map
	static void readWords(File source,TreeMap<String,Integer> map){
		try{
			Scanner scanner=new Scanner(source);
			String word;
			int count;
			while(scanner.hasNext()){		
				word=scanner.next();
				if(map.containsKey(word)){		//if word is already present
					count=map.get(word);
					count++;
					map.put(word,count);
				}
				else{							//first occurance for the word
					map.put(word,1);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
										//writes the words with their number of occurances into the destination file
	static void writeWords(File destination,TreeMap<String,Integer> map){
		try{
			String word;
			OutputStream writer = new BufferedOutputStream(Files.newOutputStream(Paths.get(destination.getAbsolutePath())));
		
			for(Map.Entry m:map.entrySet()){
				word = "["+m.getKey()+"]:["+m.getValue()+"]\n";
				writer.write(word.getBytes(),0,word.getBytes().length);
			}
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("DONE");
		

	}
										//loads the source and destination files
	public static void main(String args[]){
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		File source=new File("/Users/cb-sathyanarayanan/subfolder1/words.txt");
		File destination=new File("/Users/cb-sathyanarayanan/subfolder1/wordList.txt");
		readWords(source,map);
		writeWords(destination,map);
	}
}					
