package event;

import event.interfaces.AuthentificationEventInterface;
import request.receive.AuthentificationResponse;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
	private static volatile EventBus instance;
	private CopyOnWriteArrayList<AuthentificationEventInterface> authentificationListeners = new CopyOnWriteArrayList<>();

	private EventBus() {
	}

	public static EventBus getInstance() {
		EventBus client = instance;

		if (client != null)
			return client;

		synchronized (EventBus.class) {
			if (instance == null)
				instance = new EventBus();
			return instance;
		}

	}

	public void subscribeToAuthentificationEvent(AuthentificationEventInterface listener) {
		authentificationListeners.add(listener);
	}

	public void unSubscribeToAuthentificationEvent(AuthentificationEventInterface listener) {
		authentificationListeners.remove(listener);
	}

	public void notifyAuthentificationListeners(AuthentificationResponse authentificationResponse) {
		authentificationListeners.forEach(obj -> obj.onAuthentificationResponse(authentificationResponse));
	}
}
