package me.will0mane.lib.uranusspigot.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.will0mane.lib.uranusspigot.item.blueprint.BlueprintCItemHeadData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class ItemBuilder {
   private final ItemStack original;

   public ItemBuilder(ItemStack itemStack) {
      this.original = itemStack;
   }

   public ItemBuilder(Material material) {
      this(new ItemStack(material));
   }

   public ItemStack build() {
      return this.original;
   }

   public ItemBuilder setHead(String head) {
      if (this.original.getType() != Material.PLAYER_HEAD) {
         return this;
      } else {
         (new BlueprintCItemHeadData(this, head)).execPin();
         return this;
      }
   }

   public ItemBuilder rename(String name) {
      ItemMeta meta = this.getMeta();
      meta.setDisplayName(name);
      this.original.setItemMeta(meta);
      return this;
   }

   public ItemBuilder setLore(List<String> lore) {
      ItemMeta meta = this.getMeta();
      meta.setLore(lore);
      this.original.setItemMeta(meta);
      return this;
   }

   public ItemBuilder setLore(String... lore) {
      this.setLore(new ArrayList<>(Arrays.asList(lore)));
      return this;
   }

   public ItemBuilder setCustomModelData(int data) {
      ItemMeta meta = this.getMeta();
      meta.setCustomModelData(data);
      this.original.setItemMeta(meta);
      return this;
   }

   public ItemBuilder setUnbreakable(boolean var0) {
      ItemMeta meta = this.getMeta();
      meta.setUnbreakable(var0);
      this.original.setItemMeta(meta);
      return this;
   }

   public ItemMeta getItemMeta() {
      return this.getMeta();
   }

   private ItemMeta getMeta() {
      return this.original.getItemMeta();
   }

   public ItemStack getOriginal() {
      return this.original;
   }
}
