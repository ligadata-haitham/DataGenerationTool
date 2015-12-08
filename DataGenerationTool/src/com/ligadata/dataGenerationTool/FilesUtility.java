package com.ligadata.dataGenerationTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.ligadata.dataGenerationToolBean.ConfigObj;
import com.ligadata.dataGenerationToolBean.FileNameConfig;
import org.apache.log4j.Logger;

public class FilesUtility {

	final Logger logger = Logger.getLogger(FilesUtility.class);

	public void writeFile(String record, String Path, ConfigObj configObj,
			FileNameConfig fileNameConfig) {
		logger.info("Writting to file ...");
		TimeUtility time = new TimeUtility();
		String fileSplitPer = configObj.getFileSplitPer();
		String fileNameFormat = time.CheckTimeUnit(fileSplitPer
				.substring(fileSplitPer.length() - 1));

		double currentTime = System.currentTimeMillis();

		try {
			String fileName;
			if (fileNameConfig.getOldFileTime() == 0) {
				fileNameConfig.setOldFileTime(currentTime);
			}
			// String reportDate = df.format(today);

			if (time.CreateNewFile(configObj, fileNameConfig, currentTime)) {
				fileName = new SimpleDateFormat(fileNameFormat)
						.format(currentTime);
			} else {
				fileName = new SimpleDateFormat(fileNameFormat)
						.format(fileNameConfig.getOldFileTime());
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		} finally {
			logger.info("Writting to file successful.");
		}

	}

}
