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
		// create object
		JsonUtility json = new JsonUtility();
		GenerateRecord record = new GenerateRecord();
		FilesUtility file = new FilesUtility();
		TimeUtility time = new TimeUtility();
		// read configuration file
		String configFileLocation = args[0];
		JSONObject configJson = json.ReadJsonFile(configFileLocation);
		ConfigObj configObj = json.JsonConfig(configJson);
		// DurationInHours
		double loopEndTime = time.RunDurationTime();

		HashMap<String, String> fields = new HashMap<String, String>();
		while (System.currentTimeMillis() < loopEndTime) {
			fields = json.ReadMessageFields(configJson);
			String hit = record.GenerateHit(fields, configObj.getDelimiter());
			System.out.println(hit);

			if (configObj.isDropInFiles()) {
				file.writeFile(hit, args[2]);// destination path
			} else if (configObj.isPushToKafka()) {
				// code to push to kafka
			}

			Thread.sleep(time.SleepTime());
		}

	}

}
