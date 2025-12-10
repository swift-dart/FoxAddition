package zoruafan.foxaddition.utils;

import org.bukkit.entity.Player;
import org.geysermc.geyser.api.GeyserApi;

public enum Geyser {
	INSTANCE;
	
	public boolean isBedrockUser(Player e) {
		try {
			GeyserApi api = GeyserApi.api();
			return api.isBedrockPlayer(e.getUniqueId());
		} catch (Exception ignored) {
			return false;
		}
	}
}