package com.narxoz.rpg.combatant;

import com.narxoz.rpg.artifact.Inventory;

/**
 * Represents a player-controlled hero participating in the vault run.
 *
 * The hero owns its mutable combat state and will eventually create and
 * restore mementos for the Chronomancer's Vault rewind mechanic.
 */
public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private int mana;
    private int gold;
    private final int attackPower;
    private final int defense;
    private Inventory inventory;

    public Hero(String name, int hp, int attackPower, int defense) {
        this(name, hp, 0, attackPower, defense, 0, new Inventory());
    }

    public Hero(String name,
                int hp,
                int mana,
                int attackPower,
                int defense,
                int gold,
                Inventory inventory) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mana = mana;
        this.gold = gold;
        this.attackPower = attackPower;
        this.defense = defense;
        this.inventory = inventory == null ? new Inventory() : inventory;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMana() {
        return mana;
    }

    public int getGold() {
        return gold;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * Reduces this hero's HP by the given amount, clamped to zero.
     *
     * @param amount the damage to apply; must be non-negative
     */
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    /**
     * Restores this hero's HP by the given amount, clamped to maxHp.
     *
     * @param amount the HP to restore; must be non-negative
     */
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    /**
     * Restores mana by the given amount, clamped to a non-negative value.
     *
     * @param amount the mana to restore; must be non-negative
     */
    public void restoreMana(int amount) {
        mana += Math.max(0, amount);
    }

    /**
     * Spends the given amount of mana if available.
     *
     * @param amount the mana to spend; must be non-negative
     * @return true if the mana was spent, false otherwise
     */
    public boolean spendMana(int amount) {
        if (amount < 0 || amount > mana) {
            return false;
        }
        mana -= amount;
        return true;
    }

    /**
     * Adds gold to this hero.
     *
     * @param amount the gold to add; must be non-negative
     */
    public void addGold(int amount) {
        gold += Math.max(0, amount);
    }

    /**
     * Spends gold if the hero has enough.
     *
     * @param amount the gold to spend; must be non-negative
     * @return true if the gold was spent, false otherwise
     */
    public boolean spendGold(int amount) {
        if (amount < 0 || amount > gold) {
            return false;
        }
        gold -= amount;
        return true;
    }

    /**
     * Replaces the hero's inventory.
     *
     * @param inventory the new inventory; null creates an empty inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory == null ? new Inventory() : inventory;
    }

    /**
     * Creates a memento for the hero's current state.
     *
     * @return a HeroMemento snapshot
     */
    public HeroMemento createMemento() {
        return new HeroMemento(
                name,
                hp,
                mana,
                gold,
                maxHp,
                attackPower,
                defense,
                inventory.getArtifacts());
    }

    /**
     * Restores this hero from a previously captured memento.
     *
     * @param memento the snapshot to restore from
     */
    public void restoreFromMemento(HeroMemento memento) {
        if (memento == null) {
            return;
        }

        hp = memento.getHp();
        mana = memento.getMana();
        gold = memento.getGold();
        inventory = new Inventory(memento.getInventorySnapshot());
    }

    @Override
    public String toString() {
        return "Hero{"
                + "name='" + name + '\''
                + ", hp=" + hp
                + ", mana=" + mana
                + ", gold=" + gold
                + ", attackPower=" + attackPower
                + ", defense=" + defense
                + '}';
    }
}
