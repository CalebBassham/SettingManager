package me.calebbassham.settingsmanager;

public class StringSettingParser implements SettingParser<String> {

    @Override
    public Class<String> geType() {
        return String.class;
    }

    @Override
    public String parse(String string) {
        return string;
    }
}
