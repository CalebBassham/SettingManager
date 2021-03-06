package me.calebbassham.settingsmanager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumSetting<T extends Enum> extends Setting<T> implements SettingValueSuggester, SettingParser<T> {

    public EnumSetting(String name, Class<T> clazz) {
        super(name, clazz);
    }

    public EnumSetting(String name, T initialValue) {
        super(name, initialValue);
    }

    public EnumSetting(Class<T> clazz) {
        super(clazz);
    }

    public EnumSetting(T initialValue) {
        super(initialValue);
    }

    @Override
    public String displayValue() {
        return getValue().name().replace("_", " ").toLowerCase();
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

    @Override
    public Class<T> geType() {
        return this.getType();
    }

    @Override
    public T parse(String toParse) throws SettingParserException {
        try {
            return (T) Enum.valueOf(getType(), toParse.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SettingParserException("\"" + toParse + "\" is not an allowed value of " + getName() + ".");
        }
    }
}
