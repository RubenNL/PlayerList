package net.cubekrowd.playerlist.response;

import lombok.Getter;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ServerDTO {
	private String name;
	private List<PlayerDTO> players = new ArrayList<>();
	public ServerDTO(String name) {
		this.name=name;
	}
	public ServerDTO(ServerInfo server) {
		this(server.getName());
		this.players=server.getPlayers().stream().map(PlayerDTO::new).collect(Collectors.toList());
	}
	public void addPlayer(ProxiedPlayer player) {
		this.players.add(new PlayerDTO(player));
	}
}
