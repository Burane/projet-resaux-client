package gson;

import com.google.gson.*;
import request.receive.*;

import java.time.LocalDate;

public abstract class RequestDeserializer {

	public static Gson getDeserializer() {
		RuntimeTypeAdapterFactory<GenericResponse> typeAdapterFactory = RuntimeTypeAdapterFactory
				.of(GenericResponse.class, "type")
				.registerSubtype(AuthentificationResponse.class, ResponseType.AUTHENTIFICATION.toString())
				.registerSubtype(ErrorResponse.class, ResponseType.ERROR.toString())
				.registerSubtype(SuccessResponse.class, ResponseType.SUCCESS.toString())
				.registerSubtype(SearchResponse.class, ResponseType.SEARCH.toString())
				.registerSubtype(PreviewImageResponse.class, ResponseType.PREVIEWIMAGE.toString())
				.registerSubtype(LikeResponse.class, ResponseType.LIKE.toString())
				.registerSubtype(SearchPerDayResponse.class, ResponseType.SEARCHPERDAY.toString())
				.registerSubtype(FullImageResponse.class, ResponseType.FULLIMAGE.toString());

		return new GsonBuilder().registerTypeAdapterFactory(typeAdapterFactory)
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
	}
}