package org.uocsclub.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class KoolPlugin: JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        Bukkit.broadcastMessage("Hello world!")
        logger.info("Hello world!")

        // features
        KoolItem(this)
        KoolKommand(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}