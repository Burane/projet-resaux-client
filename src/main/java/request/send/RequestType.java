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
	LIKE("like");

	private String field;

	RequestType(String field) {
		this.field = field;
	}
	@Override
	public String toString(){
		return field;
	}
}