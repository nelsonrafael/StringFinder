package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.extractor.OldExcelExtractor;
import org.apache.poi.hssf.record.RecordInputStream.LeftoverDataException;
import org.apache.poi.util.RecordFormatException;

public class ReadXLS {

	private String path;

	public ReadXLS(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) {
		try {
			FileInputStream fileStream = new FileInputStream(new File(path));
			
			OldExcelExtractor excelExtractor = null;
			
			try {
				excelExtractor = new OldExcelExtractor(fileStream);
			}catch (IllegalArgumentException e) {
				System.out.println("ERROR ReadXLS - IllegalArgumentException (File does not begin with a BOF?) - " + this.path);
				return false;
			}catch (RecordFormatException e) {
				System.out.println("ERROR ReadXLS - RecordFormatException (The content of an excel record cannot exceed 8224 bytes) - " + this.path);
				return false;
			}
			
			String[] parts = null;

			try {
				parts = excelExtractor.getText().split("\n");
			} catch (LeftoverDataException e) {
				System.out.println("ERROR ReadXLS - LeftoverDataException (possible data corruption) - " + this.path);
			}

			excelExtractor.close();

			for (int i = 0; i < parts.length; i++) {
				if (cs) {
					if (parts[i].contains(s)) {
						return true;
					}
				} else {
					if (parts[i].toLowerCase().contains(s.toLowerCase())) {
						return true;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR ReadXLS - FileNotFoundException - " + this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
