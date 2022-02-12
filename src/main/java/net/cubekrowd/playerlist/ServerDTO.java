package net.cubekrowd.playerlist;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ServerDTO {
	private String name;
	private List<PlayerDTO> players = new ArrayList<>();
	public ServerDTO(String name) {
		this.name=name;
	}
	public void addPlayer(ProxiedPlayer player) {
		this.players.add(new PlayerDTO(player));
	}
}
