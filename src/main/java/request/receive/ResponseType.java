package request.receive;

public enum ResponseType {
	AUTHENTIFICATION("authentification"),

	ERROR("error"),

	SUCCESS("success"),

	PREVIEWIMAGE("previewImage"),

	FULLIMAGE("fullImage"),

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