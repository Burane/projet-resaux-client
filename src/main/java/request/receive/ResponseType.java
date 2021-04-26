package request.receive;

public enum ResponseType {
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

	ResponseType(String field) {
		this.field = field;
	}
}
