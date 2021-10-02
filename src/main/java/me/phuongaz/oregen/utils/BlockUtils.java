package me.phuongaz.oregen.utils;

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
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {

    public static List<Block> getBlocks(){
        List<Block> blocks = new ArrayList<>();
        blocks.add(Block.get(Block.COBBLE));
        blocks.add(Block.get(Block.STONE));
        blocks.add(Block.get(Block.COAL_ORE));
        blocks.add(Block.get(Block.IRON_ORE));
        blocks.add(Block.get(Block.LAPIS_ORE));
        blocks.add(Block.get(Block.REDSTONE_ORE));
        blocks.add(Block.get(Block.GOLD_ORE));
        blocks.add(Block.get(Block.DIAMOND_ORE));
        blocks.add(Block.get(Block.EMERALD_ORE));
        //block
        blocks.add(Block.get(Block.COAL_BLOCK));
        blocks.add(Block.get(Block.IRON_BLOCK));
        blocks.add(Block.get(Block.LAPIS_BLOCK));
        blocks.add(Block.get(Block.REDSTONE_BLOCK));
        blocks.add(Block.get(Block.GOLD_BLOCK));
        blocks.add(Block.get(Block.IRON_BLOCK));
        blocks.add(Block.get(Block.GOLD_BLOCK));
        blocks.add(Block.get(Block.EMERALD_BLOCK));
        blocks.add(Block.get(Block.DIAMOND_BLOCK));
        return blocks;
    }

}
