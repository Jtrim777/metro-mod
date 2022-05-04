package com.github.jtrim777.metro.entity

import java.util.UUID
import scala.jdk.OptionConverters.RichOptional

import com.github.jtrim777.metro.town.Memories
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.{EntityDataAccessor, SynchedEntityData}
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraft.world.{Container, DifficultyInstance, SimpleContainer}
import net.minecraft.world.entity.{AgeableMob, EntityType, MobSpawnType, SlotAccess, SpawnGroupData}
import net.minecraft.world.entity.npc.{InventoryCarrier, Npc}
import net.minecraft.world.level.{Level, ServerLevelAccessor}
import com.github.jtrim777.metro.util.SyntaxOps._
import com.mojang.serialization
import net.minecraft.core.GlobalPos
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.sensing.{Sensor, SensorType}
import Citizen.BrainOps

class Citizen(val kind: EntityType[_ <: Citizen], world: Level) extends AgeableMob(kind, world)
  with InventoryCarrier with Npc {

  private val inventory: SimpleContainer = new SimpleContainer(Citizen.InventorySize)

  override def getInventory: Container = inventory

  override def getSlot(slot: Int): SlotAccess = {
    val i = slot - Citizen.SlotOffset

    if (i >= 0 && i < Citizen.InventorySize) {
      SlotAccess.forContainer(inventory, i)
    } else {
      super.getSlot(slot)
    }
  }

  override def defineSynchedData(): Unit = {
    super.defineSynchedData()
    this.entityData.define(Citizen.DataKey, CitizenData(CitizenData.Profession.Unemployed, male = true))
  }

  override def addAdditionalSaveData(tag: CompoundTag): Unit = {
    super.addAdditionalSaveData(tag)

    tag.put("Inventory", this.inventory.createTag)
  }

  override def readAdditionalSaveData(tag: CompoundTag): Unit = {
    super.readAdditionalSaveData(tag)

    this.inventory.fromTag(tag.getList("Inventory", 10))
  }

  override def finalizeSpawn(worldGetter: ServerLevelAccessor, difficulty: DifficultyInstance,
                             spawnCause: MobSpawnType, spawnGroup: SpawnGroupData,
                             spawnData: CompoundTag): SpawnGroupData = {

    if (worldGetter.getLevel.random.nextDouble() < 0.5) {
      modifyCitizenData(_.copy(male = false))
    }

    super.finalizeSpawn(worldGetter, difficulty, spawnCause, spawnGroup, spawnData)
  }

  override def getBreedOffspring(world: ServerLevel, mate: AgeableMob): AgeableMob = {
    new Citizen(this.kind, world)
  }

  override def brainProvider(): Brain.Provider[Citizen] = {
    Brain.provider(Citizen.MemoryTypes.freeze, Citizen.SensorTypes.freeze)
  }

  override def makeBrain(dyno: serialization.Dynamic[_]): Brain[_] = {
    val brain = brainProvider().makeBrain(dyno)
    this.defineBehavior(brain)

    brain
  }

  override def getBrain: Brain[Citizen] = super.getBrain.asInstanceOf[Brain[Citizen]]

  def refreshBrain(world: ServerLevel): Unit = {
    val init: Brain[Citizen] = this.getBrain
    init.stopAll(world, this)

    this.brain = init.copyWithoutBehaviors()
    this.defineBehavior(getBrain)
  }

  private def defineBehavior(brain: Brain[Citizen]): Unit = {

  }

  def getCitizenData: CitizenData = this.entityData.get[CitizenData](Citizen.DataKey)
  def modifyCitizenData(f: CitizenData => CitizenData): Unit = {
    this.entityData.set(Citizen.DataKey, f(getCitizenData))
  }
}

object Citizen {
  val InventorySize: Int = 16
  val SlotOffset: Int = 90

  val DataKey: EntityDataAccessor[CitizenData] =
    SynchedEntityData.defineId(classOf[Citizen], CitizenData.Serializer)

  lazy val MemoryTypes: List[MemoryModuleType[_]] = List(
    MemoryModuleType.HOME, MemoryModuleType.MEETING_POINT, MemoryModuleType.LAST_SLEPT,
    MemoryModuleType.JOB_SITE, Memories.Mayor, Memories.Spouse
  )

  lazy val SensorTypes: List[SensorType[_ <: Sensor[_ >: Citizen]]] = List(
    SensorType.NEAREST_BED, SensorType.NEAREST_ITEMS, SensorType.NEAREST_LIVING_ENTITIES
  )

  implicit class BrainOps(brain: Brain[Citizen]) {
    def home: Option[GlobalPos] = brain.getMemory(MemoryModuleType.HOME).toScala
    def townCenter: Option[GlobalPos] = brain.getMemory(MemoryModuleType.MEETING_POINT).toScala
    def jobSite: Option[GlobalPos] = brain.getMemory(MemoryModuleType.JOB_SITE).toScala

    def getVerifyMayor(world: ServerLevel): Option[Citizen] = {
      val rez = for {
        id <- brain.getMemory(Memories.Mayor).toScala
        entity <- Option(world.getEntity(id))
        citizen <- Option(Entities.CitizenType.tryCast(entity))
      } yield citizen

      if (rez.isEmpty && brain.hasMemoryValue(Memories.Mayor)) {
        brain.eraseMemory(Memories.Mayor)
      }

      rez
    }

    def getVerifySpouse(world: ServerLevel): Option[Citizen] = {
      val rez = for {
        id <- brain.getMemory(Memories.Spouse).toScala
        entity <- Option(world.getEntity(id))
        citizen <- Option(Entities.CitizenType.tryCast(entity))
      } yield citizen

      if (rez.isEmpty && brain.hasMemoryValue(Memories.Spouse)) {
        brain.eraseMemory(Memories.Spouse)
      }

      rez
    }
  }


}
