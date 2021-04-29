package Controller;

import event.EventBus;
import event.interfaces.SearchPerDayEventInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import request.receive.OneSearchDayResponse;
import request.receive.SearchPerDayResponse;
import request.send.SearchPerDayRequest;
import server.Client;

import java.time.LocalDate;
import java.util.ArrayList;

public class RechercheParJoursController implements SearchPerDayEventInterface {

	@FXML private DatePicker datePickerFrom;

	@FXML private DatePicker datePickerTo;

	@FXML private AreaChart<String, Integer> areaChart;

	@FXML
	void OnGo(ActionEvent event) {
		LocalDate dateFrom = datePickerFrom.getValue();
		LocalDate dateTo = datePickerTo.getValue();

		if (dateFrom.isAfter(dateTo))
			return;

		Client.getInstance().send(new SearchPerDayRequest(dateFrom, dateTo));
		EventBus.getInstance().subscribeToSearchPerDayEvent(this);

	}

	@Override
	public void onSearchPerDayResponse(SearchPerDayResponse searchPerDayResponse) {
		EventBus.getInstance().unSubscribeToSearchPerDayEvent(this);
		updateChart(searchPerDayResponse.getSearchDayResponses());
	}

	public void updateChart(ArrayList<OneSearchDayResponse> responses) {
		Platform.runLater(() -> {
			areaChart.getData().clear();
			XYChart.Series<String, Integer> series = new XYChart.Series<>();
			areaChart.setTitle("Du " + datePickerFrom.getValue() + " jusqu'au " + datePickerTo.getValue());
			series.setName("Nombre de recherches par jour");
			for (OneSearchDayResponse res : responses) {
				series.getData().add(new XYChart.Data<>(res.getDate().toString(), res.getNbRequest()));
			}
			areaChart.getData().add(0, series);
		});
	}
}