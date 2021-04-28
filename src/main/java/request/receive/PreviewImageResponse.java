package request.receive;

import server.Client;

public class PreviewImageResponse extends GenericResponse implements GenericResponseInterface {
	private final String titre;
	private final String data;
	private final int imageId;

	public PreviewImageResponse(String titre, String data, int imageId) {
		this.titre = titre;
		this.data = data;
		this.imageId = imageId;
	}

	@Override
	public void handle(Client client) {
		// TODO transmettre l'instance au controller

	}

	public String getTitre() {
		return titre;
	}

	public String getData() {
		return data;
	}

	public int getImageId() {
		return imageId;
	}
}