package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Prints magical readings for each artifact type.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    private int scannedCount;

    @Override
    public void visit(Weapon weapon) {
        scannedCount++;
        System.out.println("[EnchantmentScanner] Weapon '" + weapon.getName()
                + "' hums with +" + weapon.getAttackBonus() + " battle energy");
    }

    @Override
    public void visit(Potion potion) {
        scannedCount++;
        System.out.println("[EnchantmentScanner] Potion '" + potion.getName()
                + "' contains " + potion.getHealing() + " restorative essence");
    }

    @Override
    public void visit(Scroll scroll) {
        scannedCount++;
        System.out.println("[EnchantmentScanner] Scroll '" + scroll.getName()
                + "' carries the spell " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        scannedCount++;
        System.out.println("[EnchantmentScanner] Ring '" + ring.getName()
                + "' radiates +" + ring.getMagicBonus() + " arcane focus");
    }

    @Override
    public void visit(Armor armor) {
        scannedCount++;
        System.out.println("[EnchantmentScanner] Armor '" + armor.getName()
                + "' projects +" + armor.getDefenseBonus() + " warding force");
    }

    public int getScannedCount() {
        return scannedCount;
    }
}
