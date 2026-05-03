package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Flags artifacts that look dangerous to use.
 */
public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;
    private int checkedCount;

    @Override
    public void visit(Weapon weapon) {
        report("Weapon", weapon.getName(), weapon.getAttackBonus() > 8,
                "too much bloodlust in the blade");
    }

    @Override
    public void visit(Potion potion) {
        report("Potion", potion.getName(), potion.getHealing() < 15,
                "weak brew may be a disguised toxin");
    }

    @Override
    public void visit(Scroll scroll) {
        report("Scroll", scroll.getName(), scroll.getSpellName().toLowerCase().contains("doom"),
                "forbidden doom rune detected");
    }

    @Override
    public void visit(Ring ring) {
        report("Ring", ring.getName(), ring.getMagicBonus() > 6,
                "arcane feedback is unstable");
    }

    @Override
    public void visit(Armor armor) {
        report("Armor", armor.getName(), armor.getWeight() > 25,
                "weight suggests a binding curse");
    }

    public int getCursedCount() {
        return cursedCount;
    }

    public int getCheckedCount() {
        return checkedCount;
    }

    private void report(String type, String name, boolean cursed, String reason) {
        checkedCount++;
        if (cursed) {
            cursedCount++;
            System.out.println("[CurseDetector] " + type + " '" + name + "' is cursed: " + reason);
            return;
        }
        System.out.println("[CurseDetector] " + type + " '" + name + "' is safe");
    }
}
