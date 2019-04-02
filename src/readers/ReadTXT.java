package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTXT {

	private String path;

	public ReadTXT(String p) {
		this.path = p;
	}

	public boolean findString(String s, boolean cs) throws Exception {
		File inputFile = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		while ((line = br.readLine()) != null) {
			if (cs) {
				if (line.contains(s)) {
					br.close();
					return true;
				}
			} else {
				if (line.toLowerCase().contains(s.toLowerCase())) {
					br.close();
					return true;
				}
			}
		}
		br.close();
		return false;
	}

}
