package de.Sn0wBlizz4rd.MCWands.Listeners;

import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.Sn0wBlizz4rd.MCWands.Main;

public class EntityHitListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Snowball) {
			Snowball s = (Snowball)e.getDamager();
			
			if(Main.sch_name.containsKey(s.getEntityId())) {
				String name = Main.sch_name.get(s.getEntityId());
				
				e.setDamage(Main.damage.get(name));
			}
		}
	}
	
}
