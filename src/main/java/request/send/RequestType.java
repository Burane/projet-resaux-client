package request.send;

public enum RequestType {

	DELETE("delete"),
	DISCONNECT("disconnect"),
	IMAGE("image"),
	LOGIN("login"),
	REGISTER("register"),
	SEARCH("search"),
	UPLOAD("upload");

	private String field;

	RequestType(String field) {
		this.field = field;
	}
	@Override
	public String toString(){
		return field;
	}
}
