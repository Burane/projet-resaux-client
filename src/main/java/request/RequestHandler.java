package request;

import com.google.gson.Gson;
import gson.RequestDeserializer;
import request.receive.GenericResponse;
import request.receive.GenericResponseInterface;
import server.Client;

public class RequestHandler {
	private final Client client;

	public RequestHandler(Client client) {
		this.client = client;
	}

	public void handle(String rawRequest) {

		Gson gson = RequestDeserializer.getDeserializer();
		GenericResponseInterface request;
		try {
			request = gson.fromJson(rawRequest, GenericResponse.class);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}

		request.handle(client);

	}
}
