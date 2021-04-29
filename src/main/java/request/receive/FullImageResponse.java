package request.receive;

import event.EventBus;
import server.Client;

public class FullImageResponse extends GenericResponse implements GenericResponseInterface {
	private final String titre;
	private final String data;
	private final int imageId;
	private final boolean isLikedByUser;
	private final int nbLike;
	private final boolean isOwnedByUser;

	public FullImageResponse(String titre, String data, int imageId, int nbLike, boolean isLikedByUser,
			boolean isOwnedByUser) {
		this.titre = titre;
		this.data = data;
		this.imageId = imageId;
		this.nbLike = nbLike;
		this.isLikedByUser = isLikedByUser;
		this.isOwnedByUser = isOwnedByUser;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifyFullImageListeners(this);
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

	public boolean isOwnedByUser() {
		return isOwnedByUser;
	}

	@Override
	public String toString() {
		return "FullImageResponse{" + "titre='" + titre + '\'' + ", imageId=" + imageId + ", isLikedByUser=" + isLikedByUser + ", nbLike=" + nbLike + '}';
	}
}