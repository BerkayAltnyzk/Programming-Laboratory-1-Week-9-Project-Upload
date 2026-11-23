package lab_week_9;


public class Lab_Week_9 {

    public static void main(String[] args) {
        Team myTeam = new Team();

        Warrior w1 = new Warrior("Aragorn", 10, 200, 30);
        Mage m1 = new Mage("Gandalf", 12, 140, 100);
        Healer h1 = new Healer("Elrond", 9, 150, 40);

        myTeam.addCharacter(w1);
        myTeam.addCharacter(m1);
        myTeam.addCharacter(h1);

        System.out.println("--- Testing Invalid Inputs ---");
        myTeam.addCharacter(new Warrior("Aragorn", 5, 100, 10));
        Warrior invalidWarrior = new Warrior("Boromir", -5, -20, 15);
        myTeam.addCharacter(invalidWarrior);

        Warrior golem = new Warrior("Golem", 20, 500, 50);

        System.out.println("\n--- Initial Team Status ---");
        myTeam.displayTeam();
        System.out.println("Enemy: " + golem.getCharacterInfo());

        System.out.println("\n--- Round 1 ---");
        myTeam.attackAll(golem);
        golem.attack(w1);
        golem.attack(m1);
        myTeam.healTeam();

        System.out.println("\n--- Round 2 ---");
        myTeam.attackAll(golem);
        golem.attack(h1);
        golem.attack(invalidWarrior);
        myTeam.healTeam();

        System.out.println("\n--- Post-Battle Operations ---");
        GameCharacter searched = myTeam.findByName("Gandalf");
        if (searched != null) {
            System.out.println("Found character: " + searched.getCharacterInfo());
        }

        myTeam.removeCharacter("Boromir");
        System.out.println("Removed Boromir.");

        System.out.println("\n--- Final Team Status ---");
        myTeam.displayTeam();
        System.out.println("Enemy: " + golem.getCharacterInfo());
    }
}