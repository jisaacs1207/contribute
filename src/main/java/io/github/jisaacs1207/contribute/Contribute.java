// TO DO
// 1) FILL IN STUBS
// 2) ...
// 3) PROFIT

// Permissions:
// contribute.admin - admin commands
// contribute.1 - kingdom one
// contribute.2 - kingdom two
// contribute.3 - kingdom three
// contribute.4 - kingdom four
// contribute.5 - kingdom five
// contribute.staff - staff that can't contribute

package io.github.jisaacs1207.contribute;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.HashMap;
import java.util.Map;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;




public final class Contribute extends JavaPlugin{
	
	// Vault
	

	public static Economy econ = null;

	
	
	public String version = "0.1 Alpha";
	public String faction = "kingdom";
	public String prefix = "[CTB]";
	public String onLoad = " has been successfully loaded.";
	public String onUnload = " has been successfully unloaded.";
	public String syntaxError = "You are clearly confuzzled. /contribute help";
	
	// Contribution Winners
	
	public static String i1Winner;
	public String i2Winner;
	public String i3Winner;
	public String i4Winner;
	
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
	
	// Item Amount
	
	public int k1i1;
	public int k1i2;
	public int k1i3;
	public int k1i4;
	
	public int k2i1;
	public int k2i2;
	public int k2i3;
	public int k2i4;
	
	public int k3i1;
	public int k3i2;
	public int k3i3;
	public int k3i4;
	
	public int k4i1;
	public int k4i2;
	public int k4i3;
	public int k4i4;
	
	public int k5i1;
	public int k5i2;
	public int k5i3;
	public int k5i4;
	
	// Kingdom Names
	
	public String kingdom1;
	public String kingdom2;
	public String kingdom3;
	public String kingdom4;
	public String kingdom5;
	
	public String pKingdom;
	
	public Map<String, Integer> i1cont = new HashMap<>();
	public Map<String, Integer> i2cont = new HashMap<>();
	public Map<String, Integer> i3cont = new HashMap<>();
	public Map<String, Integer> i4cont = new HashMap<>();
	
	@Override
	public void onEnable() {
		getLogger().info(this.onLoad);
		saveDefaultConfig();
		fillVariables();
		findWinner();
		setupEconomy();
		BukkitScheduler feedAuraScheduler = Bukkit.getServer().getScheduler();
		feedAuraScheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                feedAura();
            }
        }, 0L, 200L);
		BukkitScheduler interestEconScheduler = Bukkit.getServer().getScheduler();
		interestEconScheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                interestEcon();
            }
        }, 0L, 200L);
	}
 
	@Override
	public void onDisable() {
		getLogger().info(this.onUnload);
	}

	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player)sender;
		if ((commandLabel.equalsIgnoreCase("contribute"))||(commandLabel.equalsIgnoreCase("ctb"))) {
			fillVariables();
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
					findWinner();
					sender.sendMessage(dItemNm1 + " - " + i1Winner);
					sender.sendMessage(dItemNm2 + " - " + i2Winner);
					sender.sendMessage(dItemNm3 + " - " + i3Winner);
					sender.sendMessage(dItemNm4 + " - " + i4Winner);
					return true;
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
						return true;
					}
					else if(args[1].equalsIgnoreCase(dItemNm1)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId1), 1, (short) dItemMeta1), 1)){
							sender.sendMessage("You don't have that much " + dItemNm1 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm1 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId1), 1, (byte) dItemMeta1));
						if (this.pKingdom.equals(kingdom1)){
							k1i1 += 1;
							this.getConfig().set("kingdom1.item1", k1i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i1 += 1;
							this.getConfig().set("kingdom2.item1", k2i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i1 += 1;
							this.getConfig().set("kingdom3.item1", k3i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i1 += 1;
							this.getConfig().set("kingdom4.item1", k4i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i1 += 1;
							this.getConfig().set("kingdom5.item1", k5i1);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm2)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId2), 1, (short) dItemMeta2), 1)){
							sender.sendMessage("You don't have that much " + dItemNm2 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm2 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId2), 1, (byte) dItemMeta2));
						if (this.pKingdom.equals(kingdom1)){
							k1i2 += 1;
							this.getConfig().set("kingdom1.item2", k1i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i2 += 1;
							this.getConfig().set("kingdom2.item2", k2i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i2 += 1;
							this.getConfig().set("kingdom3.item2", k3i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i2 += 1;
							this.getConfig().set("kingdom4.item2", k4i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i2 += 1;
							this.getConfig().set("kingdom5.item2", k5i2);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm3)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId3), 1, (short) dItemMeta3), 1)){
							sender.sendMessage("You don't have that much " + dItemNm3 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm3 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId3), 1, (byte) dItemMeta3));
						if (this.pKingdom.equals(kingdom1)){
							k1i3 += 1;
							this.getConfig().set("kingdom1.item3", k1i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i3 += 1;
							this.getConfig().set("kingdom2.item3", k2i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i3 += 1;
							this.getConfig().set("kingdom3.item3", k3i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i3 += 1;
							this.getConfig().set("kingdom4.item3", k4i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i3 += 1;
							this.getConfig().set("kingdom5.item3", k5i3);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm4)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId4), 1, (short) dItemMeta4), 1)){
							sender.sendMessage("You don't have that much " + dItemNm4 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm4 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId4), 1, (byte) dItemMeta4));
						if (this.pKingdom.equals(kingdom1)){
							k1i4 += 1;
							this.getConfig().set("kingdom1.item4", k1i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i4 += 1;
							this.getConfig().set("kingdom2.item4", k2i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i4 += 1;
							this.getConfig().set("kingdom3.item4", k3i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i4 += 1;
							this.getConfig().set("kingdom4.item4", k4i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i4 += 1;
							this.getConfig().set("kingdom5.item4", k5i4);
							this.saveConfig();
						}	
					}	
				}
				else if (args[0].equalsIgnoreCase("info")) {
					if ((!args[1].equalsIgnoreCase(dItemNm1))&&(!args[1].equalsIgnoreCase(dItemNm2))&&(!args[1].equalsIgnoreCase(dItemNm3))&&(!args[1].equalsIgnoreCase(dItemNm4))){
						sender.sendMessage("You can only check the following items:");
						sender.sendMessage(dItemNm1);
						sender.sendMessage(dItemNm2);
						sender.sendMessage(dItemNm3);
						sender.sendMessage(dItemNm4);
						return true;
					}
					findWinner();
					String cappedArg = args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
					sender.sendMessage("     -= Five Kingdoms " + cappedArg + " Contributions =-");
					if(args[1].equalsIgnoreCase(dItemNm1)) {
						sender.sendMessage("            Current Winner Is : " + i1Winner);
						sender.sendMessage("");
						sender.sendMessage(kingdom1 + " : " + k1i1);
						sender.sendMessage(kingdom2 + " : " + k2i1);
						sender.sendMessage(kingdom3 + " : " + k3i1);
						sender.sendMessage(kingdom4 + " : " + k4i1);
						sender.sendMessage(kingdom5 + " : " + k5i1);
						
					}
					if(args[1].equalsIgnoreCase(dItemNm2)) {
						sender.sendMessage("            Current Winner Is : " + i2Winner);
						sender.sendMessage("");
						sender.sendMessage(kingdom1 + " : " + k1i2);
						sender.sendMessage(kingdom2 + " : " + k2i2);
						sender.sendMessage(kingdom3 + " : " + k3i2);
						sender.sendMessage(kingdom4 + " : " + k4i2);
						sender.sendMessage(kingdom5 + " : " + k5i2);
					}
					if(args[1].equalsIgnoreCase(dItemNm3)) {
						sender.sendMessage("            Current Winner Is : " + i3Winner);
						sender.sendMessage("");
						sender.sendMessage(kingdom1 + " : " + k1i3);
						sender.sendMessage(kingdom2 + " : " + k2i3);
						sender.sendMessage(kingdom3 + " : " + k3i3);
						sender.sendMessage(kingdom4 + " : " + k4i3);
						sender.sendMessage(kingdom5 + " : " + k5i3);
					}
					if(args[1].equalsIgnoreCase(dItemNm4)) {
						sender.sendMessage("            Current Winner Is : " + i4Winner);
						sender.sendMessage("");
						sender.sendMessage(kingdom1 + " : " + k1i4);
						sender.sendMessage(kingdom2 + " : " + k2i4);
						sender.sendMessage(kingdom3 + " : " + k3i4);
						sender.sendMessage(kingdom4 + " : " + k4i4);
						sender.sendMessage(kingdom5 + " : " + k5i4);
					}
				}
			}	
			else if (args.length==3){
				if (args[0].equals("add")) {
					int itemAmount = Integer.parseInt(args[2]);
					if ((!args[1].equalsIgnoreCase(dItemNm1))&&(!args[1].equalsIgnoreCase(dItemNm2))&&(!args[1].equalsIgnoreCase(dItemNm3))&&(!args[1].equalsIgnoreCase(dItemNm4))) {
						sender.sendMessage("You can only donate the following items:");
						sender.sendMessage(dItemNm1);
						sender.sendMessage(dItemNm2);
						sender.sendMessage(dItemNm3);
						sender.sendMessage(dItemNm4);
						return true;
					}
					else if(args[1].equalsIgnoreCase(dItemNm1)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId1), 1, (short) dItemMeta1), itemAmount)){
							sender.sendMessage("You don't have that much " + dItemNm1 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm1 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId1), itemAmount, (byte) dItemMeta1));
						if (this.pKingdom.equals(kingdom1)){
							k1i1 += itemAmount;
							this.getConfig().set("kingdom1.item1", k1i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i1 += itemAmount;
							this.getConfig().set("kingdom2.item1", k2i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i1 += itemAmount;
							this.getConfig().set("kingdom3.item1", k3i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i1 += itemAmount;
							this.getConfig().set("kingdom4.item1", k4i1);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i1 += itemAmount;
							this.getConfig().set("kingdom5.item1", k5i1);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm2)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId2), 1, (short) dItemMeta2), itemAmount)){
							sender.sendMessage("You don't have that much " + dItemNm2 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm2 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId2), itemAmount, (byte) dItemMeta2));
						if (this.pKingdom.equals(kingdom1)){
							k1i2 += itemAmount;
							this.getConfig().set("kingdom1.item2", k1i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i2 += itemAmount;
							this.getConfig().set("kingdom2.item2", k2i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i2 += itemAmount;
							this.getConfig().set("kingdom3.item2", k3i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i2 += itemAmount;
							this.getConfig().set("kingdom4.item2", k4i2);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i2 += itemAmount;
							this.getConfig().set("kingdom5.item2", k5i2);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm3)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId3), 1, (short) dItemMeta3), itemAmount)){
							sender.sendMessage("You don't have that much " + dItemNm3 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm3 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId3), itemAmount, (byte) dItemMeta3));
						if (this.pKingdom.equals(kingdom1)){
							k1i3 += itemAmount;
							this.getConfig().set("kingdom1.item3", k1i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i3 += itemAmount;
							this.getConfig().set("kingdom2.item3", k2i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i3 += itemAmount;
							this.getConfig().set("kingdom3.item3", k3i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i3 += itemAmount;
							this.getConfig().set("kingdom4.item3", k4i3);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i3 += itemAmount;
							this.getConfig().set("kingdom5.item3", k5i3);
							this.saveConfig();
						}
					}
					else if(args[1].equalsIgnoreCase(dItemNm4)) {
						if (!player.getInventory().containsAtLeast(new ItemStack(Material.getMaterial(dItemId4), 1, (short) dItemMeta4), itemAmount)){
							sender.sendMessage("You don't have that much " + dItemNm4 + "!" );
							return true;
						}
						sender.sendMessage(this.pKingdom + " thanks you for your generous donation of " + dItemNm4 + "!");
						player.getInventory().removeItem(new ItemStack(Material.getMaterial(dItemId4), itemAmount, (byte) dItemMeta4));
						if (this.pKingdom.equals(kingdom1)){
							k1i4 += itemAmount;
							this.getConfig().set("kingdom1.item4", k1i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom2)){
							k2i4 += itemAmount;
							this.getConfig().set("kingdom2.item4", k2i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom3)){
							k3i4 += itemAmount;
							this.getConfig().set("kingdom3.item4", k3i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom4)){
							k4i4 += itemAmount;
							this.getConfig().set("kingdom4.item4", k4i4);
							this.saveConfig();
						}
						else if (this.pKingdom.equals(kingdom5)){
							k5i4 += itemAmount;
							this.getConfig().set("kingdom5.item4", k5i4);
							this.saveConfig();
						}
					}
				}
			}
			fillVariables();
			findWinner();
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
	
	public void interestEcon(){
		Player[] players = getServer().getOnlinePlayers();
		for (int i = 0; i < players.length; i++){
			if(players[i].hasPermission("contribute.1")){
				this.pKingdom = kingdom1;
			}
			else if(players[i].hasPermission("contribute.2")){
				this.pKingdom = kingdom2;
			}
			else if(players[i].hasPermission("contribute.3")){
				this.pKingdom = kingdom3;
			}
			else if(players[i].hasPermission("contribute.4")){
				this.pKingdom = kingdom4;
			}
			else if(players[i].hasPermission("contribute.5")){
				this.pKingdom = kingdom5;
			}
			if(i2Winner.equalsIgnoreCase(pKingdom)){
				String playerName = players[i].getName();
				econ.depositPlayer(playerName, (double) 100);
			}
		}
	}
	
	public void feedAura(){
		Player[] players = getServer().getOnlinePlayers();
		for (int i = 0; i < players.length; i++){
			if(players[i].hasPermission("contribute.1")){
				this.pKingdom = kingdom1;
			}
			else if(players[i].hasPermission("contribute.2")){
				this.pKingdom = kingdom2;
			}
			else if(players[i].hasPermission("contribute.3")){
				this.pKingdom = kingdom3;
			}
			else if(players[i].hasPermission("contribute.4")){
				this.pKingdom = kingdom4;
			}
			else if(players[i].hasPermission("contribute.5")){
				this.pKingdom = kingdom5;
			}
			if(i1Winner.equalsIgnoreCase(pKingdom)){
				int food = players[i].getFoodLevel();
				food += 1;
				players[i].setFoodLevel(food);
			}
		}
	}
	
	public void fillVariables(){
		
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
		
		k1i1 = getConfig().getInt("kingdom1.item1");
		k1i2 = getConfig().getInt("kingdom1.item2");
		k1i3 = getConfig().getInt("kingdom1.item3");
		k1i4 = getConfig().getInt("kingdom1.item4");
		
		k2i1 = getConfig().getInt("kingdom2.item1");
		k2i2 = getConfig().getInt("kingdom2.item2");
		k2i3 = getConfig().getInt("kingdom2.item3");
		k2i4 = getConfig().getInt("kingdom2.item4");
		
		k3i1 = getConfig().getInt("kingdom3.item1");
		k3i2 = getConfig().getInt("kingdom3.item2");
		k3i3 = getConfig().getInt("kingdom3.item3");
		k3i4 = getConfig().getInt("kingdom3.item4");
		
		k4i1 = getConfig().getInt("kingdom4.item1");
		k4i2 = getConfig().getInt("kingdom4.item2");
		k4i3 = getConfig().getInt("kingdom4.item3");
		k4i4 = getConfig().getInt("kingdom4.item4");
		
		k5i1 = getConfig().getInt("kingdom5.item1");
		k5i2 = getConfig().getInt("kingdom5.item2");
		k5i3 = getConfig().getInt("kingdom5.item3");
		k5i4 = getConfig().getInt("kingdom5.item4");
		
	}
	public void findWinner(){
		i1cont.put(kingdom1, k1i1);
		i1cont.put(kingdom2, k2i1);
		i1cont.put(kingdom3, k3i1);
		i1cont.put(kingdom4, k4i1);
		i1cont.put(kingdom5, k5i1);
		
		i2cont.put(kingdom1, k1i2);
		i2cont.put(kingdom2, k2i2);
		i2cont.put(kingdom3, k3i2);
		i2cont.put(kingdom4, k4i2);
		i2cont.put(kingdom5, k5i2);
		
		i3cont.put(kingdom1, k1i3);
		i3cont.put(kingdom2, k2i3);
		i3cont.put(kingdom3, k3i3);
		i3cont.put(kingdom4, k4i3);
		i3cont.put(kingdom5, k5i3);
		
		i4cont.put(kingdom1, k1i4);
		i4cont.put(kingdom2, k2i4);
		i4cont.put(kingdom3, k3i4);
		i4cont.put(kingdom4, k4i4);
		i4cont.put(kingdom5, k5i4);
		
		Map.Entry<String, Integer> maxEntry = null;
		for (Map.Entry<String, Integer> entry : i1cont.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		i1Winner = maxEntry.getKey();
		Map.Entry<String, Integer> maxEntry2 = null;
		for (Map.Entry<String, Integer> entry2 : i2cont.entrySet())
		{
		    if (maxEntry2 == null || entry2.getValue().compareTo(maxEntry2.getValue()) > 0)
		    {
		        maxEntry2 = entry2;
		    }
		}
		i2Winner = maxEntry2.getKey();
		Map.Entry<String, Integer> maxEntry3 = null;
		for (Map.Entry<String, Integer> entry : i3cont.entrySet())
		{
		    if (maxEntry3 == null || entry.getValue().compareTo(maxEntry3.getValue()) > 0)
		    {
		        maxEntry3 = entry;
		    }
		}
		i3Winner = maxEntry3.getKey();
		Map.Entry<String, Integer> maxEntry4 = null;
		for (Map.Entry<String, Integer> entry : i4cont.entrySet())
		{
		    if (maxEntry4 == null || entry.getValue().compareTo(maxEntry4.getValue()) > 0)
		    {
		        maxEntry4 = entry;
		    }
		}
		i4Winner = maxEntry4.getKey();
	}
	
}


