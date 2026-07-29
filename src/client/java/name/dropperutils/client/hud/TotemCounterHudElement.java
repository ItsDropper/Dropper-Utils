package name.dropperutils.client.hud;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.feature.HudEditorFeature;
import name.dropperutils.client.feature.TotemCounterFeature;
import name.dropperutils.client.hudeditor.HudElement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TotemCounterHudElement extends HudElement {

    private float scale;

    public TotemCounterHudElement() {
        super(
                DropperUtilsConfig.get().totemHudX,
                DropperUtilsConfig.get().totemHudY
        );

        scale = DropperUtilsConfig.get().totemCounterScale;

    }


    @Override
    public void render(GuiGraphics graphics) {

        if (!TotemCounterFeature.INSTANCE.isEnabled()
                && !HudEditorFeature.INSTANCE.isEnabled()) {
            return;
        }


        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }


        int count = mc.player
                .getInventory()
                .countItem(Items.TOTEM_OF_UNDYING);


        ItemStack stack = new ItemStack(Items.TOTEM_OF_UNDYING);


        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);
        graphics.pose().scale(scale);

        graphics.renderItem(
                stack,
                0,
                0
        );

        graphics.drawString(
                mc.font,
                String.valueOf(count),
                20,
                5,
                0xFF00FF00
        );

        graphics.pose().popMatrix();


        if (HudEditorFeature.INSTANCE.isEnabled()) {

            graphics.fill(
                    x - 2,
                    y - 2,
                    x + getWidth(),
                    y + getHeight(),
                    0x55FFFFFF
            );
        }
    }


    @Override
    public void savePosition() {

        DropperUtilsConfig.get().totemHudX = x;
        DropperUtilsConfig.get().totemHudY = y;

        DropperUtilsConfig.save();
    }


    @Override
    public int getWidth() {
        return 45;
    }


    @Override
    public int getHeight() {
        return 35;
    }

    public void setScale(float value) {



        scale = value;

        DropperUtilsConfig.get().totemCounterScale = value;
        DropperUtilsConfig.save();
    }


    public float getScale() {

        return scale;
    }
}