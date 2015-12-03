package com.ligadata.dataGenerationTool;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class TimeUtility {

	public String CheckTimeUnit(String timeUnit) {// return date format to put
													// it as name for file
		switch (timeUnit.toLowerCase().substring(timeUnit.length() - 1).trim().toCharArray()[0]) {
		case 'm':
			return "yyyy-MM-dd-HH-mm";
		case 'h':
			return "yyyy-MM-dd-HH";
		case 's':
			return "yyyy-MM-dd-HH-mm-ss";
		case 'd':
			return "yyyy-MM-dd";
		default:
			return "yyyy-MM-dd-HH";
		}
	}

	public boolean CreateNewFile(ConfigObj configObj, String timeFormat,
			double currentTime) {
		// this method used to check if current time equal end time to create a
		// new file
		// String currentTime = new
		// SimpleDateFormat(timeFormat).format(System.currentTimeMillis());

		String fileSplitPer = configObj.getFileSplitPer();
		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,
				fileSplitPer.length() - 1));
		double multiplyFactor;
		double endTime;


		String temp = fileSplitPer.toLowerCase().substring(fileSplitPer.length() - 1);		
		
		switch (temp.trim().toCharArray()[0]) { //fileSplitPer.substring(fileSplitPer.length() - 1) 
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

		endTime = currentTime + ( multiplyFactor * timeAmountForFileSplit);	

		if (FilesUtility.nextFileTime == 0) {
			FilesUtility.nextFileTime = endTime;
		}


		if ( (long)currentTime >= (long)FilesUtility.nextFileTime) {   //  Double.compare(currentTime,FilesUtility.nextFileTime ) < 0
			FilesUtility.oldFileTime = FilesUtility.nextFileTime;
			FilesUtility.nextFileTime = endTime;
			return true;
		} else {
			return false;
		}

	}

	public double RunDurationTime(ConfigObj configObj) {
		// this method used to find run duration for tool
		double currentTime = System.currentTimeMillis();
		double loopEndTime = currentTime
				+ (3600000 * (configObj.getDurationInHours()));
		return loopEndTime;

	}

	public long SleepTime(ConfigObj configObj) {
		// this method used to calculate sleep duration
		long sleepInMS = (long) (1000.0 / configObj.getDataGenerationRate());
		return sleepInMS;
	}
}