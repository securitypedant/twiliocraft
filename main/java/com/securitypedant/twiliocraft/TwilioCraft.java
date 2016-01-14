package com.securitypedant.twiliocraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = TwilioCraft.MODID, version = TwilioCraft.MODVER, acceptableRemoteVersions = "*")
public class TwilioCraft {
	
	// Set the ID of the mod (Should be lower case).
    public static final String MODID = "twiliocraft";
    // Set the "Name" of the mod.
    public static final String MODNAME = "Collection of Twilio based mods for Minecraft";
    // Set the version of the mod.
    public static final String MODVER = "1.1.0";
    
    @Instance(value = TwilioCraft.MODID) // Create an instance.
    public static TwilioCraft instance;

    @SidedProxy(clientSide="com.securitypedant.twiliocraft.ClientProxy", serverSide="com.securitypedant.twiliocraft.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
    	// Call proxy, no other code needs to go in here.
        proxy.preInit(e);
    }
        
    @EventHandler
    public void init(FMLInitializationEvent e)
    {
    	// Call proxy, no other code needs to go in here.
    	proxy.init(e);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	// Call proxy, no other code needs to go in here.
    	proxy.postInit(e);
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	// Actions at server load time.
    }

}
