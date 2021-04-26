package request.receive;

import request.GenericRequest;
import request.GenericRequestInterface;
import server.Client;

import java.util.ArrayList;

public class SearchRequest extends GenericRequest implements GenericRequestInterface {
	private String query;
	private int limitFrom;
	private int limitTo;
	private ArrayList<String> keywords;

	@Override
	public void handle(Client client) {

	}


}
