package name.dropperutils;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropperUtils implements ModInitializer {
	public static final String MOD_ID = "dropperutils";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static double gamma = 1.0D;
	public static boolean gammaEnabled = false;

	@Override
	public void onInitialize() {
		LOGGER.info("DropperUtils loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
