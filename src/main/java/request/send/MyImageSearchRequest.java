package request.send;

public class MyImageSearchRequest extends GenericRequest {
	private String query;
	private int limitFrom;
	private int limitTo;

	public MyImageSearchRequest(String query, int limitFrom, int limitTo) {
		super(RequestType.MYIMAGESEARCH);
		this.query = query;
		this.limitFrom = limitFrom;
		this.limitTo = limitTo;
	}

}