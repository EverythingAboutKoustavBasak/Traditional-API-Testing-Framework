package utiliity;

public class AuthN {
	
	private static final String bearerToken = System.getenv("GITHUB_TOKEN");

	public static String getBearertoken() {
		
		if (bearerToken == null || bearerToken.isBlank()) {
            throw new IllegalStateException(
                "GITHUB_TOKEN environment variable is missing"
            );
        }
		return bearerToken;
	}


}
