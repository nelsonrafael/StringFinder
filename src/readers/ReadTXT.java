package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadTXT {

	private String path;

	public ReadTXT(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) {
		File inputFile = new File(path);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (cs) {
					if (line.contains(s)) {
						return true;
					}
				} else {
					if (line.toLowerCase().contains(s.toLowerCase())) {
						return true;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR ReadTXT - FileNotFoundException - " + this.path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
