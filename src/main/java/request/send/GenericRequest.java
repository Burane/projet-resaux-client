package request.send;

import com.google.gson.Gson;

public abstract class GenericRequest {
	private String type;

	public GenericRequest(RequestType type) {
		this.type = type.toString();
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return toJson();
	}

}
