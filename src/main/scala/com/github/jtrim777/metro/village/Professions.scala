package com.github.jtrim777.metro.village

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.metro.util.SyntaxOps._
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.entity.ai.village.poi.PoiType
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries

object Professions extends ComponentManager[VillagerProfession](Metro.MODID, ForgeRegistries.PROFESSIONS) {



  def profession(name: String, poi: PoiType, workSound: SoundEvent,
                 shareables: Set[Item] = Set.empty, interestingBlocks: Set[Block] = Set.empty): VillagerProfession = {
    new VillagerProfession(name, poi, shareables.asGoogle, interestingBlocks.asGoogle, workSound)
  }
}
