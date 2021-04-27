package event.interfaces;

import request.receive.AuthentificationResponse;

public interface AuthentificationEventInterface {
	void onAuthentificationResponse(AuthentificationResponse authentificationResponse);
}
