package me.calebbassham.settingsmanager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Setting<T> {

    private String name;
    private T value;
    private Class type;

    public Setting(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public Setting(String name, T initialValue) {
        this.name = name;
        if (initialValue == null) throw new IllegalArgumentException("initialValue must not be null.");
        this.value = initialValue;
        this.type = initialValue.getClass();
    }

    public Setting(Class type) {
        this.name = getNameFromClass(this.getClass());
        this.type = type;
    }

    public Setting(T initialValue) {
        this.name = getNameFromClass(this.getClass());
        this.value = initialValue;
        this.type = initialValue.getClass();
    }

    private String getNameFromClass(Class clazz) {
        ArrayList<String> nameParts = new ArrayList<>();
        Pattern p = Pattern.compile("[A-Z]+(?:[^A-Z]+)?");
        Matcher m = p.matcher(clazz.getSimpleName());
        while (m.find()) {
            String str = m.group(0);
            if (str.equalsIgnoreCase("setting")) continue;
            nameParts.add(str);
        }
        return String.join(" ", nameParts);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) throws SettingValueValidatorException {
        if (this instanceof SettingValueValidator) {
            try {
                SettingValueValidator validator = (SettingValueValidator<T>) this;
                String err = validator.validate(value);
                if (err != null) {
                    throw new SettingValueValidatorException(err);
                }
            } catch (ClassCastException e) {
                throw new SettingValueValidatorException("Validator is not the right type.");
            }

        }
        this.value = value;
    }

    public String displayValue() {
        return value.toString();
    }

    public Class getType() {
        return this.type;
    }

}
