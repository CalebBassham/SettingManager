package me.calebbassham.settingsmanager;

public interface SettingParser<T> {

    Class<T> geType();

    T parse(String string) throws SettingParserException;

}
