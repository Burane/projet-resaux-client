package request.receive;

import server.Client;

public class ErrorResponse extends GenericResponse implements GenericResponseInterface {
	private final String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	@Override
	public void handle(Client client) {

	}
}
