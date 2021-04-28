package request.receive;

import server.Client;

public class FullImageResponse extends GenericResponse implements GenericResponseInterface {
	private final String titre;
	private final String data;
	private final int imageId;
	private final boolean isLikedByUser;
	private final int nbLike;

	public FullImageResponse(String titre, String data, int imageId, int nbLike, boolean isLikedByUser) {
		this.titre = titre;
		this.data = data;
		this.imageId = imageId;
		this.nbLike = nbLike;
		this.isLikedByUser = isLikedByUser;
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

	public boolean isLikedByUser() {
		return isLikedByUser;
	}

	public int getNbLike() {
		return nbLike;
	}
}