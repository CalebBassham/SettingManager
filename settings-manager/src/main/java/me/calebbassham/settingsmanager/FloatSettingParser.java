package me.calebbassham.settingsmanager;

public class FloatSettingParser implements SettingParser<Float> {

    @Override
    public Class<Float> geType() {
        return Float.class;
    }

    @Override
    public Float parse(String string) throws SettingParserException {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            throw new SettingParserException("\"" + string + "\" is not a float.");
        }
    }
}
