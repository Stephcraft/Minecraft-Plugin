package org.uocsclub.plugin

import org.bukkit.Bukkit
import org.bukkit.ChatColor.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class KoolKommand(plugin: KoolPlugin): CommandExecutor {

    init {
        plugin.getCommand("kool")?.setExecutor(this) ?:
        Bukkit.broadcastMessage("Could not register kool command!")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        val player = sender as? Player ?: run {
            sender.sendMessage("${RED}You must be a player in order to execute this command!")
            return true
        }

        val number = args.firstOrNull()?.toIntOrNull()?.takeIf { args.size == 1 } ?: run {
            sender.sendMessage("${RED}Invalid argument specified!")
            return true
        }

        // give diamonds
        player.inventory.addItem(ItemStack(Material.DIAMOND, number))

        // send message
        player.sendMessage("hello ${player.name}, here are $number diamonds")

        return true
    }
}