package me.phuongaz.oregen.form;

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
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import me.onebone.economyapi.EconomyAPI;
import me.phuongaz.oregen.utils.BlockUtils;
import me.phuongaz.oregen.utils.NBTUtil;
import ru.contentforge.formconstructor.form.CustomForm;
import ru.contentforge.formconstructor.form.ModalForm;
import ru.contentforge.formconstructor.form.SimpleForm;
import ru.contentforge.formconstructor.form.element.Input;

public class ShopOreGen {

    public static void sendStartForm(Player player){
        SimpleForm form = new SimpleForm(TextFormat.colorize("&l&0OREGEN"));
        form.addButton(TextFormat.colorize("&l&0Mua "), (p, button) -> {
            CustomForm shopform = new CustomForm(TextFormat.colorize("&l&aSHOP OREGEN"));
            shopform.addElement(TextFormat.colorize("&l&fMáy tạo quặng cấp &e1"));
            shopform.addElement(TextFormat.colorize("&l&eMô tả:"));
            shopform.addElement(TextFormat.colorize("&l&c-&f Tạo ra các khoáng sản"));
            shopform.addElement(TextFormat.colorize("&l&c-&f Cấp càng cao càng nhiều loại quặng, thời gian tạo ra quặng giảm!"));
            shopform.addElement("count", new Input(TextFormat.colorize("&l&fNhập số lượng:&e 80000 &fxu/&e1")));
            shopform.setHandler((pp, response) -> {
                String count = response.getInput("count").getValue();
                try{
                    int amount = Integer.parseInt(count);
                    if(EconomyAPI.getInstance().myMoney(pp) >= amount * 80000){
                        Item item = NBTUtil.getOregen(1, amount);
                        if(pp.getInventory().canAddItem(item)){
                            pp.getInventory().addItem(item);
                            EconomyAPI.getInstance().reduceMoney(pp, amount * 80000);
                            sendNote(pp, "&l&fMua thành công &e" + amount + "&f máy tạo quặng cấp &e1&f với giá&e " + amount * 80000 + " &fxu");
                        }else sendNote(pp, "&l&fKhông còn đủ chỗ chứa!!");
                    }else sendNote(pp, "&l&fKhông đủ xu cần &e" + amount * 80000);
                }catch (NumberFormatException e){
                    sendNote(pp, "&l&cSai");
                }
            });
            shopform.send(p);
        });
        form.addButton(TextFormat.colorize("&l&0Nâng cấp máy tạo quặng"), (p, button) -> {
            ModalForm uform = new ModalForm(TextFormat.colorize("&l"));
            Item item = player.getInventory().getItemInHand();
            if(item.hasCompoundTag() && item.getId() == Item.STONECUTTER){
                int level = item.getNamedTag().getInt("levelgen");
                if(level == 15){
                    sendNote(p, "&l&aĐã đạt cấp tối đa");
                    return;
                }
                int price = 50000 * level * item.getCount();
                String content = "&l&fBạn cần &e" + price + " &fxu để nâng cấp máy tạo quặng lên cấp &e" + level + "&f số lượng&e" + item.getCount();
                content += "\n&l&c-&f Có thể tạo ra:&e " + BlockUtils.getBlocks().get(level + 1).getName();
                content += "\n&l&c-&f Thời gian tạo ra quặng: &e" + (16 - level + 1);
                uform.setContent(TextFormat.colorize(content));
                uform.setPositiveButton(TextFormat.colorize("&l&2Chấp nhận nâng cấp"));
                uform.setNegativeButton(TextFormat.colorize("&l&2Không nâng cấp!"));
                uform.setHandler((pp, response) -> {
                    if(EconomyAPI.getInstance().myMoney(pp) >= price){
                        sendNote(pp, "&l&fNâng cấp thành công máy tạo quặng lên cấp &e" + (level+1));
                        Item oregen = NBTUtil.getOregen(level+1, item.getCount());
                        player.getInventory().setItemInHand(oregen);
                        EconomyAPI.getInstance().reduceMoney(pp, price);
                    }
                });
                uform.send(p);
            }else{
                String content = "&l&fBạn cần cầm máy tạo quặng lên tay để nâng";
                sendNote(p, content);
            }
        });
        form.send(player);
    }

    private static void sendNote(Player player, String note){
        CustomForm form = new CustomForm("NOTE");
        form.addElement(TextFormat.colorize(note));
        form.setNoneHandler(ShopOreGen::sendStartForm);
        form.setHandler((p, res) -> sendStartForm(p));
        form.send(player);
    }
}
