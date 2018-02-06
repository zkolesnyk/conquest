import java.util.Scanner;

public class Nation {
    private int land;
    private int troops;

    private String name;

    private int food;
    private int gold;
    private int people;
    private int farmers;
    private int merchants;
    private int blacksmiths;

    private Nation(String name) {
        this.name = name;
        land = 20;
        food = 50;
        troops = 15;
        gold = 100;
        people = 100;
        farmers = 0;
        merchants = 0;
        blacksmiths = 0;
    }

    public Nation() {

    }

    private static Nation nation1;
    private static Nation nation2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tempString;

        System.out.println("Добро пожаловать в игру Завоевание!");
        System.out.println("Игрок 1, введи своё имя: ");
        tempString = in.next();
        nation1 = new Nation(tempString);

        System.out.println("Игрок 2, введи своё имя: ");
        tempString = in.next();
        nation2 = new Nation(tempString);

        while (nation1.takeTurn() && nation2.takeTurn()) {

        }
    }

    private boolean takeTurn() {
        System.out.println(String.format("Ход %s", name));
        people += land * 0.2;
        food += farmers - people * 0.25;
        gold += merchants * 20;
        troops += blacksmiths;

        menu();

        return nation1.land > 0 && nation2.land > 0;
    }

    private void menu() {
        while (true) {
            int input = 0;
            System.out.println(String.format("пища %s", food));
            System.out.println(String.format("золото %s", gold));
            System.out.println(String.format("территория %s", land));
            System.out.println(String.format("купцы %s", merchants));
            System.out.println(String.format("войсковые соединения %s", troops));
            System.out.println(String.format("безработные %s", people));

            System.out.println("1) купить земли");
            System.out.println("2) нанять фермеров");
            System.out.println("3) нанять купцов");
            System.out.println("4) нанять оружейников");
            System.out.println("5) в атаку!");
            System.out.println("6) сделать ход");

            Scanner in = new Scanner(System.in);
            input = in.nextInt();

            switch (input) {
                case 1: // покупка земли
                    System.out.println(String.format("Вы купили %d участков земли.", gold / 20));
                    land += gold / 20;
                    gold %= 20;
                    System.out.println(String.format("Теперь у вас %d золота.", gold));
                    break;
                case 2: // найм фермеров
                    farmers += people;
                    System.out.println(String.format("Вы наняли %d феомеров.", farmers));
                    people = 0;
                    break;
                case 3: // найм купцов
                    merchants += people;
                    System.out.println(String.format("Вы наняли %d купцов.", merchants));
                    people = 0;
                    break;
                case 4: // найм кузнецов
                    blacksmiths += people;
                    System.out.println(String.format("Вы наняли %d кузнецов.", blacksmiths));
                    people = 0;
                    break;
                case 5: // сражение
                    System.out.println("Сражение затянулось до поздней ночи и все погибли!");
                    if (nation1.troops < nation2.troops) {
                        nation2.land += 10;
                        nation1.land -= 10;
                    } else if (nation1.troops > nation2.troops) {
                        nation2.land -= 10;
                        nation1.land += 10;
                    }
                    nation1.troops = 0; // война - кровааое занятие!
                    nation2.troops = 0;

                    break;
                case 6: return; // конец хода
            }
        }
    }
}
