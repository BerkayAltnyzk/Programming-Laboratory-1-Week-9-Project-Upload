package lab_week_9;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Error: A character with the name '" + character.getName() + "' already exists in the team.");
            return false;
        }
        members.add(character);
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            return true;
        }
        return false;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker) {
                int dmg = ((Attacker) c).attack(target);
                System.out.println(c.getName() + " attacked " + target.getName() + " for " + dmg + " damage.");
            }
        }
    }

    public void healTeam() {
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter lowestTarget = null;
                double lowestRatio = Double.MAX_VALUE;

                for (GameCharacter member : members) {
                    if (member.isAlive()) {
                        double ratio = (double) member.getHealth() / member.getMaxHealth();
                        if (ratio < lowestRatio) {
                            lowestRatio = ratio;
                            lowestTarget = member;
                        }
                    }
                }

                if (lowestTarget != null) {
                    int healed = ((Supporter) c).heal(lowestTarget);
                    System.out.println(c.getName() + " healed " + lowestTarget.getName() + " for " + healed + " HP.");
                }
            }
        }
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("--- Team Members ---");
        for (GameCharacter c : members) {
            System.out.println(c.getCharacterInfo() + (c.isAlive() ? " [ALIVE]" : " [DEFEATED]"));
        }
        System.out.println("Total Alive Team Power: " + getTotalPower());
    }
}