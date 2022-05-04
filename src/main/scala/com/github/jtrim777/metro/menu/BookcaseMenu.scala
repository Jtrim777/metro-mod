package com.github.jtrim777.metro.menu

import com.github.jtrim777.metro.block.{BookcaseTile, MetroBlocks}
import com.github.jtrim777.scalacore.capabilities.ItemHandler
import com.github.jtrim777.scalacore.menu.MenuBase
import net.minecraft.world.entity.player.Inventory

class BookcaseMenu(cid: Int, pinv: Inventory, data: BookcaseTile)
  extends MenuBase[BookcaseTile](MetroMenus.BOOKCASE, MetroBlocks.BOOKCASE)(cid, pinv, data) {

  override protected def layoutSlots(handler: ItemHandler): Unit = {
    data.slotLayout.slots.foreach { ur =>
      val slot = ur(handler)

      this.addSlot(slot)
    }
  }
}
