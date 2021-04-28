package request.send;

public class FullImageRequest extends GenericRequest {
	private int imageId;

	public FullImageRequest(int imageId) {
		super(RequestType.FULLIMAGE);
		this.imageId = imageId;
	}

}