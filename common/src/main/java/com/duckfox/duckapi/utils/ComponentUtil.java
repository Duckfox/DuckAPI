package com.duckfox.duckapi.utils;

import net.md_5.bungee.api.chat.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponentUtil {
    private ComponentUtil() {
    }

    public static final Pattern COMPONENT_PATTERN = Pattern.compile("<(?<pre>.*?)>(?<in>.*?)</(?<aft>.*?)>");
    public static final Pattern HOVER_PATTERN = Pattern.compile(" hover=\"(.*?)\"");

    public static List<String> split(String text) {
        return StringUtil.splitStr(text, COMPONENT_PATTERN);
    }

    public static BaseComponent toComponent(String str, ComponentNode... nodes) {
        return toComponent(split(str), nodes);
    }

    public static BaseComponent toComponent(List<String> elements, ComponentNode... nodes) {
        TextComponent parentComp = new TextComponent("");
        for (String element : elements) {
            BaseComponent component = new TextComponent();
            boolean found = false;
            for (ComponentNode node : nodes) {
                Matcher matcher = COMPONENT_PATTERN.matcher(element);
                if (matcher.find()) {
                    if (matcher.group("pre").startsWith(node.nodeName)) {
                        found = true;
                        if (node.component != null) {
                            component = node.component;
                        } else {
                            component = new TextComponent(matcher.group("in"));
                            if (node.command != null) {
                                component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, node.command));
                            }
                            Matcher matcher1 = HOVER_PATTERN.matcher(matcher.group("pre"));
                            if (matcher1.find()) {
                                component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(matcher1.group(1)).create()));
                            }
                        }
                        break;
                    }
                }
            }
            if (!found) {
                component = new TextComponent(element);
            }
            parentComp.addExtra(component);
        }
        return parentComp;
    }

    public static class ComponentNode {
        String nodeName;
        String command;
        BaseComponent component;

        public ComponentNode(String nodeName) {
            this.nodeName = nodeName;
        }

        public ComponentNode(String nodeName, String command) {
            this(nodeName);
            this.command = command;
        }

        public ComponentNode(String nodeName, BaseComponent component) {
            this.nodeName = nodeName;
            this.component = component;
        }
    }
}
