package com.ligadata.dataGenerationTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class FilesUtility {

	public void writeFile(String record, String Path, ConfigObj configObj) throws IOException {

		TimeUtility time = new TimeUtility();
		
		String fileSplitPer = configObj.getFileSplitPer();
		String fileNameFormat = time.CheckTimeUnit(fileSplitPer.substring(fileSplitPer.length() - 1));
		int timeAmountForFileSplit = Integer.valueOf(fileSplitPer.substring(0,fileSplitPer.length()-1));
		
		// add code to change file name based on time 
		
		String fileName="" ;
		
		
		File file = new File(Path + fileName);
		System.out.println("filename" + file);

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
