package name.dropperutils.client.init;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybinds {

    public static KeyMapping fullbrightKey;
    public static KeyMapping zoomKey;
    public static KeyMapping guiKey;
    public static KeyMapping hudEditorKey;

    private static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(
                    Identifier.fromNamespaceAndPath(
                            "dropperutils",
                            "category"
                    )
            );

    public static void register() {

        fullbrightKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.dropperutils.fullbright",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_K,
                        CATEGORY
                )
        );

        zoomKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.dropperutils.zoom",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_C,
                        CATEGORY
                )
        );

        guiKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.dropperutils.gui",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_M,
                        CATEGORY
                )
        );

        hudEditorKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.dropperutils.hud_editor",
                        InputConstants.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        CATEGORY
                )
        );
    }
}

