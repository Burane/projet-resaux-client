package request.receive;

import server.Client;

import java.time.LocalDate;

public class OneSearchDayResponse extends GenericResponse {
	private final LocalDate date;
	private final int nbRequest;

	public OneSearchDayResponse(LocalDate date, int nbRequest) {
		this.date = date;
		this.nbRequest = nbRequest;
	}

	@Override
	public void handle(Client client) {

	}

	@Override
	public String toString() {
		return "OneSearchDayResponse{" + "date=" + date + ", nbRequest=" + nbRequest + '}';
	}

	public LocalDate getDate() {
		return date;
	}

	public int getNbRequest() {
		return nbRequest;
	}
}