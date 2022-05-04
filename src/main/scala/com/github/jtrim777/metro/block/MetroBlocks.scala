package com.github.jtrim777.metro.block

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries

object MetroBlocks extends ComponentManager[Block](Metro.MODID, ForgeRegistries.BLOCKS){
  private val _bookcase = entry("bookcase", new Bookcase())
  val BOOKCASE: Bookcase = _bookcase.get.asInstanceOf[Bookcase]


}
