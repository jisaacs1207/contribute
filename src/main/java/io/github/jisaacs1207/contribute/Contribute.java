
// Permissions:
// contribute.admin - admin commands
// contribute.1 - kingdom one
// contribute.2 - kingdom two
// contribute.3 - kingdom three
// contribute.4 - kingdom four
// contribute.5 - kingdom five
// contribute.staff - staff that can't contribute

package io.github.jisaacs1207.contribute;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public final class Contribute extends JavaPlugin{
	
	// Vault
	
	public static Permission perms = null;
	public static Economy economy = null;
	public static Chat chat = null;
	
	
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
	
	public Integer dItemId1;
	public Integer dItemId2;
	public Integer dItemId3;
	public Integer dItemId4;
	
	// Scrappy method of grabbing the meta info after the colon
	// For example <id>:<meta>
	public int dItemMeta1;
	public int dItemMeta2;
	public int dItemMeta3;
	public int dItemMeta4;
	
	// Kingdom Names
	
	public String kingdom1;
	public String kingdom2;
	public String kingdom3;
	public String kingdom4;
	public String kingdom5;
	
	public String pKingdom;
	
	
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
		dItemId1 = getConfig().getInt("FirstDonationItemId");
		dItemId2 = getConfig().getInt("SecondDonationItemId");
		dItemId3 = getConfig().getInt("ThirdDonationItemId");
		dItemId4 = getConfig().getInt("FourthDonationItemId");
		dItemMeta1 = getConfig().getInt("FirstDonationItemIdMeta");
		dItemMeta2 = getConfig().getInt("SecondDonationItemIdMeta");
		dItemMeta3 = getConfig().getInt("ThirdDonationItemIdMeta");
		dItemMeta4 = getConfig().getInt("FourthDonationItemIdMeta");
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
		Player player = (Player)sender;
		this.pKingdom = null;
		if((!player.hasPermission("contribute.1"))&&(!player.hasPermission("contribute.2"))&&(!player.hasPermission("contribute.3"))&&(!player.hasPermission("contribute.4"))&&(!player.hasPermission("contribute.5"))){
			sender.sendMessage("You have no kingdom affiliation! Contact an OP if you believe this is an error.");
			return true;
		}
		else if(player.isOp()){
			sender.sendMessage("Stop playing and go do some op work, eh?");
			return true;
		}
		else if(player.hasPermission("contribute.staff")){
			sender.sendMessage("Stop playing and go do some op work, eh?");
			return true;
		}
		else if(player.hasPermission("contribute.1")){
			this.pKingdom = kingdom1;
		}
		else if(player.hasPermission("contribute.2")){
			this.pKingdom = kingdom2;
		}
		else if(player.hasPermission("contribute.3")){
			this.pKingdom = kingdom3;
		}
		else if(player.hasPermission("contribute.4")){
			this.pKingdom = kingdom4;
		}
		else if(player.hasPermission("contribute.5")){
			this.pKingdom = kingdom5;
		}
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
				else if (args[0].equalsIgnoreCase("help")) {
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
				else if (args[0].equalsIgnoreCase("info")) {
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
					if ((!args[1].equalsIgnoreCase(dItemNm1))&&(!args[1].equalsIgnoreCase(dItemNm2))&&(!args[1].equalsIgnoreCase(dItemNm3))&&(!args[1].equalsIgnoreCase(dItemNm4))) {
						sender.sendMessage("You can only donate the following items:");
						sender.sendMessage(dItemNm1);
						sender.sendMessage(dItemNm2);
						sender.sendMessage(dItemNm3);
						sender.sendMessage(dItemNm4);
					}
					else if(args[1].equalsIgnoreCase(dItemNm1)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId1), 1, (short) dItemMeta1), 1)){
							sender.sendMessage("You don't have that much " + dItemNm1 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm1 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId1), 1, (byte) dItemMeta1));	
					}
					else if(args[1].equalsIgnoreCase(dItemNm2)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId2), 1, (short) dItemMeta2), 1)){
							sender.sendMessage("You don't have that much " + dItemNm2 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm2 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId2), 1, (byte) dItemMeta2));	
					}
					else if(args[1].equalsIgnoreCase(dItemNm3)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId3), 1, (short) dItemMeta3), 1)){
							sender.sendMessage("You don't have that much " + dItemNm3 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm3 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId3), 1, (byte) dItemMeta3));	
					}
					else if(args[1].equalsIgnoreCase(dItemNm4)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId4), 1, (short) dItemMeta4), 1)){
							sender.sendMessage("You don't have that much " + dItemNm4 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm4 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId4), 1, (byte) dItemMeta4));	
					}
					
				}
				else if (args[0].equalsIgnoreCase("info")) {
					//info stub
					
				}
			}	
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
}


