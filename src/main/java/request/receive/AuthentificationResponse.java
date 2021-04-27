package request.receive;

import event.EventBus;
import server.Client;

public class AuthentificationResponse extends GenericResponse implements GenericResponseInterface {
	private final boolean success;

	public AuthentificationResponse(boolean success) {
		this.success = success;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifyAuthentificationListeners(this);
	}

	@Override
	public String toString() {
		return "AuthentificationResponse{" + "success=" + success + '}';
	}

	public boolean isSuccess() {
		return success;
	}
}
