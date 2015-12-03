package com.ligadata.dataGenerationTool;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.ligadata.dataGenerationToolBean.ConfigObj;

import org.json.JSONObject;

public class MainClass {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public static void main(String[] args) throws InterruptedException,
			IOException {
		// initialize variables
		String configFileLocation = "C:/Users/haitham-pc/Documents/GitHub/DataGenerationTool/JsonFiles/DataGenerationConfig.json"; // C:/Users/haitham-pc/Documents/GitHub/DataGenerationTool/JsonFiles/DataGenerationConfig.json
		String templateFileLocation = "C:/Users/haitham-pc/Documents/GitHub/DataGenerationTool/JsonFiles/DataDefinitionTemplate.json"; // C:/Users/haitham-pc/Documents/GitHub/DataGenerationTool/JsonFiles/DataDefinitionTemplate.json
		String destiniationDirectory = "C:/Users/haitham-pc/Desktop/GenereatedData/"; // C:/Users/haitham-pc/Desktop/GenereatedData/
		
		// create object
		JsonUtility json = new JsonUtility();
		GenerateRecord record = new GenerateRecord();
		FilesUtility file = new FilesUtility();
		TimeUtility time = new TimeUtility();

		// read configuration file
		JSONObject configJson = json.ReadJsonFile(configFileLocation);
		ConfigObj configObj = json.CreateConfigObj(configJson);
		JSONObject templateJson = json.ReadJsonFile(templateFileLocation);
		//ConfigObj configObj = json.CreateConfigObj(configJson);
		
		// DurationInHours		 
		double loopEndTime = time.RunDurationTime(configObj);
		
		HashMap<String, String> fields = new HashMap<String, String>();
		while (System.currentTimeMillis() < loopEndTime) {
			System.out.println("Generating Data...");
			fields = json.ReadMessageFields(templateJson);
			String hit = record.GenerateHit(fields, configObj.getDelimiter());

			if (configObj.isDropInFiles()) {
				file.writeFile(hit, destiniationDirectory,configObj);// destination path
			} else if (configObj.isPushToKafka()) {
				// code to push to kafka
			}
			
			Thread.sleep(time.SleepTime(configObj));
		}

	}

}
