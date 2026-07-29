package name.dropperutils.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class AnchorScanner {

    public static void scanNearbyAnchors() {

        Minecraft client = Minecraft.getInstance();

        if (client.level == null || client.player == null) {
            return;
        }

        long start = System.nanoTime();

        BlockPos playerPos = client.player.blockPosition();
        Vec3 look = client.player.getLookAngle();

        for (int x = -3; x <= 5; x++) {

            for (int y = -2; y <= 3; y++) {

                for (int z = -3; z <= 5; z++) {

                    BlockPos pos = playerPos.offset(x, y, z);

                    Vec3 direction = new Vec3(x, y, z);

                    if (direction.lengthSqr() > 0) {
                        direction = direction.normalize();
                    }

                    if (look.dot(direction) < -0.25) {
                        continue;
                    }

                    if (client.level.getBlockState(pos).is(Blocks.RESPAWN_ANCHOR)) {
                        AnchorTracker.track(pos);
                    }
                }
            }
        }

        AnchorStats.finishScan(start);
    }
}