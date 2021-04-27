package event.interfaces;

import request.receive.SearchResponse;

public interface SearchEventInterface {
	void onSearchResponse(SearchResponse searchResponse);

}
