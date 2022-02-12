package net.cubekrowd.playerlist.response;

import lombok.Getter;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ServerDTO {
	private final String name;
	private final List<PlayerDTO> players;
	public ServerDTO(ServerInfo server) {
		this.name=server.getName();
		this.players=server.getPlayers().stream().map(PlayerDTO::new).collect(Collectors.toList());
	}
}
