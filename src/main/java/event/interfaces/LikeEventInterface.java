package event.interfaces;

import request.receive.LikeResponse;

public interface LikeEventInterface {
	void onLikeResponse(LikeResponse likeResponse);
}