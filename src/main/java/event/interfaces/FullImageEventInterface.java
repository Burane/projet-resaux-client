package event.interfaces;

import request.receive.FullImageResponse;

public interface FullImageEventInterface {
	void onFullImageResponse(FullImageResponse fullImageResponse);

}