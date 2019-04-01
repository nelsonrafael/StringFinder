package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadXLT {

	private String path;

	public ReadXLT(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) {
		try {
			FileInputStream fileStream = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIt = sheet.iterator();
				while (rowIt.hasNext()) {
					Row row = rowIt.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cs) {
							if (cell.toString().contains(s)) {
								workbook.close();
								return true;
							}
						} else {
							if (cell.toString().toLowerCase().contains(s.toLowerCase())) {
								workbook.close();
								return true;
							}
						}
					}
				}
				workbook.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR ReadXLT - FileNotFoundException - " + this.path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
