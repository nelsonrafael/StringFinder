package readers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSX {

	private String path;

	public ReadXLSX(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		FileInputStream fileStream = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			Iterator<Row> rowIt = sheet.iterator();
			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cs) {
						if (cell.toString().contains(s)) {
							workbook.close();
							fileStream.close();
							return true;
						}
					} else {
						if (cell.toString().toLowerCase().contains(s.toLowerCase())) {
							workbook.close();
							fileStream.close();
							return true;
						}
					}
				}
			}
			fileStream.close();
			workbook.close();
		}
		return false;
	}

}
