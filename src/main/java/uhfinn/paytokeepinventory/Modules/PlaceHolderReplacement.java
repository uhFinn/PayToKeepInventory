package uhfinn.paytokeepinventory.Modules;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaceHolderReplacement {
    public static String replace(String input, Player p) {
        String current = input;
        current = "a" + current + "a"; // I think this is physically the most disgusting line of code I have ever written lol, I need to learn RegEx
        String output = "";

        String cost = GetConfig.getConfig().getString("Cost");
        if(p.isOp() || p.hasPermission("KeepInv.free")) cost = "0";

        String[][] placeholders = {{"<cost>", cost}};

        for(int i = 0; i < placeholders.length; i++) {
            String[] temp = current.split(placeholders[i][0]);
            List<String> list = new ArrayList<>();
            for (int i2 = 0; i2 < temp.length; i2++) {
                list.add(temp[i2]);
            }
            current = String.join(placeholders[i][1], list);
        }

        for(int i = 0; i < current.length(); i++) {
            if(i != current.length() - 1 && i != 0){
                output = output + current.charAt(i);
            }
        }

        return output;
    }
}