/**
This class implements an application that prints the file extensions along with the number of files in that extension
*/

import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class fileExt{

	static void createMap(File file,HashMap<String,Integer> map){	//gets the extension of all files in this directory
		File[] fileList=file.listFiles();
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isFile()){				//if it is a file then its extension is obtained
				if(fileList[i].getName().contains(".")&&fileList[i].getName().lastIndexOf(".") != 0){
					String[] extension=fileList[i].getName().split("\\.");
					setMap(map,extension[1]);
				}
			}
			if(fileList[i].isDirectory()){		   //if it is a directory then the files under that directory is obtained
				createMap(fileList[i],map);
			}
		}
	}
	
	static void setMap(HashMap<String,Integer> map,String ext){
		int count=0;
		if(map.containsKey(ext)){						//if the key is present already
			count=map.get(ext);
			map.put(ext,++count);	
		}
		else{											//if the key is not present already
			map.put(ext,++count);
		}
	}

	static void printMap(HashMap<String,Integer> map){		//prints the map
		System.out.println("Extension\tNo.of files with that extension");
		for(Map.Entry m:map.entrySet()){		
			System.out.println(m.getKey()+"\t\t"+m.getValue());
		}
	}

	public static void main(String args[]){				//loads the directory
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		File rootDir=new File("/Users/cb-sathyanarayanan/subfolder1/subfolder2/subfolder3/subfolder4/training/week2");
 		createMap(rootDir,map);	
		printMap(map);
	}
}


				
		
	
