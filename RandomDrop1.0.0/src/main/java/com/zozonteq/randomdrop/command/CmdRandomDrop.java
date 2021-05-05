package com.zozonteq.randomdrop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdRandomDrop implements CommandExecutor {
    public static boolean enable = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()){
            if(args[0].equalsIgnoreCase("enable")){
                sender.sendMessage("有効にしました。");
                enable = true;
            }
            else if (args[0].equalsIgnoreCase("disable")){
                enable = false;
                sender.sendMessage("無効にしました。");
            }
            else if (args[0].equalsIgnoreCase("help")){

            }
            else if (args[0].equalsIgnoreCase(null)){
                sender.sendMessage("enable or disable");
            }
        }
        else{
            sender.sendMessage("権限がありません。");
        }
        return false;
    }
}
