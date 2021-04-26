package request.send;

public class SearchRequest extends GenericRequest {
	private String query;
	private int limitFrom;
	private int limitTo;

	public SearchRequest(String query, int limitFrom, int limitTo) {
		super(RequestType.SEARCH);
		this.query = query;
		this.limitFrom = limitFrom;
		this.limitTo = limitTo;
	}

}
