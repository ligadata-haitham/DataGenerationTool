package com.ligadata.dataGenerationTool;

import org.apache.commons.lang3.RandomStringUtils;

public class randomGenerator {

	public String CheckType(String FieldType, int length) {
		String randomValue = null;
		switch (FieldType) {
		case "hybrid":
			randomValue = RandomHybrid(length);
			break;
		case "string":
			randomValue = RandomString(length);
			break;
		case "integer":
			randomValue = RandomNumeric(length);
			break;
		default:
			randomValue = null;
			break;
		}
		return randomValue;
	}

	public String RandomHybrid(int length) {

		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedAlphabetic = RandomStringUtils.random(length,
				useLetters, useNumbers);
		// System.out.println(generatedAlphabetic);
		return new String(generatedAlphabetic);
	}

	public String RandomString(int length) {

		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters,
				useNumbers);
		// System.out.println(generatedString);
		return new String(generatedString);
	}

	public String RandomNumeric(int length) {

		String generatedNumeric = RandomStringUtils.randomNumeric(length);
		// System.out.println(generatedNumeric);
		return new String(generatedNumeric);
	}

}
