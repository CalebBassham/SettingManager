package me.calebbassham.settingsmanager;

public class IntegerSettingParser implements SettingParser<Integer> {
    @Override
    public Class<Integer> geType() {
        return Integer.class;
    }

    @Override
    public Integer parse(String string) throws SettingParserException {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new SettingParserException("\"" + string + "\" is not an integer.");
        }
    }
}
