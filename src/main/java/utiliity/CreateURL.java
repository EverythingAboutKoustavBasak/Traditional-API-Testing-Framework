package utiliity;

public class CreateURL {
	
	private final static String baseURI = "https://api.github.com";
	
	public static String getBaseURI() {
		return baseURI;
	}
	
	public static String getBaseURI(String resourcePah) {
		return baseURI+resourcePah;
	}

}
