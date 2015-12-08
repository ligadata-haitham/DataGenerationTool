package com.ligadata.dataGenerationTool;

import org.apache.log4j.Logger;
import com.ligadata.dataGenerationToolBean.ConfigObj;
import com.ligadata.dataGenerationToolBean.FileNameConfig;

public class TimeUtility {
	final Logger logger = Logger.getLogger(TimeUtility.class);

	public String CheckTimeUnit(String timeUnit) {
		logger.info("Checking time unit...");

		String retValue;

		switch (timeUnit.toLowerCase().substring(timeUnit.length() - 1).trim()
				.toCharArray()[0]) {
		case 'm':
			logger.info("End of checking time unit");
			retValue = "yyyy-MM-dd-HH-mm";
			break;
		case 'h':
			logger.info("End of checking time unit");
			retValue = "yyyy-MM-dd-HH";
			break;
		case 's':
			logger.info("End of checking time unit");
			retValue = "yyyy-MM-dd-HH-mm-ss";
			break;
		case 'd':
			logger.info("End of checking time unit");
			retValue = "yyyy-MM-dd";
			break;
		default:
			retValue = "yyyy-MM-dd-HH";
		}

		logger.info("Time unit : " + retValue);
		return retValue;

	}

	public boolean CreateNewFile(ConfigObj configObj,
			FileNameConfig fileNameConfig, double currentTime) {

		String fileSplitPer = configObj.getFileSplitPer();
		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,
				fileSplitPer.length() - 1));
		double multiplyFactor;
		double endTime;

		String temp = fileSplitPer.toLowerCase().substring(
				fileSplitPer.length() - 1);
		logger.info("Check the need to create a new file...");
		switch (temp.trim().toCharArray()[0]) { // fileSplitPer.substring(fileSplitPer.length()
												// - 1)
		case 'd':
			multiplyFactor = 1000 * 60 * 60 * 24;
			break;
		case 'h':
			multiplyFactor = 1000 * 60 * 60;
			break;
		case 'm':
			multiplyFactor = 1000 * 60;
			break;
		case 's':
			multiplyFactor = 1000;
			break;
		default:
			multiplyFactor = 1000 * 60 * 60;
		}

		endTime = currentTime + (multiplyFactor * timeAmountForFileSplit);

		if (fileNameConfig.getNextFileTime() == 0) {
			fileNameConfig.setNextFileTime(endTime);
		}

		if (currentTime >= fileNameConfig.getNextFileTime()) { // Double.compare(currentTime,FilesUtility.nextFileTime
																// ) < 0
			fileNameConfig.setOldFileTime(fileNameConfig.getNextFileTime());
			fileNameConfig.setNextFileTime(endTime);
			logger.info("Create new file : " + true);
			return true;
		} else {
			logger.info("Create new file : " + false);
			return false;
		}

	}

	public double RunDurationTime(ConfigObj configObj) {
		// this method used to find run duration for tool
		logger.info("Calculate run duration time...");
		double currentTime = System.currentTimeMillis();
		double loopEndTime = currentTime
				+ (3600000 * (configObj.getDurationInHours()));
		logger.info("LoopEndTime is : " + loopEndTime);
		return loopEndTime;

	}

	public long SleepTime(ConfigObj configObj) {
		// this method used to calculate sleep duration
		long sleepInMS = (long) (1000.0 / configObj.getDataGenerationRate());
		return sleepInMS;
	}
}
