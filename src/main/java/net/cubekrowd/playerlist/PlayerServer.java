package net.cubekrowd.playerlist;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import net.cubekrowd.playerlist.response.ServerDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

import static net.cubekrowd.playerlist.response.GetData.getData;

public class PlayerServer {
	private HttpServer server;
	private Playerlist plugin;
	public PlayerServer(Playerlist plugin, int port) {
		this.plugin=plugin;
		initialize(port);
	}
	private void initialize(int port) {
		try {
			server = HttpServer.create(
					new InetSocketAddress(port),
					0
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.createContext("/data.json", exchange -> {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

			if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
				exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
				exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
				exchange.sendResponseHeaders(204, -1);
				return;
			}

			OutputStream outputStream = exchange.getResponseBody();
			Gson gson = new Gson();
			List<ServerDTO> servers=getData(plugin.getProxy());
			String res = "";
			try {
				res = gson.toJson(servers);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
			byte[] bytes = res.getBytes();
			exchange.sendResponseHeaders(200, bytes.length);

			try {
				outputStream.write(bytes);
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("failed to write");
				e.printStackTrace(System.out);
			}
		});
		server.start();
		System.out.println("Server started on port "+port);
	}
	public void stop() {
		server.stop(0);
	}
}
