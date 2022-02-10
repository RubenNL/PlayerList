package net.cubekrowd.playerlist;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public final class Playerlist extends Plugin {
	private HttpServer server;
	@Override
	public void onEnable() {
		initialize();
	}

	@Override
	public void onDisable() {}
	public List<ServerDTO> getData() {
		Map<String,ServerDTO> servers=new HashMap<>();
		for(ProxiedPlayer player: this.getProxy().getPlayers()) {
			String serverName=player.getServer().getInfo().getName();
			if(!servers.containsKey(serverName)) servers.put(serverName,new ServerDTO(serverName));
			servers.get(serverName).addPlayer(player);
		}
		return servers.values().stream().toList();
	}
	public void initialize() {
		try {
			server = HttpServer.create(
					new InetSocketAddress("0.0.0.0", 8081),
					0
			);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

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
			String res = gson.toJson(getData());

			byte[] bytes = res.getBytes();

			exchange.sendResponseHeaders(200, bytes.length);

			try {
				outputStream.write(bytes);
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		server.setExecutor(threadPoolExecutor);
		server.start();
		System.out.println("Server started on port 8081");
	}
}
