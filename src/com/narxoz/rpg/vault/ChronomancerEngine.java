package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    private final GoldAppraiser goldAppraiser;
    private final EnchantmentScanner enchantmentScanner;
    private final CurseDetector curseDetector;

    public ChronomancerEngine() {
        this(new GoldAppraiser(), new EnchantmentScanner(), new CurseDetector());
    }

    public ChronomancerEngine(GoldAppraiser goldAppraiser,
                              EnchantmentScanner enchantmentScanner,
                              CurseDetector curseDetector) {
        this.goldAppraiser = goldAppraiser == null ? new GoldAppraiser() : goldAppraiser;
        this.enchantmentScanner = enchantmentScanner == null ? new EnchantmentScanner() : enchantmentScanner;
        this.curseDetector = curseDetector == null ? new CurseDetector() : curseDetector;
    }

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a summary of appraisals, snapshots, and rewinds
     */
    public VaultRunResult runVault(List<Hero> party) {
        Inventory vaultInventory = buildVaultInventory();

        System.out.println();
        System.out.println("--- Chronomancer's Vault opens ---");
        printParty(party);

        System.out.println();
        System.out.println("--- Appraisal starts ---");
        vaultInventory.accept(goldAppraiser);
        vaultInventory.accept(enchantmentScanner);
        vaultInventory.accept(curseDetector);
        System.out.println("--- Appraisal ends ---");
        System.out.println("Total resale estimate: " + goldAppraiser.getTotalValue() + " gold");
        System.out.println("Cursed artifacts found: " + curseDetector.getCursedCount());

        int mementosCreated = 0;
        int restoredCount = 0;

        if (party == null || party.isEmpty()) {
            System.out.println();
            System.out.println("No heroes entered the vault, so no rewind was needed.");
            return new VaultRunResult(goldAppraiser.getAppraisedCount(), mementosCreated, restoredCount);
        }

        Hero leadHero = party.get(0);
        Caretaker caretaker = new Caretaker();

        System.out.println();
        System.out.println("--- Snapshot taken ---");
        System.out.println("Before snapshot: " + leadHero);
        caretaker.save(leadHero.createMemento());
        mementosCreated++;
        System.out.println("Stored snapshots: " + caretaker.size());

        System.out.println();
        System.out.println("--- Time crystal trap triggers ---");
        triggerTimeCrystalTrap(leadHero);
        System.out.println("After trap: " + leadHero);
        System.out.println("Inventory size after trap: " + leadHero.getInventory().size());

        System.out.println();
        System.out.println("--- Rewind begins ---");
        HeroMemento rewindPoint = caretaker.undo();
        if (rewindPoint != null) {
            leadHero.restoreFromMemento(rewindPoint);
            restoredCount++;
        }
        System.out.println("After rewind: " + leadHero);
        System.out.println("Inventory size after rewind: " + leadHero.getInventory().size());
        System.out.println("--- Rewind ends ---");

        return new VaultRunResult(goldAppraiser.getAppraisedCount(), mementosCreated, restoredCount);
    }

    private Inventory buildVaultInventory() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Chrono Saber", 120, 6, 9));
        inventory.addArtifact(new Potion("Dawn Flask", 45, 1, 24));
        inventory.addArtifact(new Scroll("Scroll of Doom Echo", 80, 1, "Doom Echo"));
        inventory.addArtifact(new Ring("Looping Star Ring", 160, 1, 7));
        inventory.addArtifact(new Armor("Hourglass Plate", 140, 28, 8));
        return inventory;
    }

    private void printParty(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            System.out.println("Party: no heroes");
            return;
        }

        System.out.println("Party entering the vault:");
        for (Hero hero : party) {
            System.out.println("- " + hero);
        }
    }

    private void triggerTimeCrystalTrap(Hero hero) {
        hero.takeDamage(35);
        hero.spendMana(10);
        hero.spendGold(25);
        hero.getInventory().addArtifact(new Ring("Unstable Time Shard", 5, 1, 9));
    }
}
