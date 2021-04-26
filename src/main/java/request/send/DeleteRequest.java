package request.send;

import java.util.ArrayList;

public class DeleteRequest extends GenericRequest {
	private ArrayList<Integer> imageId;

	public DeleteRequest(ArrayList<Integer> imageId) {
		super(RequestType.DELETE);
		this.imageId = imageId;
	}

	public DeleteRequest(int imageId) {
		super(RequestType.DELETE);
		this.imageId = new ArrayList<>();
		this.imageId.add(imageId);
	}
}
