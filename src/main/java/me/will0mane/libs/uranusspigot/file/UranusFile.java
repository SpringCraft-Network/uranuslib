package me.will0mane.libs.uranusspigot.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;

public class UranusFile {
   private File file;
   private FileConfiguration configuration;

   public UranusFile(File f) throws IOException {
      try {
         if (!f.exists()) {
            f.createNewFile();
         }

         this.file = f;
         this.load();
      } catch (Throwable e) {
         throw e;
      }
   }

   public FileConfiguration getConfiguration() {
      return this.configuration;
   }

   public UranusFile load() {
      this.configuration = YamlConfiguration.loadConfiguration(this.file);
      return this;
   }

   public UranusFile setFile(File file) {
      this.file = file;
      this.load();
      return this;
   }

   public UranusFile copyTo(File destination) {
      FileUtil.copy(this.file, destination);
      return this;
   }

   public UranusFile copyFrom(File source) {
      FileUtil.copy(source, this.file);
      this.load();
      return this;
   }

   public File getFile() {
      return this.file;
   }
}
