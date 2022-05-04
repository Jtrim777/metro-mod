package com.github.jtrim777.metro.entity

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.world.entity.EntityType
import net.minecraftforge.registries.ForgeRegistries

object Entities extends ComponentManager[EntityType[_]](Metro.MODID, ForgeRegistries.ENTITIES){
  private val _citizen: Option[EntityType[_]] = ???//entry("citizen", ???)
  lazy val CitizenType: EntityType[Citizen] = _citizen.get.asInstanceOf[EntityType[Citizen]]
}
