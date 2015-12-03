package com.ligadata.dataGenerationTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class FilesUtility {
	
	public static double nextFileTime ;
	public static double oldFileTime ;

	public void writeFile(String record, String Path, ConfigObj configObj) throws IOException {

		TimeUtility time = new TimeUtility();
//		
		String fileSplitPer = configObj.getFileSplitPer();
		String fileNameFormat = time.CheckTimeUnit(fileSplitPer.substring(fileSplitPer.length() - 1));
//		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,fileSplitPer.length()-1));
		
		double currentTime = System.currentTimeMillis();
		
		String fileName;
		if (FilesUtility.oldFileTime == 0) {
			FilesUtility.oldFileTime = currentTime;
		}
		//String reportDate = df.format(today);
		
		if (time.CreateNewFile(configObj,fileNameFormat,currentTime)) {
			fileName = new SimpleDateFormat(fileNameFormat).format(currentTime);
		} else{
			fileName= new SimpleDateFormat(fileNameFormat).format(FilesUtility.oldFileTime);
		}
		
		
		File file = new File(Path + fileName);

		// if file doesnt exists, then create it
		if (!(file.exists())) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(record + "\n");
		bw.close();

	}


}
