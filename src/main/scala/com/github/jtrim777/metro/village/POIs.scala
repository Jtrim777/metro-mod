package com.github.jtrim777.metro.village

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.metro.util.SyntaxOps._
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries

object POIs extends ComponentManager[PoiType](Metro.MODID, ForgeRegistries.POI_TYPES) {

  def workBlock(name: String, block: Block): PoiType = {
    new PoiType(name, block.allStates, 1, 1)
  }
}
