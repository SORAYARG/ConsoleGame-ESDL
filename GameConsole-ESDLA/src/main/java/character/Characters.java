package character;

import character.beasts.Beasts;
import character.heroes.Heroes;
import util.Util;

public abstract class Characters {
    private static final String[][] characterString = {
            {
                    "     _____       ",
                    " .-,;='';_),-.   ",
                    "  \\_\\(),()/_/    ",
                    "    (,___,)      ",
                    "   ,-/`~`\\-,___  ",
                    "  / /).:.('--._) ",
                    " {_[ (_,_)       ",
                    "     | Y |       ",
                    "    /  |  \\      ",
                    "    |  |  |      ",
                    "    \"\"\" \"\"\"      "
            },
            {
                    "       __ __     ",
                    "    .-',,^,,'.   ",
                    "   / \\(0)(0)/ \\  ",
                    "   )/( ,_\"_,)\\(  ",
                    "   `  >-`~(   '  ",
                    " _N\\ |(`\\ |___   ",
                    " \\' |/ \\ \\/_-,)  ",
                    "  '.(  \\`\\_<     ",
                    "     \\ _\\|       ",
                    "      | |_\\_     ",
                    "      \\_,_>-'    "
            }
    };

    private int life;
    private int shield;
    private String name;
    private int attack;
    private int defaultShield;

    /**
     * Constructor de la clase Characters.
     *
     * @param life   La vida inicial del personaje.
     * @param shield El valor inicial del escudo del personaje.
     * @param name   El nombre del personaje.
     */
    public Characters(int life, int shield, String name) {
        this.life = life;
        this.shield = shield;
        this.name = name;
        this.attack = 0;
        this.defaultShield = shield;
    }

    /**
     * Obtiene el número de dados para el ataque.
     *
     * @return El número de dados para el ataque.
     */
    abstract public int getNumberDice();

    /**
     * Obtiene el modificador de ataque.
     *
     * @param defense El personaje defensor.
     * @return El modificador de ataque.
     */
    abstract public int getModAttack(Characters defense);

    /**
     * Obtiene el modificador de defensa.
     *
     * @param attack El personaje atacante.
     * @return El modificador de defensa.
     */
    abstract public int getModDefense(Characters attack);

    /**
     * Comprueba si el personaje está vivo.
     *
     * @return true si está vivo, false si está muerto.
     */
    public boolean isAlive() {
        return life > 0;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void restoreShieldAttack() {
        this.attack = 0;
        this.shield = this.defaultShield;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        String status = (this.isAlive()) ? "is alive" : "is dead";
        return "- " + getName() + ": life: " + getLife() + ", shield: " + getShield() + ", " + status;
    }

    public void prepareAttack(Characters defender) {
        int amount = Util.rollDiceNTimesGreaterNumber(this.getNumberDice(), 50, 100, "Preparing the attack of " + this.getName()) + this.getModAttack(defender);
        this.setAttack(amount);
    }

    public void defend(Characters attacker) {
        this.setShield(this.getModDefense(attacker) + this.getShield());
        if (attacker.getAttack() > this.getShield()) {
            int damage = attacker.getAttack() - this.getShield();
            this.life -= damage;
            if (this.life < 0) {
                this.life = 0;
            }
            System.out.println(attacker.getName() + "'s attack was successful. " + this.getName()
                    + " receives " + damage + " damage, and now has " + this.getLife() + " life.");
        } else {
            System.out.println(attacker.getName() + "'s attack was completely blocked by " + this.getName());
        }
    }

    public int getLife() {
        return this.life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getShield() {
        return this.shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void fight(Characters attacker, Characters defender) {
        System.out.println("\n\n***** Initiating fight *****\n");
        printTwoCharacters(attacker, defender);
        attacker.prepareAttack(defender);
        defender.defend(attacker);
        System.out.println("\n");
        Util.requestEnter("Press Enter to see results");
        printTwoCharacters(attacker, defender);
        attacker.restoreShieldAttack();
        defender.restoreShieldAttack();
        Util.requestEnter("\n***** Fight Ended *****\nPress Enter to continue\n");
        System.out.println();
    }

    public static void printTwoCharacters(Characters char1, Characters char2) {
        String spaceBetween = "           ";
        String spaceBetweenVrs = "    VS    ";
        int pos1 = (char1 instanceof Heroes) ? 1 : 0;
        int pos2 = (char1 instanceof Beasts) ? 1 : 0;
        for (int i = 0; i < characterString[0].length; i++) {
            System.out.println(Characters.characterString[pos1][i] + spaceBetween + Characters.characterString[pos2][i]);
        }
        System.out.println(Util.fixStringLength(17, "Attacker") + spaceBetween + Util.fixStringLength(17, "Defender"));
        System.out.println(
                Util.fixStringLength(17, char1.getName()) + spaceBetweenVrs + Util.fixStringLength(17, char2.getName()));
        System.out.println(Util.fixStringLength(17, char1.getClassName()) + spaceBetween
                + Util.fixStringLength(17, char2.getClassName()));
        System.out.println(
                Util.fixStringLength(17, "Life: " + char1.getLife()) + spaceBetween + Util.fixStringLength(17, "Life: " + char2.getLife()));
        System.out.println(
                Util.fixStringLength(17, "Shield: " + char1.getShield()) + spaceBetween + Util.fixStringLength(17, "Shield: " + char2.getShield()));
        System.out.println(Util.fixStringLength(17,
                "Attack: " + ((char1.getAttack() == 0) ? "not set" : char1.getAttack())) + spaceBetween
                + Util.fixStringLength(17, "Attack: " + ((char2.getAttack() == 0) ? "not set" : char2.getAttack())));
    }

    public static void printTwoCharactersTeam(Characters[] char1, Characters[] char2, boolean firstWon) {
        String spaceBetween = "           ";
        String spaceBetweenVrs = "    VS    ";
        int pos1 = (char1[0] instanceof Heroes) ? 1 : 0;
        int pos2 = (char1[0] instanceof Beasts) ? 1 : 0;
        for (int i = 0; i < characterString[0].length; i++) {
            System.out.println(Characters.characterString[pos1][i] + spaceBetween + Characters.characterString[pos2][i]);
        }
        System.out.println(Util.fixStringLength(17, (firstWon ? "WINNERS" : "LOSERS")) + spaceBetween
                + Util.fixStringLength(17, (firstWon ? "LOSERS" : "WINNERS")));
        for (int j = 0; j < char2.length; j++) {
            System.out.println(
                    Util.fixStringLength(17, char1[j].getName() + ((char1[j].isAlive() ? "-alive" : "-dead")))
                            + spaceBetweenVrs
                            + Util.fixStringLength(17, char2[j].getName() + ((char2[j].isAlive() ? "-alive" : "-dead"))));
        }
    }
}
