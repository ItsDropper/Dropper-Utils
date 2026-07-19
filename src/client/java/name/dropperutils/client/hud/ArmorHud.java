package name.dropperutils.client.hud;

import name.dropperutils.client.feature.ArmorHudFeature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ArmorHud {

    public static void render(GuiGraphics graphics) {

        if (!ArmorHudFeature.INSTANCE.isEnabled()) return;

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) return;

        ItemStack[] armor = new ItemStack[]{
                mc.player.getItemBySlot(EquipmentSlot.HEAD),
                mc.player.getItemBySlot(EquipmentSlot.CHEST),
                mc.player.getItemBySlot(EquipmentSlot.LEGS),
                mc.player.getItemBySlot(EquipmentSlot.FEET)
        };

        int x = 20;
        int y = graphics.guiHeight() - 140;

        for (ItemStack item : armor) {

            if (item.isEmpty()) {
                y += 22;
                continue;
            }

            int durability = item.getMaxDamage() - item.getDamageValue();

            float percent = (float) durability / item.getMaxDamage();

            int color;

            if (percent > 0.5f) {
                color = 0xFF00FF00;
            } else if (percent > 0.25f) {
                color = 0xFFFFFF00;
            } else {
                color = 0xFFFF0000;
            }

            graphics.renderItem(item, x, y);

            graphics.drawString(
                    mc.font,
                    durability + "",
                    x + 25,
                    y + 8,
                    color,
                    false
            );

            int barWidth = 16;
            int filled = (int)(barWidth * percent);

            graphics.fill(
                    x,
                    y + 17,
                    x + barWidth,
                    y + 18,
                    0xFF555555
            );

            graphics.fill(
                    x,
                    y + 17,
                    x + filled,
                    y + 18,
                    color
            );

            y += 22;
        }
    }
}