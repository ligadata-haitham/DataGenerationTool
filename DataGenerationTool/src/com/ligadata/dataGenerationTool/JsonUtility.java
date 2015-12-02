package com.ligadata.dataGenerationTool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class JsonUtility {

	public static JSONObject ReadJsonFile(String messageFileString)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				messageFileString));
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;

		while ((line = bufferedReader.readLine()) != null) {

			stringBuffer.append(line).append("\n");
		}
		System.out.println(stringBuffer);

		String configJsonString = stringBuffer.toString();
		JSONObject req = new JSONObject(configJsonString);
		return req;

	}

	public static HashMap<String, String> ReadMessageFields(JSONObject req) {
		JSONObject locs = req.getJSONObject("fields");
		JSONArray recs = locs.getJSONArray("field");
		HashMap<String, String> fields = new HashMap<String, String>();
		for (int i = 0; i < recs.length(); ++i) {
			JSONObject rec = recs.getJSONObject(i);
			String fieldName = rec.getString("name");
			String fieldType = rec.getString("type");
			int fieldLength = rec.getInt("length");
			String value = randomGenerator.CheckType(fieldType, fieldLength);
			fields.put(fieldName, value);
		} // end for loop
		return fields;
	}

	public static ConfigObj JsonConfig(JSONObject configJson) {
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
		return configObj;
	}
}