package com.github.jtrim777.metro.gui

import com.github.jtrim777.metro.menu.MetroMenus
import com.github.jtrim777.scalacore.menu.{MenuBase, MenuDataProvider}
import com.github.jtrim777.scalacore.screens.{Screen, ScreenRegistry}
import com.github.jtrim777.scalacore.tiles.TileBase
import net.minecraft.client.gui.screens.MenuScreens

object MetroScreens extends ScreenRegistry {
  override val Screens: List[MetroScreens.ScreenConfig[_ <: MenuDataProvider, _ <: MenuBase[_],
    _ <: TileBase, _ <: Screen[_ <: MenuDataProvider, _ <: MenuBase[_], _ <: TileBase]]] = List.empty

  override def registerScreens(): Unit = {
    MenuScreens.register(MetroMenus.BOOKCASE, BookcaseScreen.apply)
  }
}
