package character.heroes;

import character.Characters;
import character.beasts.Goblins;
import util.Util;

public class Hobbit extends Heroes {
    private static final String[] names = { "Frodo", "Bilbo", "Sam" };

    public Hobbit(int life, int shield, String name) {
        super(life, shield, name);
    }

    @Override
    public int getModAttack(Characters defense) {
        if (defense instanceof Goblins) {
            System.out.println("Hobbits were frightened of the goblins and attacked more softly. -5");
            return -5;
        }
        return 0;
    }

    public static String getRandomName() {
        int randomIndex = Util.generateRandomNumber(0, names.length - 1);
        return names[randomIndex];
    }
}
