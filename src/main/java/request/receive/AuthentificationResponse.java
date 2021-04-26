package request.receive;

import server.Client;

public class AuthentificationResponse extends GenericResponse implements GenericResponseInterface {
	private final boolean success;

	public AuthentificationResponse(boolean success) {
		this.success = success;
	}

	@Override
	public void handle(Client client) {

	}
}
