package com.github.jtrim777.metro.menu

import com.github.jtrim777.metro.Metro
import com.github.jtrim777.scalacore.utils.ComponentManager
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.MenuType
import net.minecraftforge.registries.ForgeRegistries

object MetroMenus extends ComponentManager[MenuType[_]](Metro.MODID, ForgeRegistries.CONTAINERS){
  private val _bookcase = entry("bookcase",
    new MenuType[BookcaseMenu]((i:Int, v:Inventory) => new BookcaseMenu(i, v, null)))
  val BOOKCASE: MenuType[BookcaseMenu] = _bookcase.get.asInstanceOf[MenuType[BookcaseMenu]]
}
