package com.mateus.mfimagegui;

import com.mateus.mfimagegui.gui.MainGUI;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;

public class MFImageGUI extends JavaPlugin {
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        JFrame jFrame = new MainGUI("Image loader", this);
        jFrame.setVisible(true);
    }
}
