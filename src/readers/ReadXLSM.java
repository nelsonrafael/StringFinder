package readers;

import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;

import miscellaneous.XSSFEventBasedExcelExtractor;

public class ReadXLSM {

	private String path;

	public ReadXLSM(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		POIXMLTextExtractor extractor = new XSSFEventBasedExcelExtractor(this.path);
		String[] parts = extractor.getText().split("\n");
		for (int i = 0; i < parts.length; i++) {
			if (cs) {
				if (parts[i].contains(s)) {
					extractor.close();
					return true;
				}
			} else {
				if (parts[i].toLowerCase().contains(s.toLowerCase())) {
					extractor.close();
					return true;
				}
			}
		}
		extractor.close();
		return false;
	}

}
