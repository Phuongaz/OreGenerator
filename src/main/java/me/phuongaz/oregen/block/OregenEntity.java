package me.phuongaz.oregen.block;
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
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import me.phuongaz.oregen.utils.BlockUtils;
import me.phuongaz.oregen.utils.NBTUtil;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OregenEntity extends BlockEntity {

    private int currentTick = 10;
    private final int levelgen;

    private final List<Block> blocks;

    public OregenEntity(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        blocks = BlockUtils.getBlocks();
        this.scheduleUpdate();
        levelgen = nbt.getInt("levelgen");
    }

    @Override
    public String getName(){
        return "OreGenerator";
    }

    public void checkBreak(){
        Item drop = NBTUtil.getOregen(getLevelGen(), 1);
        this.getLevel().dropItem(this, drop);
        this.closed = false;
        this.close();
    }

    @Override
    public boolean isBlockEntityValid() {
        return false;
    }

    @Override
    public boolean onUpdate(){
        if(this.currentTick <= 0){
            Block upblock = this.getLevel().getBlock(this.add(0D, 1D));
            if(upblock.getId() == 0){
                int rd = ThreadLocalRandom.current().nextInt(levelgen);
                Block block = blocks.get(rd);
                this.getLevel().setBlock(upblock, block);
                this.currentTick = (16 - levelgen) * 20;
            }
        }else{
            this.currentTick -= 1;
        }

        if(this.getLevel().getBlock(this).getId() == 0){
            checkBreak();
        }
        return true;
    }

    public int getLevelGen(){
        return levelgen;
    }

}
