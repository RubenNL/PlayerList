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
			System.out.println(serverName);
			if(servers.get(serverName)==null) servers.put(serverName,new ServerDTO(serverName));
			servers.get(serverName).addPlayer(player);
		}
		System.out.println(servers);
		return servers.values().stream().toList();
	}
	public void initialize() {
		try {
			server = HttpServer.create(
					new InetSocketAddress("localhost",8081),
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
			List<ServerDTO> servers=getData();
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
		System.out.println("Server started on port 8081");
	}
}
