package net.cubekrowd.playerlist;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class PlayerListCommand extends Command {
	private final Playerlist plugin;
	public PlayerListCommand(Playerlist plugin) {
		super("playerlist", null, new String[0]);
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("playerlist.reload")) return;
		plugin.reloadFromConfig();
	}
}
