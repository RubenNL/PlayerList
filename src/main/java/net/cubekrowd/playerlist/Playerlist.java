package net.cubekrowd.playerlist;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.logging.Level;

public final class Playerlist extends Plugin {
	private PlayerServer playerServer;
	@Override
	public void onEnable() {
		saveDefaultConfig("config.yml");
		reloadFromConfig();
		getProxy().getPluginManager().registerCommand(this, new PlayerListCommand(this));
	}

	@Override
	public void onDisable() {
		this.playerServer.stop();
	}

	public void saveDefaultConfig(String name) {
		getDataFolder().mkdirs();

		var configFile = new File(getDataFolder(), name);
		try {
			Files.copy(getResourceAsStream(name), configFile.toPath());
		} catch (FileAlreadyExistsException e) {
			// ignore
		} catch (IOException e) {
			getLogger().log(Level.WARNING, "Can't save default config " + name, e);
		}
	}

	public Configuration loadConfig(String name) {
		var configProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
		try {
			return configProvider.load(new File(getDataFolder(), name));
		} catch (IOException e) {
			getLogger().log(Level.WARNING, "Can't load config " + name, e);
			return new Configuration();
		}
	}
	public void reloadFromConfig() {
		getLogger().info("Reloading configuration...");
		var configConfig = loadConfig("config.yml");
		if(this.playerServer != null) this.playerServer.stop();
		int port=configConfig.getInt("port");
		getLogger().info("Starting server on port "+port);
		this.playerServer=new PlayerServer(this, port);
	}


}
