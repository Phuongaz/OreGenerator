package me.phuongaz.oregen;

/*
MIT License

Copyright (c) 2021 Phuongaz

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import me.phuongaz.oregen.form.ShopOreGen;
import me.phuongaz.oregen.utils.NBTUtil;

public class OregenCommand extends Command {

    public OregenCommand(){
        super("oregenerator", "Ore generator!", "/oregenerator", new String[]{"ogr", "og"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args){
            if(args.length == 3 && sender.isOp()){
                Player player = Server.getInstance().getPlayerExact(args[0]);
                int amount = Integer.parseInt(args[1]);
                int level = Integer.parseInt(args[2]);
                Item ore = NBTUtil.getOregen(level, amount);
                player.getInventory().addItem(ore);
                sender.sendMessage("Give " + player.getName() + " x" + amount + " Ore generator level " + level);
                return true;
            }
            ShopOreGen.sendStartForm((Player) sender);
        return true;
    }

}
