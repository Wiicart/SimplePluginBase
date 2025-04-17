package com.pedestriamc.common.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Messenger<T extends Enum<T> & CommonMessage> {

    private final EnumMap<T, Object> enumMap;
    private final String prefix;
    private final String strippedPrefix;

    /**
     * Constructor for the Messenger
     * @param config The messages config file
     * @param prefix The plugin prefix, COLORED!
     * @param clazz The enum class for messages.
     */
    public Messenger(FileConfiguration config, String prefix, Class<T> clazz)
    {
        enumMap = new EnumMap<>(clazz);
        this.prefix = prefix;
        strippedPrefix = ChatColor.stripColor(translate(prefix));

        for (T msg : clazz.getEnumConstants()) {
            String configValue = msg.getKey();
            try {
                if(config.isList(configValue)) {
                    enumMap.put(msg, config.getStringList(configValue).toArray(new String[0]));
                } else {
                    enumMap.put(msg, config.getString(configValue));
                }
            } catch (NullPointerException e) {
                Bukkit.getLogger().warning(strippedPrefix + " Unable to find message for " + msg);
            }
        }
    }

    /**
     * Sends a message.
     *
     * @param sender  Who the message should be sent to.
     * @param message The Message to be sent.
     */
    public void sendMessage(CommandSender sender, T message)
    {
        Object obj = enumMap.get(message);
        if(obj instanceof String[] msg) {
            for (String str : msg) {
                sender.sendMessage(translate(str));
            }
        } else if(obj instanceof String) {
            sender.sendMessage(translate(prefix + enumMap.get(message)));
        } else {
            Bukkit.getLogger().info(strippedPrefix + " Unknown object type or value not found for message " + message.toString());
        }
    }

    /**
     * Sends a message with placeholders.
     *
     * @param sender       Who the message should be sent to.
     * @param message      The Message to be sent.
     * @param placeholders A hashmap containing keys for the placeholder and values of the replacement.
     */
    public void sendMessage(CommandSender sender, T message, Map<String, String> placeholders)
    {
        Object msg = enumMap.get(message);
        if(msg instanceof String[] array) {
            for (String str : array) {
                for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                    str = str.replace(entry.getKey(), entry.getValue());
                }
                sender.sendMessage(translate(str));
            }
            return;
        }
        if(msg instanceof String str) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                str = str.replace(entry.getKey(), entry.getValue());
            }
            sender.sendMessage(translate(prefix + str));
            return;
        }
        Bukkit.getLogger().info(strippedPrefix + " Unknown object type or value not found for message " + message.toString());
    }

    private String translate(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
