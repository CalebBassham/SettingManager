package me.calebbassham.settingsmanager;

public class LongSettingParser implements SettingParser<Long> {

    @Override
    public Class<Long> geType() {
        return Long.class;
    }

    @Override
    public Long parse(String string) throws SettingParserException {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            throw new SettingParserException("\"" + string + "\" is not a long.");
        }
    }
}
