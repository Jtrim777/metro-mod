package com.github.jtrim777.metro.village

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import com.google.common.collect.ImmutableSet
import net.minecraft.block.Block
import net.minecraft.entity.merchant.villager.VillagerProfession
import net.minecraft.item.Item
import net.minecraft.util.SoundEvent
import net.minecraft.village.PointOfInterestType
import net.minecraftforge.registries.ForgeRegistries
import com.github.jtrim777.metro.util.SyntaxOps._

object Professions extends ComponentManager[VillagerProfession](Metro.MODID, ForgeRegistries.PROFESSIONS) {



  def profession(name: String, poi: PointOfInterestType, workSound: SoundEvent,
                 shareables: Set[Item] = Set.empty, interestingBlocks: Set[Block] = Set.empty): VillagerProfession = {
    new VillagerProfession(name, poi, shareables.asGoogle, interestingBlocks.asGoogle, workSound)
  }
}
