package request.send;

public class RegisterRequest extends GenericRequest {
	private final String username;
	private final String password;

	public RegisterRequest(String username, String password) {
		super(RequestType.REGISTER);
		this.username = username;
		this.password = password;
	}

}