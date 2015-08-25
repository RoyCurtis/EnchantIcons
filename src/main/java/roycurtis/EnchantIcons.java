package roycurtis;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
    modid   = EnchantIcons.MODID,
    name    = EnchantIcons.MODID,
    version = EnchantIcons.VERSION,

    acceptableRemoteVersions = "*",
    acceptableSaveVersions   = ""
)
public class EnchantIcons
{
    /** Frozen at 0.0.1 to prevent misleading world save error */
    public static final String VERSION = "0.0.1";
    public static final String MODID   = "enchanticons";

    public static Logger LOG = LogManager.getFormatterLogger(EnchantIcons.MODID);
    public static EnchantIconsConfiguration config;

    @EventHandler
    @SideOnly(Side.SERVER)
    public void serverPreInit(FMLPreInitializationEvent event)
    {
        LOG.error("This mod is intended only for use on clients");
        LOG.error("Please consider removing this mod from your server installation");
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void clientPreInit(FMLPreInitializationEvent event)
    {
        config = new EnchantIconsConfiguration(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void clientInit(FMLInitializationEvent event)
    {
        MinecraftForgeClient.registerItemRenderer( Items.enchanted_book, new EnchantIconsRenderer() );
        LOG.info("Loaded version %s", VERSION);
    }
}
