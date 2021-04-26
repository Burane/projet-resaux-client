package request.receive;

import server.Client;

import java.util.ArrayList;

public class SearchResponse extends GenericResponse implements GenericResponseInterface {
	private final ArrayList<ImageResponse> images;

	public SearchResponse(ArrayList<ImageResponse> images) {
		this.images = images;
	}

	@Override
	public void handle(Client client) {
		// TODO transmettre l'instance au controller

	}


}
