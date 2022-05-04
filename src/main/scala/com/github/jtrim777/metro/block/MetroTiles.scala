package com.github.jtrim777.metro.block

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.registries.ForgeRegistries

import com.github.jtrim777.scalacore.utils.ComponentManager.TileHelper

object MetroTiles extends ComponentManager[BlockEntityType[_]](Metro.MODID, ForgeRegistries.BLOCK_ENTITIES){
  val BOOKCASE: BlockEntityType[_] =
    this.tileEntity("bookcase", (pos, state) => new BookcaseTile(pos, state), MetroBlocks.BOOKCASE)
}
