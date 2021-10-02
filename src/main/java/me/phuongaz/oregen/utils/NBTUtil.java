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
import cn.nukkit.item.Item;
import cn.nukkit.level.Position;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.utils.TextFormat;
import me.phuongaz.oregen.block.OregenEntity;


public class NBTUtil {

    public static void createDataBlock(Block block, int level){
        CompoundTag nbt = createNBT(level);
        Runnable runnable = () -> {
            Position pos = block.getLocation();
            BlockEntity blockEntity = pos.getLevel().getBlockEntity(pos);

            if (blockEntity == null) {
                blockEntity = new OregenEntity(pos.getLevel().getChunk(pos.getFloorX() >> 4, pos.getFloorZ() >> 4), new CompoundTag()
                        .putString("id", "OreGen")
                        .putInt("x", (int) pos.x)
                        .putInt("y", (int) pos.y)
                        .putInt("z", (int) pos.z)
                        .putInt("levelgen", level)
                );
            }

            blockEntity.namedTag.putCompound("oreGen", nbt);
            if (blockEntity.chunk != null) {
                blockEntity.chunk.setChanged(true);
            }
        };
        runnable.run();
    }

    public static CompoundTag createNBT(int level){
        return new CompoundTag()
                .putInt("levelgen", level);
    }

    public static Item getOregen(int level, int amount){
        CompoundTag tag = new CompoundTag();
        tag.putInt("levelgen", level);
        Item oregen = Item.get(Item.STONECUTTER, 0, amount);
        oregen.setNamedTag(tag);
        System.out.println("NBT: " + oregen.getNamedTag().getInt("levelgen"));
        oregen.setCustomName(TextFormat.colorize("&l&fMáy tạo quặng cấp (&e" + level +"&f)\n&fThời gian tạo quặng:&e " + (16 - level) + " &fgiây"));
        StringBuilder lore = new StringBuilder("&l&eCó thể tạo ra quặng:");
        for (int i = 0; i <= level; i++){
            lore.append("\n&c-&f ").append(BlockUtils.getBlocks().get(i).getName());
        }
        oregen.setLore(TextFormat.colorize(lore.toString()));
        return oregen;
    }
}
