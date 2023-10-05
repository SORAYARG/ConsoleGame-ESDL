package character.heroes;

import character.Characters;
import character.beasts.Orcs;
import util.Util;

public class Elves extends Heroes {
    private static final String[] names = { "Legolas", "Bladimir" };

    public Elves(int life, int shield, String name) {
        super(life, shield, name);
    }

    @Override
    public int getModAttack(Characters defense) {
        if (defense instanceof Orcs) {
            System.out.println("Elves hate orcs, so their attack power increases. +10");
            return 10;
        }
        return 0;
    }

    public static String getRandomName() {
        int randomIndex = Util.generateRandomNumber(0, names.length - 1);
        return names[randomIndex];
    }
}
