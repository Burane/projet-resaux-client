package request.send;

public class SearchRequest extends GenericRequest {
	private final String query;
	private final int limitFrom;
	private final int limitTo;

	public SearchRequest(String query, int limitFrom, int limitTo) {
		super(RequestType.SEARCH);
		this.query = query;
		this.limitFrom = limitFrom;
		this.limitTo = limitTo;
	}

}