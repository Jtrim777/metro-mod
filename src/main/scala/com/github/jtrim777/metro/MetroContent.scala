package com.github.jtrim777.metro

import com.github.jtrim777.scalacore.gen.GenerationManager
import com.github.jtrim777.scalacore.screens.ScreenRegistry
import com.github.jtrim777.scalacore.utils.{ContentManager, ContentRegistrar}

object MetroContent extends ContentManager(Metro.MODID) {
  override def componentManagers: List[ContentRegistrar[_]] = List.empty

  override def screenRegistry: Option[ScreenRegistry] = None

  override def worldGenContent: Option[GenerationManager] = None
}
