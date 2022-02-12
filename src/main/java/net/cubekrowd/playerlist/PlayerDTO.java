package net.cubekrowd.playerlist;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

@Getter
public class PlayerDTO {
	private String name;
	private UUID uuid;
	public PlayerDTO(ProxiedPlayer player) {
		this.name=player.getDisplayName();
		this.uuid=player.getUniqueId();
	}
}
