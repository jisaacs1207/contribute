package io.github.jisaacs1207.contribute;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Contribute extends JavaPlugin{
	
	public String prefix = "[CTB]";
	public String onLoad = " has been successfully loaded.";
	public String onUnload = " has been successfully unloaded.";
	
	
	
	@Override
	public void onEnable() {
		getLogger().info(this.onLoad);
	}
 
	@Override
	public void onDisable() {
		getLogger().info(this.onUnload);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("contribute")) { // If the player typed /basic then do the following...
			Player donatorPlayer = (Player) sender;
			sender.sendMessage(ChatColor.GOLD + this.prefix + ChatColor.GREEN + " Hotdogs!");
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
}
