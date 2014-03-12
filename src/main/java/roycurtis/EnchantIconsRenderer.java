package roycurtis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.enchantment.Enchantment;
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

    static String[] tableOfElements = new String[64];

    static
    {
        tableOfElements[0] = "Pr";
        tableOfElements[1] = "FPr";
        tableOfElements[2] = "FF";
        tableOfElements[3] = "BPr";
        tableOfElements[4] = "PPr";
        tableOfElements[5] = "Rsp";
        tableOfElements[6] = "AAf";
        tableOfElements[7] = "Th";

        tableOfElements[16] = "Sh";
        tableOfElements[17] = "Sm";
        tableOfElements[18] = "BoA";
        tableOfElements[19] = "Kn";
        tableOfElements[20] = "FA";
        tableOfElements[21] = "Lt";

        tableOfElements[32] = "Eff";
        tableOfElements[33] = "ST";
        tableOfElements[34] = "Ub";
        tableOfElements[35] = "Ft";

        tableOfElements[48] = "Po";
        tableOfElements[49] = "Pu";
        tableOfElements[50] = "Fl";
        tableOfElements[51] = "Inf";

        tableOfElements[61] = "Sea";
        tableOfElements[62] = "Lre";
    }

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

        if (enchants == null || enchants.tagCount() == 0)
            return;

        int id    = enchants.getCompoundTagAt(0).getShort("id");
        int level = enchants.getCompoundTagAt(0).getShort("lvl");

        Enchantment enchant = Enchantment.enchantmentsList[id];

        String ident = tableOfElements[id];
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

            mc.fontRenderer.drawString(ident, 0, 0, color, true);
        }

        mc.fontRenderer.drawString(label, 16 - mc.fontRenderer.getStringWidth(label) , 16 - mc.fontRenderer.FONT_HEIGHT, 0xFFDDDDDD, true);
    }
}
