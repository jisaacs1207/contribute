
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
	
	public String version = "0.1 Alpha";
	public String faction = "kingdom";
	public String prefix = "[CTB]";
	public String onLoad = " has been successfully loaded.";
	public String onUnload = " has been successfully unloaded.";
	public String syntaxError = "You are clearly confuzzled. /contribute help";
	
	// Contribution Item Names
	
	public String dItemNm1;
	public String dItemNm2;
	public String dItemNm3;
	public String dItemNm4;
	
	// Contribution Item Ids
	
	public String dItemId1;
	public String dItemId2;
	public String dItemId3;
	public String dItemId4;
	
	// Kingdom Names
	
	public String kingdom1;
	public String kingdom2;
	public String kingdom3;
	public String kingdom4;
	public String kingdom5;
	
	
	
	@Override
	public void onEnable() {
		getLogger().info(this.onLoad);
		
		// Set the default config file if it isn't there.
		saveDefaultConfig();
		
		// Pull goodies from the config file
		dItemNm1 = getConfig().getString("FirstDonationItemName");
		dItemNm2 = getConfig().getString("SecondDonationItemName");
		dItemNm3 = getConfig().getString("ThirdDonationItemName");
		dItemNm4 = getConfig().getString("FourthDonationItemName");
		dItemId1 = getConfig().getString("FirstDonationItemId");
		dItemId2 = getConfig().getString("SecondDonationItemId");
		dItemId3 = getConfig().getString("ThirdDonationItemId");
		dItemId4 = getConfig().getString("FourthDonationItemId");
		kingdom1 = getConfig().getString("FirstKingdomName");
		kingdom2 = getConfig().getString("SecondKingdomName");
		kingdom3 = getConfig().getString("ThirdKingdomName");
		kingdom4 = getConfig().getString("FourthKingdomName");
		kingdom5 = getConfig().getString("FifthKingdomName");
	}
 
	@Override
	public void onDisable() {
		getLogger().info(this.onUnload);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if ((commandLabel.equalsIgnoreCase("contribute"))||(commandLabel.equalsIgnoreCase("ctb"))) { 
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
					sender.sendMessage(" /contribute - Version info");
					sender.sendMessage(" /contribute help - Help for for plugin");
					sender.sendMessage(" /contribute info [item] - Contribute stats");
					sender.sendMessage(" /contribute add <item> - Donates an item");
					if ((sender.isOp()) || (sender.hasPermission("contribute.admin"))) {
						sender.sendMessage(" /contribute force - Forces update of contribute [ADMIN]");
					}
				}
				else if (args[0].equals("info")) {
					sender.sendMessage("     -= Five Kingdoms Contribute Winners =-");
					sender.sendMessage("");
					// kingdom here is a temp stub!! This needs to be changed to decide the winning kingdom later
					sender.sendMessage(dItemNm1 + " - " + kingdom1);
					sender.sendMessage(dItemNm2 + " - " + kingdom2);
					sender.sendMessage(dItemNm3 + " - " + kingdom3);
					sender.sendMessage(dItemNm4 + " - " + kingdom4);
					}
				}
			else if (args.length == 2) {
				if (args[0].equals("add")) {
					if ((!args[1].equals(dItemNm1))&&(!args[1].equals(dItemNm2))&&(!args[1].equals(dItemNm3))&&(!args[1].equals(dItemNm4))) {
						sender.sendMessage("You can only donate the following items:");
						sender.sendMessage(dItemNm1);
						sender.sendMessage(dItemNm2);
						sender.sendMessage(dItemNm3);
						sender.sendMessage(dItemNm4);
					}
					
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
