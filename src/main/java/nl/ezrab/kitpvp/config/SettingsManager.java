package nl.ezrab.kitpvp.config;

import nl.ezrab.kitpvp.KitPvP;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class SettingsManager {

    protected KitPvP kitPvP;

    private File file;
    private FileConfiguration config;

    public SettingsManager(KitPvP kitPvP, String fileName) {
        this.kitPvP = kitPvP;

        if (!this.kitPvP.getDataFolder().exists()) {
            this.kitPvP.getDataFolder().mkdir();
        }

        this.file = new File(this.kitPvP.getDataFolder(), fileName + ".yml");

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public Object get(String path) {
        return this.config.get(path);
    }

    public Set<String> getKeys() {
        return this.config.getKeys(false);
    }

    public void set(String path, Object value) {
        this.config.set(path, value);
        this.save();
    }

    public boolean contains(String path) {
        return this.config.contains(path);
    }

    public ConfigurationSection createSection(String path) {
        ConfigurationSection section = this.config.createSection(path);
        this.save();
        return section;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
