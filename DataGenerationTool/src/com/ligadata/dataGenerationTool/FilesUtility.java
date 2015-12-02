package com.ligadata.dataGenerationTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.ligadata.dataGenerationToolBean.ConfigObj;

public class FilesUtility {

	public void writeFile(String record, String Path) throws IOException {

		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH").format(System
				.currentTimeMillis());

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
