package readers;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class ReadDOC {

	private String path;

	public ReadDOC(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
		HWPFDocument document = new HWPFDocument(inputStream);
		WordExtractor extractor = new WordExtractor(document);
		String[] fileData = extractor.getParagraphText();
		for (int i = 0; i < fileData.length; i++) {
			if (fileData[i] != null) {
				if (cs) {
					if (fileData[i].contains(s)) {
						extractor.close();
						document.close();
						inputStream.close();
						return true;
					}
				} else {
					if (fileData[i].toLowerCase().contains(s.toLowerCase())) {
						extractor.close();
						document.close();
						inputStream.close();
						return true;
					}
				}
			}
		}
		extractor.close();
		document.close();
		inputStream.close();
		return false;
	}

}
