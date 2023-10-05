package character.beasts;

import character.Characters;
import util.Util;

public abstract class Beasts extends Characters {


    public Beasts(int life, int shield, String name) {
        super(life, shield, name);
    }

    @Override
    public int getNumberDice() {
        return 1;
    }

    public static Characters newInstanceRandom(int life, int shield, int number) {
        int tipoHeroe = Util.generateRandomNumber(1, 2);

        switch (tipoHeroe) {
            case 1:
                // Orco
                return new Orcs(life, shield, "Orc " + number);
            case 2:
                // Hobbit
                return new Goblins(life, shield, "Goblin " + number);
            default:
                throw new IllegalArgumentException("Invalid beast type: " + tipoHeroe);
        }
    }
}
