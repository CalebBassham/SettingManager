package me.calebbassham.settingsmanager;

public interface SettingValueValidator<T> {

    /**
     * Limit what a setting can be changed to.
     * @param newValue The new value the setting is being set to.
     * @return An error or null.
     */
    String validate(T newValue);

}
