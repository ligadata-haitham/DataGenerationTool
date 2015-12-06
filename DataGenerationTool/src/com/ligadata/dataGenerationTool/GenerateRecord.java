package com.ligadata.dataGenerationTool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GenerateRecord {

	public String GenerateHit(HashMap<String, String> record, String Delimiter) {
		String hit = "";
		// Get a set of the entries
		Set<Entry<String, String>> set = record.entrySet();
		// Get an iterator
		Iterator<Entry<String, String>> i = set.iterator();
		// Display elements
		while (i.hasNext()) {
			Map.Entry<String, String> line =  i.next();
			hit = hit + line.getKey() + Delimiter + line.getValue() + Delimiter;
			// System.out.print(line.getKey() + ": ");
			// System.out.println(line.getValue());
		}
		return hit;

	}
}
