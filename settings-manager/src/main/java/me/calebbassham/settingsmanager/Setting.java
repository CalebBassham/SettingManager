package me.calebbassham.settingsmanager;

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
