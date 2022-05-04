package com.github.jtrim777.metro.village

import java.util.Random

import com.github.jtrim777.metro.util.SyntaxOps.I2OMapOps
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraft.world.item.{Item, ItemStack, Items}
import net.minecraftforge.event.village.VillagerTradesEvent

object Trades {

  val MasonTrades: TradeSet = TradeSet()
    .level(1, purchase(Items.LEAD, 1, 4), sell(Items.LEATHER, 10))

  val ByProfession: Map[VillagerProfession, TradeSet] = Map(
//    VillagerProfession.MASON -> MasonTrades
  )

  def registerTrades(events: VillagerTradesEvent): Unit = {
    ByProfession.get(events.getType).foreach { tradeset =>
      val existing = events.getTrades.asScala

      tradeset.trades.foreach { case (level, trades) =>
        val levelTrades = existing(level)
        trades.foreach(levelTrades.add)
      }
    }
  }

  def purchase(item: Item, count: Int, cost: Int): Trade = {
    Trade(
      new ItemStack(Items.EMERALD, cost),
      ItemStack.EMPTY, new ItemStack(item, count),
      12,
      2,
      0.05f
    )
  }

  def sell(item: Item, count: Int): Trade = {
    Trade(
      new ItemStack(item, count),
      ItemStack.EMPTY,
      new ItemStack(Items.EMERALD, 1),
      16,
      2,
      0.05f
    )
  }

  case class Trade(costA: ItemStack, costB: ItemStack, result: ItemStack, uses: Int,
                   xp: Int, multx: Float) extends ItemListing {
    override def getOffer(villager: Entity, randGen: Random): MerchantOffer = {
      new MerchantOffer(costA, costB, uses, xp, multx)
    }
  }

  case class TradeSet(trades: Map[Int, List[Trade]]) {
    def level(l: Int, available: Trade*): TradeSet = {
      val txp = math.pow(2, l).toInt
      val actual = available.toList.map(_.copy(xp = txp))

      TradeSet(trades.updated(l, actual))
    }
  }

  object TradeSet {
    def apply(): TradeSet = TradeSet(Map.empty)
  }


}
