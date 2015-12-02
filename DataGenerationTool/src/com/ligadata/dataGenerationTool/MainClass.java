package com.ligadata.dataGenerationTool;

import java.io.IOException;
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
		String destiniationDirectory = "C:/Users/haitham-pc/Desktop/GenereatedData"; // C:/Users/haitham-pc/Desktop/GenereatedData
		
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
		
//		System.out.println(configJson);
//		System.out.println(templateJson);
		
		// DurationInHours		 
		double loopEndTime = time.RunDurationTime(configObj);
		
//		String fileSplitPer = configObj.getFileSplitPer();
//		String fileNameFormat = time.CheckTimeUnit(fileSplitPer.substring(fileSplitPer.length() - 1));
//		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,fileSplitPer.length()-1));
//		System.out.println(fileSplitPer);
//		System.out.println(fileNameFormat);
//		System.out.println(timeAmountForFileSplit);
		
		
//		System.out.println(loopEndTime);
		
		HashMap<String, String> fields = new HashMap<String, String>();
		while (System.currentTimeMillis() < loopEndTime) {
			fields = json.ReadMessageFields(templateJson);
			String hit = record.GenerateHit(fields, configObj.getDelimiter());
			System.out.println(hit);

			if (configObj.isDropInFiles()) {
				file.writeFile(hit, destiniationDirectory,configObj);// destination path
			} else if (configObj.isPushToKafka()) {
				// code to push to kafka
			}

			Thread.sleep(time.SleepTime());
		}

	}

}
