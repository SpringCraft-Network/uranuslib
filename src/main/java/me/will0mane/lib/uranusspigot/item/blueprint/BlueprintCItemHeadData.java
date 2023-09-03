/* Decompiler 39ms, total 150ms, lines 33 */
package me.will0mane.lib.uranusspigot.item.blueprint;

import java.util.Collections;
import java.util.List;

import me.will0mane.lib.uranus.blueprint.Blueprint;
import me.will0mane.lib.uranus.blueprint.node.BlueprintNode;
import me.will0mane.lib.uranusspigot.item.ItemBuilder;
import me.will0mane.lib.uranusspigot.item.blueprint.node.HeadDataNode;
import me.will0mane.lib.uranusspigot.item.blueprint.types.BlueprintC;

public class BlueprintCItemHeadData extends Blueprint<BlueprintC> {
   private final HeadDataNode node;

   public BlueprintCItemHeadData(ItemBuilder item, String data) {
      this.node = new HeadDataNode(item, data);
   }

   public List<BlueprintNode> getNodes() {
      return Collections.singletonList(this.node);
   }

   public void execPin() {
      this.node.executePin();
   }

   public List<ItemBuilder> run() {
      return this.node.executePin();
   }

   public void finishPin() {
   }
}
