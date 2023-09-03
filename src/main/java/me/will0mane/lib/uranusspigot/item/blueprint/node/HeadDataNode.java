package me.will0mane.lib.uranusspigot.item.blueprint.node;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import me.will0mane.lib.uranus.blueprint.node.BlueprintNode;
import me.will0mane.lib.uranusspigot.item.ItemBuilder;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadDataNode extends BlueprintNode {
   private final ItemBuilder item;
   private final String data;

   public HeadDataNode(ItemBuilder item, String data) {
      this.item = item;
      this.data = data;
   }

   public List<?> inputVars() {
      return Collections.emptyList();
   }

   public List<?> outputVars() {
      return Collections.emptyList();
   }

   public List<ItemBuilder> executePin(Object... objects) {
      SkullMeta itemMeta = (SkullMeta)this.item.getOriginal().getItemMeta();
      if (itemMeta != null) {
         GameProfile profile = new GameProfile(UUID.randomUUID(), "");
         profile.getProperties().put("textures", new Property("textures", this.data));

         try {
            Field profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
            profileField.setAccessible(false);
         } catch (IllegalAccessException | NoSuchFieldException | SecurityException | IllegalArgumentException var6) {
            var6.printStackTrace();
         }

         this.item.getOriginal().setItemMeta(itemMeta);
      }
      return Collections.singletonList(this.item);
   }
}
