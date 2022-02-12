package net.cubekrowd.playerlist;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public final class Playerlist extends Plugin {
	private HttpServer server;
	@Override
	public void onEnable() {
		initialize();
	}

	@Override
	public void onDisable() {}
	public List<ServerDTO> getData() {
		List<ServerDTO> servers = new ArrayList<>();
		for(ServerInfo serverInfo: this.getProxy().getServersCopy().values()) {
			ServerDTO serverDTO=new ServerDTO(serverInfo.getName());
			serverInfo.getPlayers().forEach(serverDTO::addPlayer);
			servers.add(serverDTO);
		}
		return servers;
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
