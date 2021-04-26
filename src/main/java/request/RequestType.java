package request;

public enum RequestType {
	AUTHENTIFICATION("authentification"),

	ERROR("error"),

	SUCCESS("success"),

	IMAGE("image"),

	SEARCH("search");

	private final String field;

	@Override
	public String toString() {
		return field;
	}

	RequestType(String field) {
		this.field = field;
	}
}
