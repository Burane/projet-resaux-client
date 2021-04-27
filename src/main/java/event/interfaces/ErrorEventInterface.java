package event.interfaces;

import request.receive.ErrorResponse;

public interface ErrorEventInterface {
	void onErrorResponse(ErrorResponse errorResponse);
}
