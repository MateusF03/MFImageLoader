package com.mateus.mfimageloader.listener;

import com.mateus.mfbridge.event.ImageSendEvent;
import com.mateus.mfimageloader.renderer.ImageMapRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;


public class ImageListener implements Listener {

    @EventHandler
    public void onImage(ImageSendEvent event) {
        Player player = event.getPlayer();
        ItemStack map = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        assert mapMeta != null;
        if (event.getName() != null) mapMeta.setDisplayName(event.getName());
        MapView mapView = Bukkit.createMap(event.getPlayer().getWorld());
        mapView.getRenderers().clear();
        mapView.addRenderer(new ImageMapRenderer(event.getImage()));
        mapMeta.setMapView(mapView);
        map.setItemMeta(mapMeta);
        player.getInventory().addItem(map);
    }
}
