package request.receive;

import request.GenericRequest;
import request.GenericRequestInterface;
import server.Client;

public class ImageRequest extends GenericRequest implements GenericRequestInterface {
	private int imageId;

	@Override
	public void handle(Client client) {


	}
}
