package roycurtis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class EnchantIconsRenderer implements IItemRenderer
{
    static Minecraft       mc         = Minecraft.getMinecraft();
    static StringTranslate translator = new StringTranslate();
    static RenderItem      renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return itemRenderType == ItemRenderType.INVENTORY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... objects)
    {

        ItemEnchantedBook book     = (ItemEnchantedBook) itemStack.getItem();
        NBTTagList        enchants = book.func_92110_g(itemStack);

        GL11.glScalef(0.5f, 0.5f, 0.5f);
        renderItem.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), itemStack, 0, 16, true);
        GL11.glScalef(2f, 2f, 2f);


        RenderHelper.disableStandardItemLighting();
        if (enchants == null || enchants.tagCount() == 0)
            return;

        int id    = enchants.getCompoundTagAt(0).getShort("id");
        int level = enchants.getCompoundTagAt(0).getShort("lvl");

        String ident = EnchantIcons.config.nameMap.get(id);
        String label = enchants.tagCount() > 1
                ? "*"
                : translator.translateKey("enchantment.level." + level);

        if (enchants.tagCount() == 1)
        {
            int color = id > 60 ? 0xFFFFCCFF
                : id > 40 ? 0xFFFFFFCC
                : id > 30 ? 0xFFFFCCCC
                : id > 15 ? 0xFFCCCCFF
                : 0xFFFFCCFF;
            mc.fontRenderer.drawStringWithShadow(ident,0,0,color);
        }

        mc.fontRenderer.drawStringWithShadow(label, 16 - mc.fontRenderer.getStringWidth(label), 16 - mc.fontRenderer.FONT_HEIGHT, 0xFFDDDDDD);
        RenderHelper.enableGUIStandardItemLighting();
    }
}
