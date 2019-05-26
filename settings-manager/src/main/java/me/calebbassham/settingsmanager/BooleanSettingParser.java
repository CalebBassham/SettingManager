package me.calebbassham.settingsmanager;

public class BooleanSettingParser implements SettingParser<Boolean> {

    @Override
    public Class<Boolean> geType() {
        return Boolean.class;
    }

    @Override
    public Boolean parse(String string) throws SettingParserException {
        switch (string.toLowerCase()) {
            case "true":
            case "yes":
            case "enabled":
                return true;
            case "false":
            case "no":
            case "disabled":
                return false;
        }

        throw new SettingParserException("\"" + string + "\" is not a boolean.");
    }
}
