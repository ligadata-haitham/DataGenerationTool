package com.ligadata.dataGenerationTool;

import java.text.SimpleDateFormat;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class TimeUtility {

	public String CheckTimeFormat(String timeUnit) {// return date format to put
													// it as name for file
		switch (timeUnit.toLowerCase().substring(timeUnit.length() - 1)) {
		case "m":
			return "yyyy-MM-dd-HH-mm";
		case "h":
			return "yyyy-MM-dd-HH";
		case "s":
			return "yyyy-MM-dd-HH-mm-ss";
		case "d":
			return "yyyy-MM-dd";
		default:
			return "yyyy-MM-dd-HH";
		}
	}

	public boolean CheckCurrentTime(String endTime, String timeFormat) {
		// this method used to check if current time equal end time to create a
		// new file
		String currentTime = new SimpleDateFormat(timeFormat).format(System
				.currentTimeMillis());
		if (currentTime.equalsIgnoreCase(endTime))
			return true;
		else
			return false;

	}

	public double RunDurationTime() {
		// this method used to find run duration for tool
		ConfigObj config = new ConfigObj();
		double currentTime = System.currentTimeMillis();
		double loopEndTime = currentTime
				+ (3600000 * (config.getDurationInHours()));
		return loopEndTime;

	}

	public long SleepTime() {
		// this method used to calculate sleep duration
		ConfigObj config = new ConfigObj();
		long sleepInMS = (long) (1000.0 / config.getDataGenerationRate());
		return sleepInMS;
	}
}
