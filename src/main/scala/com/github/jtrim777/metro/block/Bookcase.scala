package com.github.jtrim777.metro.block

import com.github.jtrim777.metro.menu.BookcaseMenu
import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Player
import net.minecraft.world.{Container, Containers, InteractionHand, InteractionResult, MenuProvider, SimpleMenuProvider}
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.{BlockBehaviour, BlockState}
import net.minecraft.world.level.block.{Block, EntityBlock, SoundType}
import net.minecraft.world.level.material.Material
import net.minecraft.world.phys.BlockHitResult
import com.github.jtrim777.scalacore.utils.StrExt
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.network.NetworkHooks

class Bookcase extends Block(Bookcase.Properties) with EntityBlock {

  override def newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
    new BookcaseTile(pos, state)

  override def onRemove(old: BlockState, world: Level, pos: BlockPos,
                        newState: BlockState, foo: Boolean): Unit = {

    if (!old.is(newState.getBlock)) {
      val tile = world.getBlockEntity(pos)
      tile match {
        case container: Container =>
          Containers.dropContents(world, pos, container)
          world.updateNeighbourForOutputSignal(pos, this)
        case _ =>
      }
    }

    super.onRemove(old, world, pos, newState, foo)
  }

  override def use(state:BlockState, world: Level, pos: BlockPos, player: Player, hand: InteractionHand, result: BlockHitResult): InteractionResult = {
    if (!world.isClientSide) {
      val tile = world.getBlockEntity(pos).asInstanceOf[BookcaseTile]
      val provider = new SimpleMenuProvider({(id, inv, _) => new BookcaseMenu(id, inv, tile)},
        "block.bookcase".translate)

      NetworkHooks.openGui(player.asInstanceOf[ServerPlayer], provider, pos)
    }

    InteractionResult.SUCCESS
  }
}

object Bookcase {
  val Properties: BlockBehaviour.Properties = BlockBehaviour.Properties
    .of(Material.WOOD)
    .strength(1.5F)
    .sound(SoundType.WOOD)
}
