package de.Sn0wBlizz4rd.MCWands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import de.Sn0wBlizz4rd.MCWands.Commands.CommandWand;
import de.Sn0wBlizz4rd.MCWands.Config.LanguageConfig;
import de.Sn0wBlizz4rd.MCWands.Config.WandConfig;
import de.Sn0wBlizz4rd.MCWands.Listeners.EntityHitListener;
import de.Sn0wBlizz4rd.MCWands.Listeners.PlayerWandListener;
import de.Sn0wBlizz4rd.MCWands.Metrics.Metrics;

public class Main extends JavaPlugin {

	/**Plugin written by Sn0wBlizz4rd. All Rights reserved (c) 2013 **/
	
	public static Main plugin;
	
	public static List<String>wands = new ArrayList<String>();
	
	public static Map<String, String>color = new HashMap<String, String>();
	public static Map<String, Integer>damage = new HashMap<String, Integer>();
	public static Map<String, Integer>cooldown = new HashMap<String, Integer>();
	public static Map<String, String>name = new HashMap<String, String>();
	public static Map<String, String>nameother = new HashMap<String, String>();
	
	public static Map<String, List<String>>plc = new HashMap<String, List<String>>();
	
	public static Map<Integer, Integer>sch = new HashMap<Integer, Integer>();
	
	public static Map<Integer, String>sch_name = new HashMap<Integer, String>();
	
	public static boolean useperms;
	
	private void setupListeners() {
		getServer().getPluginManager().registerEvents(new PlayerWandListener(), plugin);
		getServer().getPluginManager().registerEvents(new EntityHitListener(), plugin);
	}
	
	private void setupMetrics() {
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch(Exception e) {
			getLogger().severe("Metrics for MCWands could not be loaded (" + e.getMessage() + ")!");
		}
	}
	
	public static void setupLanguage() {
		
		File f = new File("plugins/MCWands/language.yml");
		
		if(!f.exists()) {
			plugin.getLogger().warning("No language file found, creating one for you.");
			LanguageConfig.setDefaults();
			plugin.getLogger().info("Language file created successfully.");
		}
		
		LanguageConfig.reload();
	}
	
	public static void setupWands() {
		File f = new File("plugins/MCWands/wands/wands.cfg");
		
		if(!f.exists()) {
			plugin.getLogger().warning("No wand file found, creating one for you.");
			WandConfig.setDefaults();
			plugin.getLogger().info("Wand file created successfully.");
		}
		
		WandConfig.reload();
	}
	
	private void setupCommands() {
		getCommand("wand").setExecutor(new CommandWand());
	}
	
	
	
	public void onEnable() {

		plugin = this;
		
		saveDefaultConfig();
		
		useperms = getConfig().getBoolean("Enable-permissions");
		
		setupListeners();
		setupMetrics();
		setupLanguage();
		setupWands();
		setupCommands();
		
	}
	
	public void onDisable() {}
	
}
