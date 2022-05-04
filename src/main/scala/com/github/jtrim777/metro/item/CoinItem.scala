package com.github.jtrim777.metro.item

import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.{CreativeModeTab, Item}

class CoinItem(val value: Int) extends Item(CoinItem.Props) {

}

object CoinItem {
  val Props: Properties = (new Properties).tab(CreativeModeTab.TAB_MISC).stacksTo(16)
}
