package me.calebbassham.settingsmanager;

public class DoubleSettingParser implements SettingParser<Double> {

    @Override
    public Class<Double> geType() {
        return Double.class;
    }

    @Override
    public Double parse(String string) throws SettingParserException {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            throw new SettingParserException("\"" + string + "\" is not a double.");
        }
    }
}
