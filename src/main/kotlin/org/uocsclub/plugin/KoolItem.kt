package org.uocsclub.plugin

import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.entity.Slime
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector

class KoolItem(plugin: KoolPlugin): Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {

        // player right clicked a block with a diamond
        val item = event.item ?: return
        if(item.type != Material.DIAMOND) return
        val clickedBlock = event.clickedBlock ?: return
        if(event.action != Action.RIGHT_CLICK_BLOCK) return

        // clicked block becomes diamond block
        clickedBlock.type = Material.DIAMOND_BLOCK

        // particles
        clickedBlock.world.spawnParticle(
            Particle.BLOCK_DUST,
            clickedBlock.location + Vector(0.5, 0.5, 0.5),
            500,
            0.5, 0.5, 0.5,
            clickedBlock.blockData
        )

        // sound
        val num = (0..7).random()
        val sound = Sound.valueOf("ITEM_GOAT_HORN_SOUND_$num")
        clickedBlock.world.playSound(
            clickedBlock.location,
            sound,
            1f, 1f
        )
    }

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {

        // player damaged entity with slime ball
        val player = event.damager as? Player ?: return
        if(player.inventory.itemInMainHand.type != Material.SLIME_BALL) return

        // replace by slime entity
        event.entity.world.spawn(event.entity.location, Slime::class.java) { slime ->
            slime.size = 1
        }

        // remove previous entity
        event.entity.remove()
    }
}

private operator fun Location.plus(vector: Vector) = clone().add(vector)
