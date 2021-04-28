package request.send;

public class LikeRequest extends GenericRequest {

	private final int imageId;

	public LikeRequest(int imageId) {
		super(RequestType.LIKE);
		this.imageId = imageId;
	}

	@Override
	public String toString() {
		return "LikeRequest{" + "imageId=" + imageId + '}';
	}

	public int getImageId() {
		return imageId;
	}
}