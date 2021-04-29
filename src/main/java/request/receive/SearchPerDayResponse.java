package request.receive;

import event.EventBus;
import server.Client;

import java.util.ArrayList;

public class SearchPerDayResponse extends GenericResponse {
	final ArrayList<OneSearchDayResponse> searchDayResponses;

	public SearchPerDayResponse(ArrayList<OneSearchDayResponse> searchDayResponses) {
		this.searchDayResponses = searchDayResponses;
	}

	@Override
	public void handle(Client client) {
		EventBus.getInstance().notifySearchPerDayListeners(this);
	}

	public ArrayList<OneSearchDayResponse> getSearchDayResponses() {
		return searchDayResponses;
	}

	@Override
	public String toString() {
		return "SearchPerDayResponse{" + "searchDayResponses=" + searchDayResponses + '}';
	}
}