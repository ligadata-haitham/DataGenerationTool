package com.ligadata.dataGenerationTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.ligadata.dataGenerationToolBean.ConfigObj;
import com.ligadata.dataGenerationToolBean.FileNameConfig;

public class FilesUtility {
	

	public void writeFile(String record, String Path, ConfigObj configObj,FileNameConfig fileNameConfig) throws IOException {

		TimeUtility time = new TimeUtility();
//		FileNameConfig fileNameConfig = new FileNameConfig();
//		
		String fileSplitPer = configObj.getFileSplitPer();
		String fileNameFormat = time.CheckTimeUnit(fileSplitPer.substring(fileSplitPer.length() - 1));
//		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,fileSplitPer.length()-1));
		
		double currentTime = System.currentTimeMillis();
		
		
		String fileName;
<<<<<<< HEAD
		//boolean cond = fileNameConfig.getOldFileTime() == 0;
=======
		
>>>>>>> origin/master
		if (fileNameConfig.getOldFileTime() == 0) {
			fileNameConfig.setOldFileTime(currentTime);
		}
		//String reportDate = df.format(today);
		
		if (time.CreateNewFile(configObj,fileNameConfig,currentTime)) {
			fileName = new SimpleDateFormat(fileNameFormat).format(currentTime);
		} else{
			fileName= new SimpleDateFormat(fileNameFormat).format(fileNameConfig.getOldFileTime());
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
