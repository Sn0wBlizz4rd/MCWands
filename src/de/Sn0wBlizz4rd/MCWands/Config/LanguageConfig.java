package de.Sn0wBlizz4rd.MCWands.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageConfig {

	public static String PREFIX;
	public static String WAND_NOT_FOUND;
	public static String WAND_GIVEN;
	public static String COMMAND_GIVE_EXPLAIN;
	public static String COMMAND_RELOAD_EXPLAIN;
	public static String NO_PERMISSION;
	public static String GIVEN_SUCCESS;
	public static String VALID_PLAYER;
	public static String COOLDOWN_DENY;
	public static String WAND_PERMISSION;
	public static String RELOADED;
	
	public static void setDefaults() {
		File f = new File("plugins/MCWands/language.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);

		y.set("PREFIX", "&6[MCWands] &7");
		y.set("WAND_NOT_FOUND", "Wand not found.");
		y.set("WAND_GIVEN", "You were given a wand.");
		y.set("COMMAND_GIVE_EXPLAIN", "Gives a player a wand.");
		y.set("COMMAND_RELOAD_EXPLAIN", "Reloads the configs from disk.");
		y.set("NO_PERMISSION", "You do not have permission to perform that command.");
		y.set("GIVEN_SUCCESS", "Given '[wand]' to [player] successfully.");
		y.set("VALID_PLAYER", "An error occurred, is the player valid?");
		y.set("COOLDOWN_DENY", "You have to wait, until your countdown is timed out.");
		y.set("WAND_PERMISSION", "You do not have permissions to use this wand.");
		y.set("RELOADED", "Successfully reloaded configurations.");
		
		try {
			y.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reload() {
		File f = new File("plugins/MCWands/language.yml");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);

		PREFIX = ChatColor.translateAlternateColorCodes('&', y.getString("PREFIX"));
		WAND_NOT_FOUND = ChatColor.translateAlternateColorCodes('&', y.getString("WAND_NOT_FOUND"));
		WAND_GIVEN = ChatColor.translateAlternateColorCodes('&', y.getString("WAND_GIVEN"));
		COMMAND_GIVE_EXPLAIN = ChatColor.translateAlternateColorCodes('&', y.getString("COMMAND_GIVE_EXPLAIN"));
		COMMAND_RELOAD_EXPLAIN = ChatColor.translateAlternateColorCodes('&', y.getString("COMMAND_RELOAD_EXPLAIN"));
		NO_PERMISSION = ChatColor.translateAlternateColorCodes('&', y.getString("NO_PERMISSION"));
		GIVEN_SUCCESS = ChatColor.translateAlternateColorCodes('&', y.getString("GIVEN_SUCCESS"));
		VALID_PLAYER = ChatColor.translateAlternateColorCodes('&', y.getString("VALID_PLAYER"));
		COOLDOWN_DENY = ChatColor.translateAlternateColorCodes('&', y.getString("COOLDOWN_DENY"));
		WAND_PERMISSION = ChatColor.translateAlternateColorCodes('&', y.getString("WAND_PERMISSION"));
		RELOADED = ChatColor.translateAlternateColorCodes('&', y.getString("RELOADED"));
		
	}
	
}
