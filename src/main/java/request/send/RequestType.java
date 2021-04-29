package request.send;

public enum RequestType {

	DELETE("delete"),
	DISCONNECT("disconnect"),
	FULLIMAGE("fullImage"),
	LOGIN("login"),
	REGISTER("register"),
	SEARCH("search"),
	UPLOAD("upload"),
	MYIMAGESEARCH("myImageSearch"),
	LIKE("like"),
	SEARCHPERDAY("searchPerDay");


	private final String field;

	RequestType(String field) {
		this.field = field;
	}
	@Override
	public String toString(){
		return field;
	}
}