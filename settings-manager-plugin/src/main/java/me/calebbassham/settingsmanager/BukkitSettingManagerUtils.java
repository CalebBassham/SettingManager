package me.calebbassham.settingsmanager;

import org.bukkit.command.CommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BukkitSettingManagerUtils {

    public static void handleCommand(SettingsManager manager, String[] args) throws SettingParserException, SettingValueValidatorException, CommandException {
        // combine each arg until a setting is found

        LinkedList<String> args2 = new LinkedList<>(Arrays.asList(args));

        Setting setting = null;
        StringBuilder sb = new StringBuilder();
        while (setting == null) {
            String toAdd = args2.poll();
            if (toAdd == null) throw new CommandException("Not a setting.");

            if (sb.length() != 0) sb.append(" ");

            sb.append(toAdd);

            setting = manager.getSetting(sb.toString());
        }

        // pass remaining args to setting parser

        String toParse = String.join(" ", args2);
        SettingParser parser = manager.getParser(setting.getType());
        if (parser == null) throw new SettingParserException("No parser for type \"" + setting.getType().getName() + "\"");

        Object value = parser.parse(toParse);

        setting.setValue(value);
    }

    public static List<String> handleTabCompleter(SettingsManager manager, String[] args) {
        // get setting
        LinkedList<String> args2 = new LinkedList<>(Arrays.asList(args));

        Setting setting = null;
        StringBuilder sb = new StringBuilder();
        while (setting == null) {
            String toAdd = args2.poll();
            if (toAdd == null) {
                return manager.getSettings().stream()
                        .map(s -> s.getName().toLowerCase())
                        .filter(name -> name.startsWith(String.join(" ", args).toLowerCase()))
                        .map(name -> name.split(" "))
                        .map(parts -> Arrays.stream(parts, args.length - 1, parts.length).collect(Collectors.joining(" ")))
                        .collect(Collectors.toList());
            }

            if (sb.length() != 0) sb.append(" ");

            sb.append(toAdd);

            setting = manager.getSetting(sb.toString());
        }

        // Allow a setting to give tab-completions? Throw in the towel and move this to SettingManager class? Give up?
        if (setting instanceof SettingValueSuggester) {
            SettingValueSuggester suggestor = (SettingValueSuggester) setting;
            return suggestor.handleSuggestions(setting, args2.toArray(new String[0])).stream()
                    .filter(completions -> completions.toLowerCase().startsWith(String.join(" ", args2).toLowerCase()))
                    .map(completion -> completion.split(" "))
                    .map(parts -> Arrays.stream(parts, args2.size() - 1, parts.length).collect(Collectors.joining(" ")))
                    .collect(Collectors.toList());
        }

        return emptyList();
    }

    private static <T> List<T> emptyList() {
        return new ArrayList<>();
    }

}
