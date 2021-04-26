package request.send;

import java.util.ArrayList;

public class UploadRequest extends GenericRequest {
	private final String data;
	private final String titre;
	private final ArrayList<String> tags;

	public UploadRequest(String data, String titre, ArrayList<String> tags) {
		super(RequestType.UPLOAD);
		this.data = data;
		this.titre = titre;
		this.tags = tags;
	}

	public UploadRequest(String data, String titre, String tag) {
		super(RequestType.UPLOAD);
		this.data = data;
		this.titre = titre;
		this.tags = new ArrayList<>();
		this.tags.add(tag);
	}

}
