package com.mateus.mfimagegui.gui;

import com.mateus.mfbridge.event.ImageSendEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField playerName;
    private JTextField mapName;
    private JButton chooseFileButton;
    private JButton sendButton;

    public MainGUI(String title, Plugin plugin) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        final BufferedImage[] bufferedImage = new BufferedImage[1];

        chooseFileButton.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                if (!isImage(selectedFile)) {
                    JOptionPane.showMessageDialog(null, "This file is not a image");
                } else {
                    try {
                        bufferedImage[0] = ImageIO.read(selectedFile);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        sendButton.addActionListener(e -> {
            if (playerName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "The player name field is empty");
                return;
            }
            if (bufferedImage[0] == null) {
                JOptionPane.showMessageDialog(null, "You did not choose an image");
                return;
            }
            Player player = Bukkit.getPlayer(playerName.getText());
            if (player == null || !player.isOnline()) {
                JOptionPane.showMessageDialog(null, "This player is not online");
                return;
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    String text = mapName.getText().isEmpty() ? null : ChatColor.translateAlternateColorCodes('&', mapName.getText());
                    Bukkit.getPluginManager().callEvent(new ImageSendEvent(player,bufferedImage[0],text));
                }
            }.runTask(plugin);
        });
    }

    private boolean isImage(File file) {
        try {
            return ImageIO.read(file) != null;
        } catch (IOException e) {
            return false;
        }
    }
}
