package character.heroes;

import character.Characters;
import character.beasts.Orcs;
import util.Util;

abstract public class Heroes extends Characters {

    public Heroes(int life, int shield, String name) {
        super(life, shield, name);
    }

    @Override
    public int getNumberDice() {
        return 2;
    }

    @Override
    public int getModDefense(Characters attack) {
        if (attack instanceof Orcs) {
            System.out.println("Hero was frightened by the orc and lowered his shield. -10");
            return -10;
        }
        return 0;
    }

    public static Characters newInstanceRandom(int life, int shield, int number) {
        int tipoHeroe = Util.generateRandomNumber(1, 3);

        switch (tipoHeroe) {
            case 1:
                // Elfo
                return new Elves(life, shield, Elves.getRandomName() + " " + number);
            case 2:
                // Hobbit
                return new Hobbit(life, shield, Hobbit.getRandomName() + " " + number);
            case 3:
                // Humanos
                return new Humans(life, shield, Humans.getRandomName() + " " + number);
            default:
                throw new IllegalArgumentException("Invalid hero type: " + tipoHeroe);
        }
    }
}
