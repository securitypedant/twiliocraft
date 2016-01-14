package com.securitypedant.twiliocraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class CommonProxy {
	
	// Global mod constants that will be populated from the config file in the preInit.
	public static String TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN, TWILIO_NUMBER, ADMIN_NUMBER, LOGIN_MESSAGE, LOGOUT_MESSAGE;

	// Register on the events bus so that we can capture events for us to interact with.
	EventHandlers events = new EventHandlers();
	
	public void preInit(FMLPreInitializationEvent e) {
		
		// C O N F I G  F I L E  S E T U P
		// ------------------------------------------------------------------------------------------------------------
        // Setup the config file that will be in .minecraft/config/ and it will be named secondFactor.cfg
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
    	FMLCommonHandler.instance().bus().register(events);
    	MinecraftForge.EVENT_BUS.register(events);
        
        // Load the configuration from its file
        config.load();
        
        // Twilio API credentials
        Property configTwilioAccountSid = config.get("Service", "AccountSid", "");
        configTwilioAccountSid.comment = "REQUIRED: Twilio Account Sid";
        TWILIO_ACCOUNT_SID = configTwilioAccountSid.getString();
        
        Property configTwilioAuthToken = config.get("Service", "AuthToken", "");
        configTwilioAuthToken.comment = "REQUIRED: Twilio Auth Token";
        TWILIO_AUTH_TOKEN = configTwilioAuthToken.getString();

        Property configTwilioNumber = config.get("Service", "TwilioNumber", "");
        configTwilioNumber.comment = "REQUIRED: Twilio number";
        TWILIO_NUMBER = configTwilioNumber.getString();

        Property configAdminNumber = config.get("Service", "AdminNumber", "");
        configAdminNumber.comment = "REQUIRED: Admin number that we want to send events to";
        ADMIN_NUMBER = configAdminNumber.getString();
        
        Property configLoginMessage = config.get("Service", "LoginMessage", " has logged into the server");
        configLoginMessage.comment = "REQUIRED: What is the message that comes after the player name during login?";
        LOGIN_MESSAGE = configLoginMessage.getString();
        
        Property configLogoutMessage = config.get("Service", "LogoutMessage", " has logged out of the server");
        configLogoutMessage.comment = "REQUIRED: What is the message that comes after the player name at logout?";
        LOGOUT_MESSAGE = configLogoutMessage.getString();
        
        // Save the configuration to its file
        config.save();
		
	}

	public void init(FMLInitializationEvent e) {
	}

	public void postInit(FMLPostInitializationEvent e) {
	}
}
