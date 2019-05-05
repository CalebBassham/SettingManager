package me.calebbassham.settingsmanager;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class SettingsManager {

    private HashMap<String, Setting> settings = new HashMap<>();
    private HashSet<SettingParser> settingParsers = new HashSet<>();

    public void register(Setting setting) {
        this.settings.put(setting.getName().toLowerCase(), setting);
    }

    private void unregister(Setting setting) {
        this.settings.remove(setting.getName().toLowerCase());
    }

    private void unregister(String name) {
        this.settings.remove(name.toLowerCase());
    }

    public Setting getSetting(String name) {
        return settings.get(name.toLowerCase());
    }

    public <U extends Setting> Setting getSetting(final Class<U> clazz) {
        return settings.values().stream()
                .filter(setting -> setting.getClass().equals(clazz))
                .findFirst()
                .orElse(null);
    }

    public void registerParser(SettingParser parser) {
        this.settingParsers.add(parser);
    }

    public void registerParsers(SettingParser... parsers) {
        Arrays.stream(parsers).forEach(this::registerParser);
    }

    protected final void registerProvidedParsers() {
        this.registerParsers(SettingParsers.ALL);
    }

    public <T> SettingParser<T> getParser(Class clazz) {
        return settingParsers.stream()
                .filter(settingParser -> settingParser.geType().equals(clazz))
                .findFirst()
                .orElse(null);
    }

    public Collection<Setting> getSettings() {
        return settings.values();
    }

}
