package com.github.jtrim777.metro

import com.github.jtrim777.scalacore.ModHeart
import com.github.jtrim777.scalacore.setup.{ClientProxy, ServerProxy}
import com.github.jtrim777.scalacore.utils.ContentManager
import net.minecraftforge.fml.event.lifecycle.{FMLCommonSetupEvent, FMLDedicatedServerSetupEvent}

class Metro extends ModHeart {
  override def getModID: String = Metro.MODID

  override def getClientProxy: ClientProxy = proxies.MetroClientProxy()

  override def getServerProxy: ServerProxy = proxies.MetroServerProxy()

  override def getContent: ContentManager = MetroContent

  override def commonSetup(event: FMLCommonSetupEvent): Unit = {}
  override def serverSetup(event: FMLDedicatedServerSetupEvent): Unit = {}
}

object Metro {
  final val MODID: String = "metro"
  final val Version: String = "0.1.0"
}
