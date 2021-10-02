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

import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.item.Item;
import me.phuongaz.oregen.utils.NBTUtil;

public class EventListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Block block = event.getBlock();
        Item item = event.getItem();
        if(item.hasCompoundTag() && item.getId() == Item.STONECUTTER){
            int level = item.getNamedTag().getInt("levelgen");
            NBTUtil.createDataBlock(block, level);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        Block block = event.getBlock();
        if(block.getId() == Block.STONECUTTER){
            event.setDrops(new Item[]{});
        }
    }
}
