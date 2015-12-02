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
		// read configuration file
		String configFileLocation = args[0];// "C:\\Users\\haitham-pc\\Desktop\\DataGenerationConfig.json";
		JSONObject configJson = JsonUtility.ReadJsonFile(configFileLocation);
		ConfigObj configObj = JsonUtility.JsonConfig(configJson);
		// DurationInHours
		double currentTime = System.currentTimeMillis();
		double loopEndTime = currentTime
				+ (3600000 * (configObj.getDurationInHours()));

		HashMap<String, String> fields = new HashMap<String, String>();

		while (System.currentTimeMillis() < loopEndTime) {
			fields = JsonUtility.ReadMessageFields(configJson);
			String record = GenerateRecord.GenerateHit(fields,
					configObj.getDelimiter());
			System.out.println(record);

			if (configObj.isDropInFiles()) {
				FilesUtility.writeFile(record, args[2]);// destination path
			} else if (configObj.isPushToKafka()) {
				// code to push to kafka
			}

			double sleepInMS = 1000.0 / configObj.getDataGenerationRate();
			Thread.sleep((long) sleepInMS);
		}

	}

}
