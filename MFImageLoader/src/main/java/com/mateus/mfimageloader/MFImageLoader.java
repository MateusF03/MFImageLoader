package com.mateus.mfimageloader;

import com.mateus.mfimageloader.listener.ImageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MFImageLoader extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ImageListener(), this);
    }
}
