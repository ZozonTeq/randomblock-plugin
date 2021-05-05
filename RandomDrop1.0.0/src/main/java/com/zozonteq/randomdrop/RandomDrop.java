package com.zozonteq.randomdrop;

import com.zozonteq.randomdrop.command.CmdRandomDrop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.Random;

public final class RandomDrop extends JavaPlugin implements Listener {
    public static JavaPlugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        plugin = this;

        getCommand("randomdrop").setExecutor(new CmdRandomDrop());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        super.onDisable();
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        if(CmdRandomDrop.enable){
            World w = e.getPlayer().getWorld();
            Location loc = e.getBlock().getLocation();
            Material material =e.getBlock().getType();
            Player player = e.getPlayer();
            Collection<ItemStack> dropItem = e.getBlock().getDrops();
            Material[] materials = Material.values();
            Material materialBlock = Material.STONE_AXE;
            Random random = new Random();
            int num = 0;
            while(!materialBlock.isBlock()){
                num = random.nextInt(materials.length);
                materialBlock = materials[num];
            }
            e.setCancelled(true);
            loc.getBlock().setType(materialBlock);
            e.getPlayer().sendMessage(materialBlock+"だ!");
            for(ItemStack s :dropItem){
                w.dropItem(loc.add(0,1,0),s);
            }
        }
    }

    public static JavaPlugin getPlugin(){
        return plugin;
    }
}
