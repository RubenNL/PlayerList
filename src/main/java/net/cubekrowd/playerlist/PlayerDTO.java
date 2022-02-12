package net.cubekrowd.playerlist;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@Getter
public class PlayerDTO {
	public String name;
	public PlayerDTO(ProxiedPlayer player) {
		this.name=player.getDisplayName();
		this.name=player.getName();
	}
}
