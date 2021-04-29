package request.send;

public class LoginRequest extends GenericRequest {
	private final String username;
	private final String password;

	public LoginRequest(String username, String password) {
		super(RequestType.LOGIN);
		this.username = username;
		this.password = password;
	}

}