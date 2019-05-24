package me.calebbassham.settingsmanager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumSetting<T extends Enum> extends Setting<T> implements SettingValueSuggester {

    public EnumSetting(String name, Class<T> clazz) {
        super(name, clazz);
    }

    public EnumSetting(String name, T initialValue) {
        super(name, initialValue);
    }

    @Override
    public String displayValue() {
        return getName().replace("_", " ").toLowerCase();
    }

    @Override
    public List<String> handleSuggestions(Setting setting, String[] args) {
        T[] values = (T[]) setting.getType().getEnumConstants();
        return Arrays.stream(values)
                .map(Enum::name)
                .map(name -> name.replace("_", " "))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
