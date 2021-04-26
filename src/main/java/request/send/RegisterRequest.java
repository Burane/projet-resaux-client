package request.send;

public class RegisterRequest extends GenericRequest {
	private String username;
	private String password;

	public RegisterRequest(String username, String password) {
		super(RequestType.REGISTER);
		this.username = username;
		this.password = password;
	}

}
