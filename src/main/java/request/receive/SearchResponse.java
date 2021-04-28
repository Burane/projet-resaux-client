package request.receive;

import event.EventBus;
import server.Client;

import java.util.ArrayList;

public class SearchResponse extends GenericResponse implements GenericResponseInterface {
	private final ArrayList<PreviewImageResponse> images;

	public SearchResponse(ArrayList<PreviewImageResponse> images) {
		this.images = images;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifySearchListeners(this);
	}

	public ArrayList<PreviewImageResponse> getImages() {
		return images;
	}
}