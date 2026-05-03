package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Estimates resale prices for vault artifacts.
 */
public class GoldAppraiser implements ArtifactVisitor {

    private int totalValue;
    private int appraisedCount;

    @Override
    public void visit(Weapon weapon) {
        appraise("Weapon", weapon.getName(), weapon.getValue() + weapon.getAttackBonus() * 12);
    }

    @Override
    public void visit(Potion potion) {
        appraise("Potion", potion.getName(), potion.getValue() + potion.getHealing() * 2);
    }

    @Override
    public void visit(Scroll scroll) {
        appraise("Scroll", scroll.getName(), scroll.getValue() + scroll.getSpellName().length() * 5);
    }

    @Override
    public void visit(Ring ring) {
        appraise("Ring", ring.getName(), ring.getValue() + ring.getMagicBonus() * 25);
    }

    @Override
    public void visit(Armor armor) {
        appraise("Armor", armor.getName(), armor.getValue() + armor.getDefenseBonus() * 15);
    }

    public int getTotalValue() {
        return totalValue;
    }

    public int getAppraisedCount() {
        return appraisedCount;
    }

    private void appraise(String type, String name, int estimatedValue) {
        totalValue += estimatedValue;
        appraisedCount++;
        System.out.println("[GoldAppraiser] " + type + " '" + name + "' resale estimate: "
                + estimatedValue + " gold");
    }
}
