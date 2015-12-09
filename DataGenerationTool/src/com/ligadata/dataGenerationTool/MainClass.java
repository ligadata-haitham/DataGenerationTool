package com.ligadata.dataGenerationTool;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import com.ligadata.dataGenerationToolBean.ConfigObj;
import com.ligadata.dataGenerationToolBean.FileNameConfig;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class MainClass {

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ParseException
	 */
	final static Logger logger = Logger.getLogger(MainClass.class);

	public static void main(String[] args) throws InterruptedException,
			IOException, ParseException {
		// create object
		JsonUtility json = new JsonUtility();
		GenerateRecord record = new GenerateRecord();
		FilesUtility file = new FilesUtility();
		TimeUtility time = new TimeUtility();
		FileNameConfig fileNameConfig = new FileNameConfig();

		// read configuration file
		// logger.info("Reading config file from " + args[0]);
		String configFileLocation = "C:/Users/Yousef/Documents/GitHub/DataGenerationTool/JsonFiles/DataGenerationConfig.json";
		JSONObject configJson = json.ReadJsonFile(configFileLocation);
		ConfigObj configObj = json.CreateConfigObj(configJson);

		// initialize variables
		String templateFileLocation = configObj.getTemplatePath();
		String destiniationDirectory = configObj.getDestiniationPath();

		// read message file
		JSONObject templateJson = json.ReadJsonFile(templateFileLocation);
		// DurationInHours
		double loopEndTime = time.RunDurationTime(configObj);
		HashMap<String, String> fields = new HashMap<String, String>();
		while (System.currentTimeMillis() < loopEndTime) {
			fields = json.ReadMessageFields(templateJson, configObj);
			String hit = record.GenerateHit(fields, configObj.getDelimiter());

			if (configObj.isDropInFiles()) {
				// write hit to file
				file.writeFile(hit, destiniationDirectory,
						configObj.getFileSplitPer(), fileNameConfig);
			} else if (configObj.isPushToKafka()) {
				// code to push to kafka
			}
			Thread.sleep(time.SleepTime(configObj.getDataGenerationRate()));
		}
		logger.info("Done ...");
	}

}
