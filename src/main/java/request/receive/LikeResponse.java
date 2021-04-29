package request.receive;

import event.EventBus;
import server.Client;

public class LikeResponse extends GenericResponse {
	private final int imageId;
	private final boolean isLikedByUser;

	public LikeResponse(int imageId, boolean isLikedByUser) {
		this.imageId = imageId;
		this.isLikedByUser = isLikedByUser;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifyLikeListeners(this);
	}

	@Override
	public String toString() {
		return "LikeResponse{" + "isLikedByUser=" + isLikedByUser + '}';
	}

	public boolean isLikedByUser() {
		return isLikedByUser;
	}

	public int getImageId() {
		return imageId;
	}
}