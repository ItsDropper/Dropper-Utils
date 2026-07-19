package name.dropperutils.client;

import net.fabricmc.api.ClientModInitializer;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.feature.FeatureRegistry;
import name.dropperutils.client.init.Events;
import name.dropperutils.client.init.HudRegistration;
import name.dropperutils.client.init.Keybinds;

public class DropperUtilsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		DropperUtilsConfig.load();
		FeatureRegistry.loadConfigs();

		Keybinds.register();
		HudRegistration.register();
		Events.register();
	}
}