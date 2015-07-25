package roycurtis;

import net.minecraftforge.common.config.Configuration;
import sun.misc.Regexp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KJ4IPS on 5/19/2014.
 */


public class EnchantIconsConfiguration extends Configuration {

    public Map<Integer,String> nameMap;

    private final static String[] defaultNames = new String[] {
            "0:Pr",     //Protection
            "1:FPr",    //Fire Prot.
            "2:FF",     //Feather Fall
            "3:Bpr",    //Blast Prot.
            "4:Ppr",    //Proj Prot.
            "5:Rsp",    //Respiration
            "6:AAf",    //Aqua Affinity
            "7:Th",     //Thorns

            "16:Sh",    //Sharpness
            "17:Sm",    //Smite
            "18:BoA",   //Bane of Anth.
            "19:Kn",    //Knock Back
            "20:FA",    //Fire Affinity
            "21:Lt",    //Looting

            "32:Eff",   //Efficiency
            "33:St",    //Silk Touch
            "34:Ub",    //Unbreaking
            "35:Ft",    //Fortune

            "48:Po",    //Power
            "49:Pu",    //Punch
            "50:Fl",    //Flame
            "51:Inf",   //Infinity

            "61:Sea",   //Luck of the Sea
            "62:Lre"    //Lure
    };

    public EnchantIconsConfiguration(File file) {
        super(file);
        nameMap = new HashMap<Integer,String>();
        this.load();

        String[] enchString = this.get("General","enchNames",defaultNames).getStringList();

        String[] fooArray;
        for(String s : enchString){
            fooArray = s.split(":");
            Integer id = Integer.parseInt(fooArray[0]);
            if(id == null) continue;
            nameMap.put(id,fooArray[1]);
        }
        this.save();
    }

}
