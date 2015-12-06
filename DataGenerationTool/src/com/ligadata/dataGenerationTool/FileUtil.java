package com.ligadata.dataGenerationTool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<String> listFilesForFolder(final File folder) {
		List<String> filePath = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String fileName=fileEntry.getPath().replace('\\','/').trim();
				if (fileName.toLowerCase().endsWith("csv"))
				{
				filePath.add(fileName);
				}
			}
		}
		return filePath;
	}	
}
