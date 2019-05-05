package me.calebbassham.settingsmanager;

import java.util.List;

public interface SettingValueSuggester {

    List<String> handleSuggestions(Setting setting, String[] args);

}
