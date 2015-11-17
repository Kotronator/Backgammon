package backgammon;

import java.io.InputStream;

public class FileLoader {
	
	public static InputStream loadFile(String str)
	{
		
		InputStream stream =FileLoader.class.getResourceAsStream("/resources/"+str);
//		
		return stream;
				//.getResourceAsStream(str);
	}

}
