package request.receive;

public enum ResponseType {
	AUTHENTIFICATION("authentification"),

	ERROR("error"),

	SUCCESS("success"),

	PREVIEWIMAGE("previewImage"),

	FULLIMAGE("fullImage"),

	LIKE("like"),

	SEARCH("search"),

	SEARCHPERDAY("searchPerDay");


	private final String field;

	@Override
	public String toString() {
		return field;
	}

	ResponseType(String field) {
		this.field = field;
	}
}