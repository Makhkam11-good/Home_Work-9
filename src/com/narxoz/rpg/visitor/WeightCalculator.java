package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Calculates carrying load for vault artifacts.
 */
public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight;
    private int itemCount;

    @Override
    public void visit(Weapon weapon) {
        addWeight("Weapon", weapon.getName(), weapon.getWeight(),
                "battle-ready load with +" + weapon.getAttackBonus() + " attack");
    }

    @Override
    public void visit(Potion potion) {
        addWeight("Potion", potion.getName(), potion.getWeight(),
                "light pack item with " + potion.getHealing() + " healing");
    }

    @Override
    public void visit(Scroll scroll) {
        addWeight("Scroll", scroll.getName(), scroll.getWeight(),
                "paperweight spell focus for " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        addWeight("Ring", ring.getName(), ring.getWeight(),
                "nearly weightless magic bonus +" + ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        addWeight("Armor", armor.getName(), armor.getWeight(),
                "heavy defense load with +" + armor.getDefenseBonus() + " defense");
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getItemCount() {
        return itemCount;
    }

    private void addWeight(String type, String name, int weight, String note) {
        totalWeight += weight;
        itemCount++;
        System.out.println("[WeightCalculator] " + type + " '" + name + "' weighs "
                + weight + " units: " + note);
    }
}
