package com.ligadata.dataGenerationTool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ligadata.dataGenerationToolBean.ConfigObj;

public class JsonUtility {
	final Logger logger = Logger.getLogger(JsonUtility.class);

	public JSONObject ReadJsonFile(String messageFileString) {
		logger.info("Start reading JSON file");
		BufferedReader bufferedReader;
		JSONObject req = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(
					messageFileString));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {

				stringBuffer.append(line).append("\n");
			}
			bufferedReader.close();
			String configJsonString = stringBuffer.toString();
			req = new JSONObject(configJsonString);
			return req;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);

		} finally {
			logger.info("Done from reading JSON file");
		}
		return req;

	}

	public HashMap<String, String> ReadMessageFields(JSONObject req,
			ConfigObj configObj) {
		logger.info("Start parsing JSON file");
		RandomGenerator random = new RandomGenerator();
		JSONObject locs = req.getJSONObject("fields");
		JSONArray recs = locs.getJSONArray("field");
		HashMap<String, String> fields = new HashMap<String, String>();
		try {
			for (int i = 0; i < recs.length(); ++i) {
				JSONObject rec = recs.getJSONObject(i);
				String fieldName = rec.getString("name");
				String fieldType = rec.getString("type");
				int fieldLength = rec.getInt("length");
				String value = random.CheckType(fieldType, fieldLength,
						configObj);
				fields.put(fieldName, value);
			} // end for loop
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e);
		} finally {
			logger.info("Done from parsing JSON file");
		}
		return fields;
	}

	public ConfigObj CreateConfigObj(JSONObject configJson) {
		logger.info("Start set configuration");
		ConfigObj configObj = new ConfigObj();
		configObj.setDataGenerationRate(configJson
				.getDouble("DataGenerationRate"));
		configObj.setStartDate(configJson.getString("StartDate"));
		configObj.setEndDate(configJson.getString("EndDate"));
		configObj.setDurationInHours(configJson.getDouble("DurationInHours"));
		configObj.setDropInFiles(configJson.getBoolean("DropInFiles"));
		configObj.setPushToKafka(configJson.getBoolean("PushToKafka"));
		configObj.setFileSplitPer(configJson.getString("FileSplitPer"));
		configObj.setDelimiter(configJson.getString("Delimiter"));
		logger.info("The value of DataGenerationRate: "
				+ configJson.getDouble("DataGenerationRate"));
		logger.info("The value of StartDate: "
				+ configJson.getString("StartDate"));
		logger.info("The value of EndDate: " + configJson.getString("EndDate"));
		logger.info("The value of DurationInHours: "
				+ configJson.getDouble("DurationInHours"));
		logger.info("The value of DropInFiles: "
				+ configJson.getBoolean("DropInFiles"));
		logger.info("The value of PushToKafka: "
				+ configJson.getBoolean("PushToKafka"));
		logger.info("The value of FileSplitPer: "
				+ configJson.getString("FileSplitPer"));
		logger.info("The value of Delimiter: "
				+ configJson.getString("Delimiter"));
		logger.info("Done from set configuration");
		return configObj;
	}
}
