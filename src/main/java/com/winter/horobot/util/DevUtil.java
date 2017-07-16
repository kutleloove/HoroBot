package com.winter.horobot.util;

import com.winter.horobot.Main;
import com.winter.horobot.exceptions.ErrorHandler;
import sx.blah.discord.util.Image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DevUtil {

	public static void changeAvatar(String uri) {
		try {
			URL url = new URL(uri);
			Main.getClient().changeAvatar(Image.forUrl("png", uri));
		} catch (MalformedURLException e) {
			ErrorHandler.log(e, "Tried changing avatar to " + uri);
		}
	}

}