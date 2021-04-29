package request.send;

public class MyImageSearchRequest extends GenericRequest {
	private final String query;
	private final int limitFrom;
	private final int limitTo;

	public MyImageSearchRequest(String query, int limitFrom, int limitTo) {
		super(RequestType.MYIMAGESEARCH);
		this.query = query;
		this.limitFrom = limitFrom;
		this.limitTo = limitTo;
	}

}