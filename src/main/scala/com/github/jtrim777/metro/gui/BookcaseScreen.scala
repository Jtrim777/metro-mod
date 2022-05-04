package com.github.jtrim777.metro.gui

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.metro.block.BookcaseTile
import com.github.jtrim777.metro.menu.BookcaseMenu
import com.github.jtrim777.scalacore.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class BookcaseScreen(menu: BookcaseMenu, player: Inventory, name: Component)
  extends Screen[BookcaseTile, BookcaseMenu, BookcaseTile](menu, player, name) {
  override val textureLocation: ResourceLocation =
    new ResourceLocation(Metro.MODID, "textures/gui/bookcase.png")

  override protected def layoutComponents(): Unit = {}
}

object BookcaseScreen {
  def apply(menu: BookcaseMenu, player: Inventory, name: Component): BookcaseScreen = new BookcaseScreen(menu, player, name)
}
