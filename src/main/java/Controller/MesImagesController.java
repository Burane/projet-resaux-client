package Controller;

import event.EventBus;
import event.interfaces.SearchEventInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import request.receive.SearchResponse;
import request.send.SearchRequest;
import server.Client;

public class MesImagesController extends ImagePreviewController implements SearchEventInterface {

	@FXML private TextField rechercheField;

	@FXML @Override
	public void initialize() {
		EventBus.getInstance().subscribeToSearchEvent(this);
		imagePerPage = gridPane.getRowCount() * gridPane.getColumnCount();
	}

	@Override
	public void onRecherche(ActionEvent actionEvent) {
		page.setText("Page : " + (pageNumber + 1));
		String query = rechercheField.getText();
		int limitFrom = pageNumber * imagePerPage;
		int limitTo = (pageNumber + 1) * imagePerPage;
		System.out.println("limitFrom " + limitFrom + " limitTo " + limitTo);
		Client.getInstance().send(new SearchRequest(query, limitFrom, limitTo));
	}

	public void onSearchResponse(SearchResponse searchResponse) {
		loadImages(searchResponse.getImages());
	}


}