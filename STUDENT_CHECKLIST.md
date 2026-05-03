# Student Checklist - Homework 9

Use this checklist to track your progress. Work through the phases in order - each phase builds on the previous one.

---

## Phase 1: Understand the Patterns

- [x] Read `ASSIGNMENT.md` from start to finish
- [x] In your own words, write down what Visitor does and what Memento does
- [x] Identify why the two patterns are independent in this assignment
- [x] Read `FAQ.md` sections on Visitor and Memento
- [x] Review the provided source files in `artifact/`, `combatant/`, `memento/`, and `vault/`

### Phase 1 Notes

Visitor lets the program add new operations for a fixed set of artifact types.
The inventory only walks through artifacts, and each artifact calls the correct
`visit(...)` overload through `accept(visitor)`. This avoids `instanceof` checks
and keeps new appraisal behavior inside new visitor classes.

Memento lets `Hero` save and restore its mutable state without exposing that
state to the caretaker. `Hero` creates and reads `HeroMemento`, while
`Caretaker` only stores snapshots and returns them later.

The patterns are independent because Visitor works with the artifact hierarchy,
while Memento works with hero state. `ChronomancerEngine` can use both during
one demo run, but visitors do not need mementos and mementos do not need
visitors.

---

## Phase 2: Design on Paper First

- [ ] Sketch a class diagram showing `ArtifactVisitor` and the artifact hierarchy
- [ ] Sketch a class diagram showing `Hero`, `HeroMemento`, and `Caretaker`
- [ ] Decide what each of your first 3 visitors will compute or print
- [ ] Decide what hero state will be saved in the memento
- [ ] Decide what event will trigger the rewind in the demo
- [ ] Decide what your 4th visitor will prove for open/closed behavior

---

## Phase 3: Implement the Visitor Skeleton

- [ ] Create at least 3 concrete visitor classes in separate `.java` files
- [ ] Verify each visitor implements all 5 `visit(...)` overloads
- [ ] Verify each visitor produces visibly different output
- [ ] Implement `accept(ArtifactVisitor visitor)` in `Weapon.java`
- [ ] Implement `accept(ArtifactVisitor visitor)` in `Potion.java`
- [ ] Implement `accept(ArtifactVisitor visitor)` in `Scroll.java`
- [ ] Implement `accept(ArtifactVisitor visitor)` in `Ring.java`
- [ ] Implement `accept(ArtifactVisitor visitor)` in `Armor.java`
- [ ] Implement `Inventory.accept(visitor)` to walk the list

---

## Phase 4: Implement the Memento Boundary

- [ ] Verify `HeroMemento.java` keeps its getters package-private
- [ ] Verify `HeroMemento` has no public mutable fields
- [ ] Add the hero fields that belong in the snapshot
- [ ] Implement `Hero.createMemento()`
- [ ] Implement `Hero.restoreFromMemento(HeroMemento)`
- [ ] Keep `Caretaker` in a different package from `HeroMemento`

---

## Phase 5: Implement the Caretaker

- [ ] Implement `Caretaker.save(HeroMemento memento)`
- [ ] Implement `Caretaker.undo()`
- [ ] Implement `Caretaker.peek()`
- [ ] Implement `Caretaker.size()`
- [ ] Verify `Caretaker` treats mementos as opaque values
- [ ] Verify `Caretaker` never reads memento internals

---

## Phase 6: Implement the Vault Engine

- [ ] Fill in `ChronomancerEngine.runVault(...)`
- [ ] Build a mixed `Inventory` with at least 5 artifacts
- [ ] Apply at least 3 visitors through `Inventory.accept(visitor)`
- [ ] Save a hero snapshot before the vault event
- [ ] Trigger a state change or trap
- [ ] Restore the hero from a saved memento
- [ ] Return a meaningful `VaultRunResult`

---

## Phase 7: Wire Everything in Main.java

- [ ] Create at least 2 heroes with different starting states
- [ ] Instantiate your concrete visitors
- [ ] Instantiate `ChronomancerEngine`
- [ ] Run the vault demo
- [ ] Print the final `VaultRunResult`
- [ ] Make sure the banner prints without crashing

---

## Phase 8: Verify Output and Open/Closed Behavior

- [ ] The output clearly shows when appraisal starts and ends
- [ ] The output clearly shows when the snapshot is taken
- [ ] The output clearly shows when the rewind happens
- [ ] At least one visitor behaves differently for at least 2 artifact types
- [ ] Add a 4th visitor without modifying any file under `artifact/`
- [ ] Confirm the new visitor works through `Inventory.accept(visitor)`

---

## Phase 9: Draw UML Diagrams and Submit

- [ ] **Diagram 1 - Visitor:** interface, all artifact classes, and `Inventory`
- [ ] **Diagram 2 - Memento:** `Hero`, `HeroMemento`, `Caretaker`, and `ChronomancerEngine`
- [ ] Check that both diagrams are legible and clearly labeled
- [ ] Confirm all `.java` files compile
- [ ] Confirm no `.class` files are included in the ZIP
- [ ] Submit the finished homework
