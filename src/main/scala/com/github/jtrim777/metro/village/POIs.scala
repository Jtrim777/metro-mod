package com.github.jtrim777.metro.village

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.metro.util.SyntaxOps._
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.block.Block
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.registries.ForgeRegistries

object POIs extends ComponentManager[PointOfInterestType](Metro.MODID, ForgeRegistries.POI_TYPES) {

  def workBlock(name: String, block: Block): PointOfInterestType = {
    new PointOfInterestType(name, block.allStates, 1, 1)
  }
}
