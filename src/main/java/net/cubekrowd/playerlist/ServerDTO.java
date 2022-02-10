package net.cubekrowd.playerlist;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class ServerDTO {
	@Getter
	private String name;
	private List<ProxiedPlayer> players;
	public ServerDTO(String name) {
		this.name=name;
	}
	public void addPlayer(ProxiedPlayer player) {
		this.players.add(player);
	}
	public List<String> getPlayers() {
		return players.stream().map(ProxiedPlayer::getDisplayName).collect(Collectors.toList());
	}
}
