/**
This program scans a directory and its subdirectories and moves all their files to a single directory(with some naming changes if the file name is already present)
*/

import java.io.File;
import java.util.ArrayList;


public class copyFile{
		
		//scans the directory and creates the file list
	static void scanDirectory(File source,ArrayList<File> fileArrayList){
		File[] fileList=source.listFiles();
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isFile()){
				fileArrayList.add(fileList[i]);
			}
			if(fileList[i].isDirectory()){
				scanDirectory(fileList[i],fileArrayList);
			}
		}
	}
		//displays the files that are present in the source directory and its subdirectories
	static void printList(ArrayList<File> fileArrayList){

		if(fileArrayList.size()==0){
			System.out.println("There are no files in the source folder to be moved");
			return;
		}
		System.out.println("FILES IN THE SOURCE FOLDER");
		for(File file:fileArrayList){
			System.out.println(file.getName());
		}
	}
		//moves the files to the destination directory
	static void addToDirectory(ArrayList<File> fileArrayList,File destination){
	
		if(fileArrayList.size()==0){
			return;
		}
		try{
			for(File file:fileArrayList){
				File newFile=new File(destination.getAbsolutePath()+"/"+file.getName());
				File oldFile=newFile;
				int count=1;
		
				while(newFile.exists()){
					String[] substring=oldFile.getName().split("\\.");
					newFile=new File(destination.getAbsolutePath()+"/"+substring[0]+"-"+count+"."+substring[1]);
					count++;
				}

				file.getAbsoluteFile().renameTo(newFile.getAbsoluteFile());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("The files have been copied");
	}

	//loads the source and destination files
	public static void main(String args[]){
		ArrayList<File> fileArrayList=new ArrayList<File>();
		File destination=new File("/Users/cb-sathyanarayanan/subfolder1/NewFolder");
		if(!destination.exists()){
			destination.mkdir();
		}
		
		File source =new File("/Users/cb-sathyanarayanan/subfolder1/copytest");

		scanDirectory(source,fileArrayList);
		printList(fileArrayList);
		addToDirectory(fileArrayList,destination);
	}
}




	











			
