/**
*This program uses a json config file to parse a csv file from one format to another
*/
import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;  
import org.json.simple.parser.ParseException;  

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVPrinter;

import java.text.SimpleDateFormat;

import java.io.*;
import java.util.*;

class csvTransformation{
	private List<CSVRecord> recordList=new ArrayList<CSVRecord>();
	private Set<String> headerList;
	private JSONObject jsonObject;


	//Reads the input csv file
	public void readCsv(File source)throws Exception{
		try{
			FileReader reader=new FileReader(source);
			CSVFormat csvFormat=CSVFormat.DEFAULT.withHeader();
			CSVParser csvParser=new CSVParser(reader,csvFormat);
			recordList= csvParser.getRecords();
			headerList=csvParser.getHeaderMap().keySet();
		}catch(IOException io){
			io.printStackTrace();
		}
	}




	//loads the json config file
	public void loadConfig(File source)throws Exception{
		try{
			JSONParser jsonParser=new JSONParser();
			Object obj=jsonParser.parse(new FileReader(source));
			jsonObject=(JSONObject) obj;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	


	//writes the output csv file
	public void writeCsv(File destination)throws Exception{
			FileWriter fileWriter=null;
			CSVPrinter csvPrinter=null;
		try{
			fileWriter=new FileWriter(destination);
			CSVFormat csvFormat=CSVFormat.DEFAULT.withRecordSeparator("\n");
			csvPrinter= new CSVPrinter(fileWriter,csvFormat);

			
			//setting the new header for the output file
			LinkedHashSet<String> headers = new LinkedHashSet<String>();
			LinkedHashSet<String> jsonHeaders = new LinkedHashSet<String>(); 
			for(String header:headerList){
				
				JSONObject object=(JSONObject)jsonObject.get(header);
				if(object != null && (((String)object.get("type")).equals("json"))){
					jsonHeaders.add((String)object.get("column Name"));	
				}
				else{
					headers.add(header);
				
				}
			}
			for(String jsonHeader: jsonHeaders){
				headers.add(jsonHeader);
			}
			csvPrinter.printRecord(headers);
			

			//transforms the required columns and writes it to the output file
			for(CSVRecord record:recordList){
				getConfig(csvPrinter,record);
			}	
					
		}
		finally{
			fileWriter.flush();
			fileWriter.close();
			csvPrinter.close();
		}
	}





	//transforms the required columns and writes it to the output file
	public void getConfig(CSVPrinter csvPrinter,CSVRecord record)throws Exception{
		LinkedHashMap<String,JSONObject> jsonColumn=new LinkedHashMap<String,JSONObject>();

		for(String heading:headerList){
			JSONObject headerObject=(JSONObject)jsonObject.get(heading);
			String type=(headerObject!=null)? (String)headerObject.get("type"):null;

			if(type!=null){
				switch(type){
		
					case "date":
						SimpleDateFormat oldFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
						SimpleDateFormat newFormat= new SimpleDateFormat("M/dd/yyyy HH:mm:ss");
	
						Date date= oldFormat.parse((String) record.get(heading));
						String printValue= newFormat.format(date);

						csvPrinter.print(printValue);
						//System.out.println(printValue);
						break;


					case "currency":
						Double Value=((Double) headerObject.get("value")) * (Double.parseDouble(record.get(heading)));
						csvPrinter.print(Double.toString(Value));
						//System.out.println(Double.toString(Value));
						break;


					case "json":
						String value=record.get(heading);
						if(value!=null && !value.isEmpty()){
							String columnName=(String)headerObject.get("column name");
							JSONObject column=jsonColumn.containsKey(columnName)? jsonColumn.get(columnName) : new JSONObject();
							column.put((String)headerObject.get("field name"),value);
							jsonColumn.put(columnName,column);
						}
						break;
					}
				}
				else{
					csvPrinter.print(record.get(heading));
					//System.out.println(record.get(heading));
				}
			}


		for(JSONObject jsonObj:jsonColumn.values()){
			csvPrinter.print(jsonObj.toString());
			//System.out.println(jsonObj.toString());
		}
		csvPrinter.println();
	}
}

public class csvTransform{
	public static void main(String args[])throws Exception{
		
		File csvSource=new File("input1.csv");
		File jsonSource=new File("config.json");
		File destination=new File("output.csv");	
		csvTransformation csv=new csvTransformation();
		csv.readCsv(csvSource);
		csv.loadConfig(jsonSource);
		csv.writeCsv(destination);
		
	}	
}
		
						
							
					


















	
