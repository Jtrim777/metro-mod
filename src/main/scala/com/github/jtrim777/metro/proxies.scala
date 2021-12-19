package com.github.jtrim777.metro

import com.github.jtrim777.scalacore.setup.{ClientProxy, ServerProxy}

object proxies {
  case class MetroClientProxy() extends ClientProxy {
    override def init(): Unit = {

    }
  }

  case class MetroServerProxy() extends ServerProxy {
    override def init(): Unit = {

    }
  }
}
