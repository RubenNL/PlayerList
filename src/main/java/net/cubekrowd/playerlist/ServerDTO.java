package net.cubekrowd.playerlist;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class ServerDTO {
	public String name;
	//private List<ProxiedPlayer> players = new ArrayList<>();
	public List<String> playerNames = new ArrayList<>();
	public ServerDTO(String name) {
		this.name=name;
	}
	public void addPlayer(ProxiedPlayer player) {
//		this.players.add(player);
		this.playerNames.add(player.getDisplayName());
	}
}
