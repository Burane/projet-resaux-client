package request.send;

import java.time.LocalDate;

public class SearchPerDayRequest extends GenericRequest {
	private final LocalDate dateFrom;
	private final LocalDate dateTo;

	public SearchPerDayRequest(LocalDate dateFrom, LocalDate dateTo) {
		super(RequestType.SEARCHPERDAY);
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}
}