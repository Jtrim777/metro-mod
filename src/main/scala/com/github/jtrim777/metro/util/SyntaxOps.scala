package com.github.jtrim777.metro.util

import java.util.stream.Collectors
import scala.jdk.CollectionConverters.{CollectionHasAsScala, IterableHasAsJava}
import scala.jdk.OptionConverters.RichOptional
import scala.reflect.ClassTag

import com.google.common.collect.{ImmutableList, ImmutableSet}
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.registries.RegistryObject

object SyntaxOps {
  implicit class BlockOps(block: Block) {
    def allStates: ImmutableSet[BlockState] =
      ImmutableSet.copyOf(block.getStateDefinition.getPossibleStates)
  }

  implicit class SetOps[T : ClassTag](set: Set[T]) {
    def asGoogle: ImmutableSet[T] = ImmutableSet.copyOf(set.toList.asJava)
  }

  implicit class I2OMapOps[T](i2o: Int2ObjectMap[T]) {
    def asScala: Map[Int, T] = i2o
      .int2ObjectEntrySet()
      .stream()
      .map[(Int, T)](e => (e.getIntKey, e.getValue))
      .collect(Collectors.toList[(Int, T)])
      .asScala
      .toMap
  }

  implicit class ListOps[T](list: List[T]) {
    def freeze: ImmutableList[T] = ImmutableList.builder().addAll(list.asJava).build()
  }

  implicit class ROOps[T <: net.minecraftforge.registries.IForgeRegistryEntry[T]](robj: RegistryObject[T]) {
    def asOpt: Option[T] = robj.map(a => a).toScala
  }
}
