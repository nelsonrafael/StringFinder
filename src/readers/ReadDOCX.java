package readers;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ReadDOCX {

	private String path;

	public ReadDOCX(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
		XWPFDocument document = new XWPFDocument(inputStream);
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		for (XWPFParagraph para : paragraphs) {
			if (cs) {
				if (para.getText().contains(s)) {
					document.close();
					inputStream.close();
					return true;
				}
			} else {
				if (para.getText().toLowerCase().contains(s.toLowerCase())) {
					document.close();
					inputStream.close();
					return true;
				}
			}
		}
		document.close();
		inputStream.close();
		return false;
	}
}
