package request.receive;

import event.EventBus;
import server.Client;

public class ErrorResponse extends GenericResponse implements GenericResponseInterface {
	private final String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifyErrorListeners(this);
	}

	@Override
	public String toString() {
		return "ErrorResponse{" + "message='" + message + '\'' + '}';
	}
}
