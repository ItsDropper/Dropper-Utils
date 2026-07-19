package name.dropperutils.client.feature;


public abstract class Feature {

    private final String name;
    private boolean enabled;
    private final Category category;

    public Feature(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    public Category getCategory() {
        return category;
    }
    public void saveConfig() {}

    public void loadConfig() {}

    public void setEnabled(boolean enabled) {

        if (this.enabled == enabled) {
            return;
        }

        this.enabled = enabled;


        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {}

    public void onDisable() {}
}