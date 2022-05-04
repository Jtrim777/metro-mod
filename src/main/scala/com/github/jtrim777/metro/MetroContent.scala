package com.github.jtrim777.metro

import com.github.jtrim777.metro.block.{MetroBlocks, MetroTiles}
import com.github.jtrim777.metro.gui.MetroScreens
import com.github.jtrim777.metro.item.MetroItems
import com.github.jtrim777.scalacore.gen.GenerationManager
import com.github.jtrim777.scalacore.screens.ScreenRegistry
import com.github.jtrim777.scalacore.utils.{ContentManager, ContentRegistrar}

object MetroContent extends ContentManager(Metro.MODID) {
  override def componentManagers: List[ContentRegistrar[_]] = List(
    MetroBlocks,
    MetroTiles,
    MetroItems
  )

  override def screenRegistry: Option[ScreenRegistry] = Some(MetroScreens)

  override def worldGenContent: Option[GenerationManager] = None
}
