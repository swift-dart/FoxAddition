package zoruafan.foxaddition.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import zoruafan.foxaddition.FoxAdditionAPI;

public class GeyserManager extends FoxPlayer {
    FoxAdditionAPI api = FoxAdditionAPI.INSTANCE;
    JavaPlugin plugin = api.getPlugin();
    private Boolean floodgate = cF();
    private Boolean geyser = cG();
    
    public GeyserManager() { this.floodgate = cF(); }
    
    public boolean iB(Player e) {
        boolean isBedrock = false;
        this.floodgate = cF();
        this.geyser = cG();
        ConfigurationSection l = api.getFiles().getST().getConfigurationSection("bedrock.modules");
        if (l.getBoolean("prefix.enable", false)) {
            isBedrock = e.getName().startsWith(l.getString("prefix.value", "."));
            if (isBedrock) return true;
        }
        if (l.getBoolean("uuid.enable", false)) {
            isBedrock = e.getUniqueId().toString().startsWith("000000");
            if (isBedrock) return true;
        }
        if (geyser == true && l.getBoolean("geyser.enable", false) && l.getBoolean("floodgate.enable", true) == false) {
        	try { isBedrock = Geyser.INSTANCE.isBedrockUser(e); if (isBedrock) return true; } catch (Exception ignored) {}
        }
        if(floodgate == true && l.getBoolean("floodgate.enable", true)) {
            try { isBedrock = Floodgate.INSTANCE.isBedrockUser(e); if (isBedrock) return true; } catch (Exception ignored) {}
        }
        return isBedrock;
    }
    
    public String getDevice(Player e) {
        String de = "Java";
        ConfigurationSection l = api.getFiles().getST().getConfigurationSection("bedrock.modules.floodgate");
        if(floodgate == true && (l.getBoolean("device", true) && l.getBoolean("enable", true))) {
            try { de = Floodgate.INSTANCE.getDevice(e); return de; } catch (Exception ignored) {}
        }
        return de;
    }

    private Boolean cF() {
        return Bukkit.getPluginManager().isPluginEnabled("Floodgate");
    }
    private Boolean cG() {
    	return Bukkit.getPluginManager().isPluginEnabled("Geyser-Spigot");
    }
}