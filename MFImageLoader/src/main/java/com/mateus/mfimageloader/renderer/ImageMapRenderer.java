package com.mateus.mfimageloader.renderer;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageMapRenderer extends MapRenderer {
    private final BufferedImage image;
    public ImageMapRenderer(BufferedImage image) {
        this.image = image;
    }
    @Override
    public void render(MapView map, MapCanvas canvas, Player player) {
        BufferedImage newImage = resizeImage(image);
        canvas.drawImage(0,0,newImage);
    }
    private BufferedImage resizeImage(BufferedImage image) {
        int width = Math.min(128, image.getWidth());
        int height = Math.min(128, image.getHeight());
        BufferedImage resizedImage = new BufferedImage(width,height,1);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}
