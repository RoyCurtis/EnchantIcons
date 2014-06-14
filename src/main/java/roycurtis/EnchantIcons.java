package roycurtis;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Mod(modid = EnchantIcons.MODID, version = EnchantIcons.VERSION)
public class EnchantIcons
{
    public static final String MODID   = "enchanticons";
    public static final String VERSION = "0.0.1";

    public static Logger logger;
    public static File   BaseDir;
    public static EnchantIconsConfiguration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger  = event.getModLog();
        BaseDir = new File(event.getModConfigurationDirectory(), MODID);
        config = new EnchantIconsConfiguration(event.getSuggestedConfigurationFile());


        if ( !BaseDir.exists() )
            BaseDir.mkdir();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForgeClient.registerItemRenderer( Item.enchantedBook.itemID, new EnchantIconsRenderer() );
        logger.log(Level.INFO, "Loaded version %s", VERSION);
    }
}
