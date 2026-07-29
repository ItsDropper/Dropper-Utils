package name.dropperutils.client.util;

import name.dropperutils.client.feature.AnchorOptimizerFeature;
import name.dropperutils.client.feature.DebugHudFeature;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AnchorOptimizer {

    private static final List<TrackedAnchor> TRACKED_ANCHORS = new ArrayList<>();
    private static final Set<BlockPos> TRACKED_POSITIONS = new HashSet<>();

    private static int activeTicks = 0;
    private static int scanTimer = 0;

    private static BlockPos lastPlayerPos;
    private static float lastYaw;
    private static float lastPitch;

    public static void tick() {

        Minecraft client = Minecraft.getInstance();

        if (client.level == null || client.player == null) {
            return;
        }

        // Debug only
        if (!AnchorOptimizerFeature.INSTANCE.isEnabled()) {

            if (DebugHudFeature.INSTANCE.isEnabled()) {
                scanTimer++;

                if (scanTimer >= 20) {
                    AnchorScanner.scanNearbyAnchors();
                    scanTimer = 0;
                }
            }

            return;
        }

        if (activeTicks > 0) {
            activeTicks--;
        }

        if (activeTicks <= 0) {
            return;
        }

        BlockPos playerPos = client.player.blockPosition();

        float yaw = client.player.getYRot();
        float pitch = client.player.getXRot();

        boolean moved =
                lastPlayerPos == null ||
                        !playerPos.equals(lastPlayerPos);

        boolean turned =
                Math.abs(yaw - lastYaw) > 15 ||
                        Math.abs(pitch - lastPitch) > 15;

        if (moved || turned) {

            lastPlayerPos = playerPos;
            lastYaw = yaw;
            lastPitch = pitch;

            AnchorScanner.scanNearbyAnchors();
        }

        scanTimer++;

        if (scanTimer >= 40) {
            AnchorScanner.scanNearbyAnchors();
            scanTimer = 0;
        }

        Iterator<TrackedAnchor> iterator = TRACKED_ANCHORS.iterator();

        while (iterator.hasNext()) {

            TrackedAnchor anchor = iterator.next();

            double distance = anchor.getPos().distSqr(playerPos);

            if (distance > 64 * 64) {

                TRACKED_POSITIONS.remove(anchor.getPos());
                iterator.remove();
                continue;
            }

            if (anchor.getTicks() % 10 != 0) {
                continue;
            }

            boolean exists =
                    client.level
                            .getBlockState(anchor.getPos())
                            .is(Blocks.RESPAWN_ANCHOR);

            if (!exists) {

                TRACKED_POSITIONS.remove(anchor.getPos());
                iterator.remove();
                continue;
            }

            anchor.setLoaded(true);
            anchor.tick();
        }
    }

    public static void trackAnchor(BlockPos pos) {

        if (TRACKED_POSITIONS.contains(pos)) {
            return;
        }

        TRACKED_POSITIONS.add(pos);
        TRACKED_ANCHORS.add(new TrackedAnchor(pos));
    }

    public static List<TrackedAnchor> getTrackedAnchors() {
        return TRACKED_ANCHORS;
    }

    public static void clear() {
        TRACKED_ANCHORS.clear();
        TRACKED_POSITIONS.clear();
    }

    public static void wakeUp() {
        activeTicks = 40;
    }
}