package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import java.util.List;

/**
 * Entry point for Homework 9: Chronomancer's Vault.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Inventory scoutInventory = new Inventory();
        scoutInventory.addArtifact(new Weapon("Starter Saber", 35, 4, 4));
        scoutInventory.addArtifact(new Potion("Amber Draught", 25, 1, 18));

        Inventory mageInventory = new Inventory();
        mageInventory.addArtifact(new Scroll("Ward Script", 40, 1, "Barrier"));
        mageInventory.addArtifact(new Ring("Student Focus Ring", 30, 1, 2));

        Hero scout = new Hero("Aruzhan", 100, 30, 14, 7, 80, scoutInventory);
        Hero mage = new Hero("Timur", 82, 45, 8, 5, 35, mageInventory);

        GoldAppraiser goldAppraiser = new GoldAppraiser();
        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        CurseDetector curseDetector = new CurseDetector();

        ChronomancerEngine engine = new ChronomancerEngine(
                goldAppraiser,
                enchantmentScanner,
                curseDetector);
        VaultRunResult result = engine.runVault(List.of(scout, mage));

        System.out.println();
        System.out.println("Final VaultRunResult: " + result);
    }
}
