package character.heroes;

import character.Characters;
import util.Util;

public class Humans extends Heroes {
    private static final String[] names = {"Aragorn", "Boromir"};

    public Humans(int life, int shield, String name) {
        super(life, shield, name);
    }

    @Override
    public int getModAttack(Characters defense) {
        return 0;
    }

    public static String getRandomName() {
        int randomIndex = Util.generateRandomNumber(0, names.length - 1);
        return names[randomIndex];
    }
}
