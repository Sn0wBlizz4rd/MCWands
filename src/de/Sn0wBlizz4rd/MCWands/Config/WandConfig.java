package de.Sn0wBlizz4rd.MCWands.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Sn0wBlizz4rd.MCWands.Main;

public class WandConfig {

	public static void setDefaults() {
		File f = new File("plugins/MCWands/wands/wands.cfg");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);
		
		y.set("wands.goodwand.color", "WHITE");
		y.set("wands.goodwand.damage", 6);
		y.set("wands.goodwand.name", "&fGood &7Wand");
		y.set("wands.goodwand.cooldown", 2);
		
		y.set("wands.evilwand.color", "BLACK");
		y.set("wands.evilwand.damage", 8);
		y.set("wands.evilwand.name", "&4Evil &7Wand");
		y.set("wands.evilwand.cooldown", 3);
		
		y.set("wands.naturewand.color", "GREEN");
		y.set("wands.naturewand.damage", 2);
		y.set("wands.naturewand.name", "&2Nature &7Wand");
		y.set("wands.naturewand.cooldown", 4);
		
		y.set("wands.aquawand.color", "AQUA");
		y.set("wands.aquawand.damage", 12);
		y.set("wands.aquawand.name", "&bAqua &7Wand");
		y.set("wands.aquawand.cooldown", 5);
		
		try {
			y.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reload() {
		File f = new File("plugins/MCWands/wands/wands.cfg");
		YamlConfiguration y = YamlConfiguration.loadConfiguration(f);
		
		Main.color.clear();
		Main.damage.clear();
		Main.name.clear();
		Main.nameother.clear();
		Main.wands.clear();
		
		for(String s : y.getConfigurationSection("wands").getKeys(false)) {

			Main.color.put(s, y.getString("wands." + s + ".color"));
			Main.damage.put(s, y.getInt("wands." + s + ".damage"));
			Main.cooldown.put(s, y.getInt("wands." + s + ".cooldown"));
			Main.name.put(ChatColor.translateAlternateColorCodes('&', y.getString("wands." + s + ".name")), s);
			Main.nameother.put(s, ChatColor.translateAlternateColorCodes('&', y.getString("wands." + s + ".name")));
			
			Main.wands.add(s);
		}
	}
	
}
