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

	public boolean findString(String s, boolean cs) {

		File file = null;

		WordExtractor extractor = null;

		try {

			file = new File(path);

			FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());

			HWPFDocument document = new HWPFDocument(inputStream);

			extractor = new WordExtractor(document);

			String[] fileData = extractor.getParagraphText();

			for (int i = 0; i < fileData.length; i++) {

				if (fileData[i] != null) {
					if (cs) {

						if (fileData[i].contains(s)) {

							document.close();
							inputStream.close();
							return true;

						}

					} else {

						if (fileData[i].toLowerCase().contains(s.toLowerCase())) {

							document.close();
							inputStream.close();
							return true;

						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

}
