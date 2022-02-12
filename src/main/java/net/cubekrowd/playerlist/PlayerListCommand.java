package net.cubekrowd.playerlist;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;
import java.util.List;

public class PlayerListCommand extends Command {
	private final Playerlist plugin;
	public PlayerListCommand(Playerlist plugin) {
		super("playerlist", "playerlist.admin");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<String> options= Arrays.asList("reload");
		if(args.length==0 || !options.contains(args[0])) {
			sender.sendMessage(new TextComponent("No command specified. options: ["+String.join(", ",options)+"]."));
			return;
		}
		if(args[0].equals("reload")) {
			sender.sendMessage(new TextComponent("Reloading playerlist plugin..."));
			plugin.reloadFromConfig();
			sender.sendMessage(new TextComponent("Reloading done!"));
		}
	}
}
