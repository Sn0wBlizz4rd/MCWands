package de.Sn0wBlizz4rd.MCWands.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;

import de.Sn0wBlizz4rd.MCWands.Main;
import de.Sn0wBlizz4rd.MCWands.Config.LanguageConfig;

public class CommandWand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		
		if(args.length==0) {
			if(sender.hasPermission("MCWands.showhelp")) {
				sender.sendMessage("§6[]=====- MCWands help -=====[]");
				sender.sendMessage("§6/wand give <Wandname> [Player] §7" + LanguageConfig.COMMAND_GIVE_EXPLAIN);
				sender.sendMessage("§6/wand reload §7" + LanguageConfig.COMMAND_RELOAD_EXPLAIN);
				sender.sendMessage("§6[]=====- MCWands help -=====[]");
			} else {
				PluginDescriptionFile pdf = Main.plugin.getDescription();
				sender.sendMessage(LanguageConfig.PREFIX + "MCWands version " + pdf.getVersion() + " by Sn0wBlizz4rd.");
			}
		}
		
		if(args.length==2 || args.length==3) {
			if(args[0].equalsIgnoreCase("give")) {
				if(sender.hasPermission("MCWands.give")) {
					String wand = args[1];
					
					Player p = null;
					
					if(sender instanceof Player) {
						p = (Player) sender;
					}
					
					if(args.length==3) {
						String tt = args[2];
						if(Bukkit.getPlayer(tt)!=null) {
							p = Bukkit.getPlayer(tt);
						}
					}
					
					if(p != null) {
						if(Main.wands.contains(wand)) {
							ItemStack is = new ItemStack(Material.STICK, 1);
							ItemMeta im = is.getItemMeta();
							im.setDisplayName(Main.nameother.get(wand));
							is.setItemMeta(im);
								
							p.getInventory().addItem(is);
								
							p.sendMessage(LanguageConfig.PREFIX + LanguageConfig.WAND_GIVEN);
							
							sender.sendMessage(LanguageConfig.PREFIX + LanguageConfig.GIVEN_SUCCESS.replace("[player]", p.getName()).replace("[wand]", wand));
						} else {
							p.sendMessage(LanguageConfig.PREFIX + LanguageConfig.WAND_NOT_FOUND);
						}
					} else {
						sender.sendMessage(LanguageConfig.PREFIX + LanguageConfig.VALID_PLAYER);
					}
				} else {
					sender.sendMessage(LanguageConfig.PREFIX + LanguageConfig.NO_PERMISSION);
				}
			}
		}
		
		if(args.length==1) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender.hasPermission("MCWands.reload")) {
					Main.useperms = Main.plugin.getConfig().getBoolean("Enable-permissions");
					Main.setupLanguage();
					Main.setupWands();
					sender.sendMessage(LanguageConfig.PREFIX + LanguageConfig.RELOADED);
				} else {
					sender.sendMessage(LanguageConfig.PREFIX + LanguageConfig.NO_PERMISSION);
				}
			}
		}
		
		return true;
	}

	
	
}
