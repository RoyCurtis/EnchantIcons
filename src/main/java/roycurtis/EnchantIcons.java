package roycurtis;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.logging.log4j.Logger;

@Mod(modid = EnchantIcons.MODID, version = EnchantIcons.VERSION)
public class EnchantIcons
{
    public static final String MODID   = "enchanticons";
    public static final String VERSION = "0.0.1";

    public static Logger Logger;
    public static EnchantIconsConfiguration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Logger = event.getModLog();
        config = new EnchantIconsConfiguration(event.getSuggestedConfigurationFile());


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForgeClient.registerItemRenderer( Items.enchanted_book, new EnchantIconsRenderer() );
        Logger.info("Loaded version %s", VERSION);
    }
}
