package com.duckfox.duckapi.utils;

import com.duckfox.duckapi.DuckAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private StringUtil() {
    }

    public static String color(String str) {
        return replaceAll(str, "&", "§");
    }

    public static String papi(String str, Player player) {
        return DuckAPI.isPapiLoad ? PlaceholderAPI.setPlaceholders(player, str) : str;
    }

    public static String format(String str, Player player, String... args) {
        return format(papi(str, player), args);
    }

    public static String format(String str, String... args) {
        return replaceAll(color(str), args);
    }

    public static List<String> format(List<String> list, String... args) {
        list.replaceAll(str -> format(str, args));
        return list;
    }

    public static List<String> format(List<String> list, Player player, String... args) {
        list.replaceAll(str -> format(str, player, args));
        return list;
    }

    public static String replaceAll(String original, String... replacements) {
        if (replacements == null) {
            return original;
        }
        int num = replacements.length / 2;
        int count = 0;
        for (int i = 0; i < num; i++) {
            original = original.replace(replacements[count++], replacements[count++]);
        }
        return original;
    }
    public static List<String> splitStr(String input,Pattern pattern){
        ArrayList<String> list = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        int start = 0;
        int end = 0;
        while (matcher.find(start)){
            if (end < matcher.start()){
                list.add(input.substring(end, matcher.start()));
            }
            list.add(matcher.group());
            start = matcher.end();
            end = matcher.end();
        }
        if (start < input.length()){
            list.add(input.substring(start));
        }
        return list;
    }
}
