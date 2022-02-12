package net.cubekrowd.playerlist.response;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class GetData {
	public static List<ServerDTO> getData(ProxyServer proxy) {
		List<ServerDTO> servers = new ArrayList<>();
		for(ServerInfo serverInfo: proxy.getServersCopy().values()) {
			ServerDTO serverDTO=new ServerDTO(serverInfo.getName());
			serverInfo.getPlayers().forEach(serverDTO::addPlayer);
			servers.add(serverDTO);
		}
		return servers;
	}
}
