package request.send;

public class ImageRequest extends GenericRequest {
	private int imageId;

	public ImageRequest(int imageId) {
		super(RequestType.IMAGE);
		this.imageId = imageId;
	}

}
