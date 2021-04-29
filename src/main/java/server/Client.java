package server;

import request.RequestHandler;
import request.send.DisconnectRequest;
import request.send.GenericRequest;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class Client implements Runnable {

	private Socket client;
	private PrintStream writer;
	private BufferedReader reader;
	private RequestHandler requestHandler;
	private final LinkedList<String> queue = new LinkedList<>();
	private boolean isAuthentified = false;
	private int userId = -1;

	private static volatile Client instance;
	private boolean isRunning = true;

	private Client() {
		try {
			client = new Socket(System.getProperty("server.url"), Integer.parseInt(System.getProperty("server.port")));
			client.setKeepAlive(true);
			writer = new PrintStream(client.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(client.getLocalAddress() + " " + client.getLocalPort());
	}

	public static Client getInstance() {
		Client client = instance;

		if (client != null)
			return client;

		synchronized (Client.class) {
			if (instance == null)
				instance = new Client();
			return instance;
		}

	}

	public void run() {
		try {
			requestHandler = new RequestHandler(this);
			while (isRunning) {
				String request = receiveContent();
				if (!request.isEmpty()) {
					queue.addFirst(request);
//					System.out.println(request);
					String currentRequest = queue.pollLast();
					if (currentRequest != null)
						requestHandler.handle(currentRequest);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			close();
		}
	}

	public void send(byte[] content) {
		try {
			writer.write(content);
			writeEndLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.flush();
	}

	public void send(String content) {
		try {
			writer.write(content.getBytes(StandardCharsets.UTF_8));
			writeEndLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.flush();
	}

	public void send(GenericRequest request) {
		try {
//			System.out.println(request.toJson());
			writer.write(request.toJson().getBytes(StandardCharsets.UTF_8));
			writeEndLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.flush();
	}

	private void writeEndLine() {
		try {
			writer.write("\n\r".getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String receiveContent() throws Exception {
		return readBuffer();
	}

	private String readBuffer() throws Exception {
		StringBuilder str = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			if (line.isEmpty())
				break;
			str.append(line);
		}
		if (line == null)

			while (reader.ready()) {
				int ch = reader.read();
				if (ch == -1)
					str.append((char) ch);
			}
		return str.toString();
	}

	public boolean isAuthentified() {
		return isAuthentified;
	}

	public void setAuthentified(boolean authentified) {
		isAuthentified = authentified;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void close() {
		System.out.println("Closing connection with :" + client.getInetAddress() + ":" + client.getLocalPort());
		send(new DisconnectRequest());
		isRunning = false;
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}