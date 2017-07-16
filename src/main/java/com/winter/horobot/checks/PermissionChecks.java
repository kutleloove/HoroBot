package com.winter.horobot.checks;

import com.winter.horobot.Main;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Permissions;

import java.util.function.Predicate;

public class PermissionChecks {

	public static Predicate<MessageReceivedEvent> hasPermision(Permissions p) {
		return e -> (e.getChannel().getModifiedPermissions(e.getAuthor()).contains(p) || Main.config.get(Main.ConfigValue.GLOBALS).contains(e.getAuthor().getStringID())) && e.getChannel().getModifiedPermissions(e.getClient().getOurUser()).contains(p);
	}

	public static Predicate<MessageReceivedEvent> isGlobal() {
		return e -> Main.config.get(Main.ConfigValue.GLOBALS).contains(e.getAuthor().getStringID());
	}

}
