package readers;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.extractor.OldExcelExtractor;

public class ReadXLS {

	private String path;

	public ReadXLS(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		FileInputStream fileStream = new FileInputStream(new File(path));
		OldExcelExtractor excelExtractor = new OldExcelExtractor(fileStream);
		String[] parts = excelExtractor.getText().split("\n");
		for (int i = 0; i < parts.length; i++) {
			if (cs) {
				if (parts[i].contains(s)) {
					excelExtractor.close();
					fileStream.close();
					return true;
				}
			} else {
				if (parts[i].toLowerCase().contains(s.toLowerCase())) {
					excelExtractor.close();
					fileStream.close();
					return true;
				}
			}
		}
		excelExtractor.close();
		fileStream.close();
		return false;
	}

}
