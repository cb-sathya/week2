/** 
This program implements an application that searches a given text from the input file and writes it to <search-word>.locations in the format <line-no>:<start-index1>,<start-index2>...
*/

import java.util.*;
import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;

public class wordSearch{
	static void searchWord(File source,String searchString){
		try{
			BufferedReader reader=new BufferedReader(new FileReader(source.getAbsolutePath()));
			int lineCount=0;
			int index;
			String line,result="";
			
			while((line=reader.readLine())!=null){
				lineCount++;
				index=0;
				String[] words=line.split(" ");
				if(line.contains(searchString)){
					result+="Line "+lineCount+" : ";
					for(String word:words){
						index++;
						if(word.equals(searchString)){
							result+=index+" , ";
						}
					}
					result=result.substring(0,result.length()-2)+".\n";
				}
			}
			if(result==""){
				System.out.println("The word was not found");
			}
			else{
				OutputStream writer = new BufferedOutputStream(Files.newOutputStream(Paths.get("/Users/cb-sathyanarayanan/subfolder1/wordsearch/"+searchString+".locations.txt"),CREATE));
				writer.write(result.getBytes(),0,result.getBytes().length);
				writer.close();
				System.out.println("Written");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int choice;
		String searchString;
		File source;
		System.out.println("Enter the file name");
		source=new File(scanner.next());
		do{
			System.out.println("1.Search\n2.Exit");
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Enter the string to be searched for:");
				searchString=scanner.next();
				searchWord(source,searchString);
			}
			else{
				System.out.println("The locations of the words searched will be found in /Users/cb-sathyanarayanan/subfolder1/wordSearch/<searchString>.locations.txt");
			}
		}while(choice==1);
	}
}
		
		
