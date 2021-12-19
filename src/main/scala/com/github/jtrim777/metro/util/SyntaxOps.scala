package com.github.jtrim777.metro.util

import java.util.stream.Collectors
import scala.jdk.CollectionConverters.CollectionHasAsScala

import com.google.common.collect.ImmutableSet
import it.unimi.dsi.fastutil.ints.Int2ObjectMap
import net.minecraft.block.{Block, BlockState}

object SyntaxOps {
  implicit class BlockOps(block: Block) {
    def allStates: ImmutableSet[BlockState] =
      ImmutableSet.copyOf(block.getStateDefinition.getPossibleStates)
  }

  implicit class SetOps[T](set: Set[T]) {
    def asGoogle: ImmutableSet[T] = ImmutableSet.copyOf(set.toArray)
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
}
