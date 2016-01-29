/**
This program implements an application to input an csv file and remove duplicate records and output a new csv file with non-duplicate records
*/

import java.io.*;
import java.util.*;
import java.nio.file.*;

import org.apache.commons.csv.*;
public class csvDuplicate{
							//reads the input csv file  and removes the duplicate records
	static List<String> csvGet(File source){
		List<String> records=new ArrayList<String>();
		try{
			BufferedReader reader=new BufferedReader(new FileReader(source));
			String record;
			while((record=reader.readLine())!=null&& !record.isEmpty()){
				if(!records.contains(record)){
					records.add(record);
				}
			}
			reader.close();
		}catch(Exception e){				//handles exceptions
			e.printStackTrace();
		}
		return records;
	}
							//write an output file with no duplicate records
	static void csvPut(File destination,List<String> records){
		try{
			CSVPrinter printer=new CSVPrinter(new FileWriter(destination),CSVFormat.DEFAULT.withRecordSeparator("\n"));
			for(String record:records){
				printer.printRecord(record);
			}
			System.out.println("Done successfully");
			printer.close();
		}catch(Exception e){			//handles exception
			e.printStackTrace();
		}
	}

							//loads the source and destination files
	public static void main(String args[]){
		File source=new File("/users/cb-sathyanarayanan/subfolder1/subfolder2/subfolder3/subfolder4/training/week2/duplicate.csv");
		File destination=new File("/users/cb-sathyanarayanan/subfolder1/subfolder2/subfolder3/subfolder4/training/week2/noDuplicate.csv");
		List<String> csvRecords=new ArrayList<String>();
		csvRecords=csvGet(source);
		csvPut(destination,csvRecords);
	}
}
		
