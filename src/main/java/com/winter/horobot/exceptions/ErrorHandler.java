package com.winter.horobot.exceptions;

import com.winter.horobot.Main;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

public class ErrorHandler {

	public static void log(Throwable t) {
		log(t, "");
	}

	public static void log(Throwable t, String meta) {
		IChannel c = Main.getClient().getChannelByID(Long.parseLong(Main.config.get(Main.ConfigValue.ERROR_CHANNEL)));
		EmbedBuilder e = new EmbedBuilder();
		e.setLenient(false);
		e.withColor(Color.red);
		e.withTitle("An error has occured! " + meta);
		e.appendDescription("```\n");
		e.appendDescription(t.getMessage().replaceAll(" ", "\u00A0").trim()); // Nobreak Space
		e.appendDescription("\n```");
		c.sendMessage(e.build());
	}

}
