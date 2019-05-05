package me.calebbassham.settingsmanager;

public class SettingParsers {

    public static SettingParser<Integer> INTEGER = new IntegerSettingParser();
    public static SettingParser<String> STRING = new StringSettingParser();
    public static SettingParser<Double> DOUBLE = new DoubleSettingParser();
    public static SettingParser<Float> FLOAT = new FloatSettingParser();
    public static SettingParser<Boolean> BOOLEAN = new BooleanSettingParser();
    public static SettingParser<Long> LONG = new LongSettingParser();


    public static SettingParser[] ALL = new SettingParser[]{
            INTEGER, STRING, DOUBLE, FLOAT, BOOLEAN, LONG
    };
}
