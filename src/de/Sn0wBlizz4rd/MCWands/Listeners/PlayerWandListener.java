package de.Sn0wBlizz4rd.MCWands.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.Sn0wBlizz4rd.MCWands.Main;
import de.Sn0wBlizz4rd.MCWands.Config.LanguageConfig;
import de.Sn0wBlizz4rd.MCWands.Effect.FireworkEffectPlayer;

public class PlayerWandListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().getItemInHand().getType() == Material.STICK) {
			if(Main.name.containsKey(e.getPlayer().getItemInHand().getItemMeta().getDisplayName())) {
				
				final String nn = Main.name.get(e.getPlayer().getItemInHand().getItemMeta().getDisplayName());
				
				if((Main.useperms && e.getPlayer().hasPermission("MCWands.usewand." + nn)) || (!Main.useperms)) {
					if(!Main.plc.containsKey(e.getPlayer().getName())) {
						Main.plc.put(e.getPlayer().getName(), new ArrayList<String>());
					}
					
					if(!Main.plc.get(e.getPlayer().getName()).contains(nn)) {
						List<String>list = Main.plc.get(e.getPlayer().getName());
						list.add(nn);
						Main.plc.put(e.getPlayer().getName(), list);
						final String newname = e.getPlayer().getName();
						
						Main.plugin.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable() {
							public void run() {
								List<String>list2 = Main.plc.get(newname);
								list2.remove(nn);
								Main.plc.put(newname, list2);
							}
						}, 20L * Main.cooldown.get(nn));
						
						@SuppressWarnings("deprecation")
						final Snowball s = e.getPlayer().throwSnowball();
						
						final Color color = FireworkEffectPlayer.getColor(Main.color.get(Main.name.get(e.getPlayer().getItemInHand().getItemMeta().getDisplayName())).toUpperCase());
						
						final int id = s.getEntityId();
						
						int i = Main.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
							public void run() {
								if(!s.isDead()) {
									FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(color).with(Type.BALL).trail(false).build();
									FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
									try {
										fplayer.playFirework(s.getWorld(), s.getLocation(), effect);
									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									Main.plugin.getServer().getScheduler().cancelTask(Main.sch.get(id));
								}
							}
						}, 0L, 2L);
						
						Main.sch.put(id, i);
						Main.sch_name.put(id, nn);
					} else {
						e.getPlayer().sendMessage(LanguageConfig.PREFIX + LanguageConfig.COOLDOWN_DENY);
					}
				} else {
					e.getPlayer().sendMessage(LanguageConfig.PREFIX + LanguageConfig.WAND_PERMISSION);
				}
			}
		}
	}
	
}
