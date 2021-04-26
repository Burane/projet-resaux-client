package request.send;

public class LoginRequest extends GenericRequest {
	private String username;
	private String password;

	public LoginRequest(String username, String password) {
		super(RequestType.LOGIN);
		this.username = username;
		this.password = password;
	}

}
