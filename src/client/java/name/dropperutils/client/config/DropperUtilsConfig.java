package name.dropperutils.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DropperUtilsConfig {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final File FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("dropperutils.json")
            .toFile();


    public boolean armorHud = false;
    public boolean totemCounter = false;
    public boolean fullbright = true;
    public boolean saturation = true;


    public boolean zoom = true;
    public boolean smoothZoom = true;
    public boolean anchorOptimizer = false;
    public boolean debugHud = false;
    public boolean noExplosionEffects = false;
    public boolean lowFire = false;
    public boolean potionEffects = false;
    public boolean hudEditor = false;
    public boolean armorHudHorizontal = true;

    public int zoomFov = 30;
    public float zoomSpeed = 0.15f;
    public float totemCounterScale = 1.0f;
    public int debugHudX = 5;
    public int debugHudY = 300;
    public int armorHudX = 20;
    public int armorHudY = 200;
    public int totemHudX = 300;
    public int totemHudY = 200;
    public int potionHudX = 5;
    public int potionHudY = 20;


    private static DropperUtilsConfig INSTANCE;


    public static DropperUtilsConfig get() {

        if (INSTANCE == null) {
            load();
        }

        return INSTANCE;
    }


    public static void save() {

        try (FileWriter writer = new FileWriter(FILE)) {

            GSON.toJson(INSTANCE, writer);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    public static void load() {

        try {

            if (FILE.exists()) {

                FileReader reader = new FileReader(FILE);

                INSTANCE = GSON.fromJson(
                        reader,
                        DropperUtilsConfig.class
                );

                reader.close();

            } else {

                INSTANCE = new DropperUtilsConfig();
                save();

            }

        } catch (Exception e) {

            e.printStackTrace();
            INSTANCE = new DropperUtilsConfig();

        }
    }
}