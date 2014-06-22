package io.github.jisaacs1207.contribute;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public final class Contribute extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info("Contribute has been successfully loaded");
	}
 
	@Override
	public void onDisable() {
		getLogger().info("Contribute has been successfully unloaded");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("contribute")) {
			// doSomething
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
}
