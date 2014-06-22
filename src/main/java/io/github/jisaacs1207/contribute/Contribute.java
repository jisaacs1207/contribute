
// Permissions:
// contribute.admin - admin commands

package io.github.jisaacs1207.contribute;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Contribute extends JavaPlugin{
	
	public String version = "1.0 Alpha";
	public String faction = "kingdom";
	public String prefix = "[CTB]";
	public String onLoad = " has been successfully loaded.";
	public String onUnload = " has been successfully unloaded.";
	public String syntaxError = "You are clearly confuzzled. /contribute help";
	
	
	
	@Override
	public void onEnable() {
		getLogger().info(this.onLoad);
	}
 
	@Override
	public void onDisable() {
		getLogger().info(this.onUnload);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if ((commandLabel.equalsIgnoreCase("contribute"))) { // If the player typed /basic then do the following...
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + this.prefix + ChatColor.GREEN + " Five Kingdoms Contribute " + this.version);
				sender.sendMessage(ChatColor.GOLD + this.prefix + ChatColor.GREEN + " Aid your " + this.faction + " by donating materials!");
				sender.sendMessage(ChatColor.GOLD + this.prefix + ChatColor.YELLOW + " Type '" + ChatColor.LIGHT_PURPLE + "/contribute help" + ChatColor.YELLOW + "' for command help.");
			}
			else if (args.length == 1) {
				if (!args[0].equals("help")&&(!args[0].equals("info"))) {
					sender.sendMessage(syntaxError);
				}
				else if (args[0].equals("help")) {
					sender.sendMessage("     -= Five Kingdoms Contribute Help =-");
					sender.sendMessage("");
					sender.sendMessage(" /contribute             - Version info");
					sender.sendMessage(" /contribute help        - Help for for plugin");
					sender.sendMessage(" /contribute info [item] - Contribute stats");
					sender.sendMessage(" /contribute add <item>  - Donates an item");
					if ((sender.isOp()) || (sender.hasPermission("contribute.admin"))) {
						sender.sendMessage(" /contribute force - Forces update of contribute [ADMIN]");
					}
				}
				else if (args[0].equals("info")) {
					sender.sendMessage("     -= Five Kingdoms Contribute Info =-");
					}
				}
			else if (args.length == 2) {
				if (args[0].equals("add")) {
					//add stub
				}
				else if (args[0].equals("info")) {
					//info stub
				}
			}	
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
}
