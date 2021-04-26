package request.receive;

import server.Client;

public class SuccessResponse extends GenericResponse implements GenericResponseInterface {
	private final String message;

	public SuccessResponse(String message) {
		this.message = message;
	}

	@Override
	public void handle(Client client) {

	}
}