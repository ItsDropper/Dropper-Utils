package name.dropperutils.client.hud;

import name.dropperutils.client.feature.TotemCounterFeature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TotemCounterHud {

    public static void render(GuiGraphics graphics) {

        if (!TotemCounterFeature.INSTANCE.isEnabled()) return;

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) return;

        int count = mc.player.getInventory().countItem(Items.TOTEM_OF_UNDYING);

        int centerX = graphics.guiWidth() / 2 + 18;
        int y = graphics.guiHeight() - 58;

        ItemStack stack = new ItemStack(Items.TOTEM_OF_UNDYING);

        int totemX = centerX - 25;

        graphics.renderItem(stack, totemX, y);

        graphics.pose().pushMatrix();

        graphics.pose().scale(1.30f);

        graphics.drawString(
            mc.font,
            String.valueOf(count),
            (int)((totemX + 20) / 1.30f),
            (int)((y + 5) / 1.30f),
            0xFF00FF00
        );

        graphics.pose().popMatrix();
    }
}
