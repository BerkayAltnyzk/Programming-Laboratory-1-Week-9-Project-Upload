package lab_week_9;

public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 5 + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        int restore = healAmount + getLevel();
        return target.restoreHealth(restore);
    }

    @Override
    public String getCharacterInfo() {
        return "Healer: " + getName() + " | Level: " + getLevel() + " | HP: " + getHealth() + "/" + getMaxHealth() + " | Heal Amount: " + healAmount + " | Power: " + calculatePower();
    }
}