package com.securitypedant.twiliocraft;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.twilio.sdk.TwilioRestException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventHandlers {
	
	@SubscribeEvent
	public void playerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    String currentTime = sdf.format(cal.getTime());
		
		// Whenever a new player logs in, let's message the admin.
		String playerName = event.player.getDisplayName();
		
		TwilioMessaging twilioClient = new TwilioMessaging();
		try {
		 twilioClient.sendSMS(playerName + CommonProxy.LOGIN_MESSAGE + " at " + currentTime, CommonProxy.ADMIN_NUMBER);
		} catch (TwilioRestException ex) {
			// Should do something here.
		}
	}
	
	@SubscribeEvent
	public void playerLogin(PlayerEvent.PlayerLoggedOutEvent event)
	{
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    String currentTime = sdf.format(cal.getTime());

		// Whenever a new player logs in, let's message the admin.
		String playerName = event.player.getDisplayName();
		
		TwilioMessaging twilioClient = new TwilioMessaging();
		try {
		 twilioClient.sendSMS(playerName + CommonProxy.LOGOUT_MESSAGE + " at " + currentTime, CommonProxy.ADMIN_NUMBER);
		} catch (TwilioRestException ex) {
			// Should do something here.
		}
	}	
	
}
