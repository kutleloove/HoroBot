package com.winter.horobot.util;

import com.winter.horobot.data.locale.Localisation;
import com.winter.horobot.exceptions.ErrorHandler;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MessageUtil {

	public static String[] argsArray(IMessage m) {
		return m.getContent().substring(GuildUtil.getPrefix(m.getGuild()).length()).split("\\s+");
	}

	public static String args(IMessage m) {
		return Arrays.stream(argsArray(m)).collect(Collectors.joining(" "));
	}

	/**
	 * Send a message in a channel, params are what the %s in the message will be replaced with
	 * @param channel The channel to send the message in
	 * @param messageKey The localisation key of the message
	 * @param params The replacements for %s in the message
	 */
	public static void sendMessage(IChannel channel, String messageKey, Object... params) {
		RequestBuffer.request(() -> channel.sendMessage(Localisation.getMessage(channel.getGuild(), messageKey, params)));
	}

	public static void sendImageEmbed(IChannel channel, IUser requested, URI uri) {
		RequestBuffer.request(() -> {
			EmbedBuilder b = new EmbedBuilder();
			b.withAuthorIcon(ImageUtil.getAvatar(requested));
			b.withAuthorName("Requested by " + requested.getDisplayName(channel.getGuild()));
			try {
				BufferedImage i = ImageUtil.imageFromURL(uri.toURL());
				b.withColor(ImageUtil.averageColor(i));
				b.withImage(uri.toString());
				channel.sendMessage(b.build());
			} catch (IOException e) {
				ErrorHandler.log(e, "Guild: " + channel.getGuild());
				channel.sendMessage("Something went wrong. An error has been sent to the developers. If the problem persists, join the HoroBot Discord and ask there.");
			}
		});
	}

}
