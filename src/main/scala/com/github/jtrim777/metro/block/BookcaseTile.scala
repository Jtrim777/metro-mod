package com.github.jtrim777.metro.block

import com.github.jtrim777.metro.menu.BookcaseMenu
import com.github.jtrim777.scalacore.capabilities.ItemHandler
import com.github.jtrim777.scalacore.inventory.InventoryLayout
import com.github.jtrim777.scalacore.menu.MenuDataProvider
import com.github.jtrim777.scalacore.tiles.TileBase
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.{Component, TranslatableComponent}
import net.minecraft.world.Container
import net.minecraft.world.entity.player.{Inventory, Player}
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity
import net.minecraft.world.level.block.state.BlockState

class BookcaseTile(pos: BlockPos, state: BlockState)
  extends TileBase(18, MetroTiles.BOOKCASE, "block.bookcase")(pos, state) with MenuDataProvider {
  override val slotLayout: InventoryLayout = InventoryLayout()
    .box("contents", 8, 20, 9, 1)
    .build

  override def container: Container = this

  override def inventoryUpdated(): Unit = {}

  override def createMenu(containerID: Int, playerInventory: Inventory): AbstractContainerMenu =
    new BookcaseMenu(containerID, playerInventory, this)
}

object BookcaseTile {

}
