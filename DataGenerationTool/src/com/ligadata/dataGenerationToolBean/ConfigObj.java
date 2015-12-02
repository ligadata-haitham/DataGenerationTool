package com.ligadata.dataGenerationToolBean;

public class ConfigObj {

	double dataGenerationRate;
	String startDate;
	String endDate;
	double durationInHours;
	boolean dropInFiles;
	boolean pushToKafka;
	String FileSplitPer;
	String delimiter;

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public double getDataGenerationRate() {
		return dataGenerationRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isDropInFiles() {
		return dropInFiles;
	}

	public void setDropInFiles(boolean dropInFiles) {
		this.dropInFiles = dropInFiles;
	}

	public double getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(double durationInHours) {
		this.durationInHours = durationInHours;
	}

	public boolean isPushToKafka() {
		return pushToKafka;
	}

	public void setPushToKafka(boolean pushToKafka) {
		this.pushToKafka = pushToKafka;
	}

	public String getFileSplitPer() {
		return FileSplitPer;
	}

	public void setFileSplitPer(String FileSplitPer) {
		this.FileSplitPer = FileSplitPer;
	}

	public void setDataGenerationRate(double dataGenerationRate) {
		this.dataGenerationRate = dataGenerationRate;
	}

}
