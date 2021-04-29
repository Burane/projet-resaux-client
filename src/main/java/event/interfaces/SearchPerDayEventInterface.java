package event.interfaces;

import request.receive.SearchPerDayResponse;

public interface SearchPerDayEventInterface {
	void onSearchPerDayResponse(SearchPerDayResponse searchPerDayResponse);

}