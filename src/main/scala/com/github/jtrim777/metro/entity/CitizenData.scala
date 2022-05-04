package com.github.jtrim777.metro.entity

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataSerializer

case class CitizenData(profession: CitizenData.Profession, male: Boolean) {

}

object CitizenData {
  sealed abstract class Profession(val id: Int)
  object Profession {
    def get(id: Int): Profession = ???

    case object Unemployed extends Profession(0)
  }

  val Serializer: EntityDataSerializer[CitizenData] = new EntityDataSerializer[CitizenData] {
    override def write(buffer: FriendlyByteBuf, data: CitizenData): Unit = {
      buffer.writeVarInt(data.profession.id)
      buffer.writeBoolean(data.male)
    }

    override def read(buffer: FriendlyByteBuf): CitizenData = {
      CitizenData(Profession.get(buffer.readVarInt()), buffer.readBoolean())
    }

    override def copy(source: CitizenData): CitizenData = source.copy()
  }
}
