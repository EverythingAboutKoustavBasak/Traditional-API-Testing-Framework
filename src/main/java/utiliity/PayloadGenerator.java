package utiliity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PayloadGenerator {
	
	public static String generateStringPayload(String fileName) throws IOException {
		 
//		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + fileName;
		Path filePath = Paths.get(
	                System.getProperty("user.dir"),
	                "src",
	                "test",
	                "resources",
	                "payloads",
	                fileName
	        );
		
//		return new String(Files.readAllBytes(filePath));
		return Files.readString(filePath);
		
	}

}
