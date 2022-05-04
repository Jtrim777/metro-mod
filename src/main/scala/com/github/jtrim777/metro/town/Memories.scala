package com.github.jtrim777.metro.town

import java.util.{Optional, UUID}

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.core.SerializableUUID
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraftforge.registries.ForgeRegistries
import com.github.jtrim777.metro.util.SyntaxOps._

object Memories extends ComponentManager[MemoryModuleType[_]](Metro.MODID, ForgeRegistries.MEMORY_MODULE_TYPES) {

  private val _mayor: Option[MemoryModuleType[_]] =
    entry("mayor", new MemoryModuleType[UUID](Optional.of(SerializableUUID.CODEC))).asOpt
  lazy val Mayor: MemoryModuleType[UUID] = _mayor.get.asInstanceOf[MemoryModuleType[UUID]]

  private val _spouse: Option[MemoryModuleType[_]] =
    entry("spouse", new MemoryModuleType[UUID](Optional.of(SerializableUUID.CODEC))).asOpt
  lazy val Spouse: MemoryModuleType[UUID] = _spouse.get.asInstanceOf[MemoryModuleType[UUID]]


}
