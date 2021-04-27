package event.interfaces;

import request.receive.SuccessResponse;

public interface SuccessEventInterface {
	void onSuccessResponse(SuccessResponse successResponse);
}
