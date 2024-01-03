
public class Loader
{
    // Метод для генерации котёнка с фиксированным весом
    private static Cat getKitten(){return new Cat(1100.0);}
    public static void main(String[] args)
    {
        // Создание кошек и вывод их статусов
        Cat felix = new Cat();
        System.out.println("феликс " + felix.getStatus());

        Cat bob = new Cat();
        System.out.println("Боб " + bob.getStatus());

        // Кормление Боба
        bob.feed(6000.0);
        System.out.println("bob " + bob.getStatus());


        // Создание кошки Алекс и перекормление
        Cat alex = new Cat();
        alex.feed(100000.0);
        System.out.println("alex " + alex.getStatus());

        // Создание и мяукание кошки Мурки
        Cat murka = new Cat();
        System.out.println("вес мурки - " + murka.getWeight());
        murka.meow();
        System.out.println("вес мурки - " + murka.getWeight());

        // Цикл мяукания Мурки до достижения веса 1000
        while(murka.getWeight() >= 1000 ) {murka.meow();}

        // Создание и кормление Тома
        Cat tom = new Cat();
        System.out.println("вес тома " + tom.getWeight());
        tom.feed(500.0);
        tom.drink(10.0);
        System.out.println("Вес Тома после кормежки и напитка - " + tom.getWeight());
        System.out.println("том " + tom.getStatus());
        System.out.println("===============================================");

        // Создание остальных кошек
        Cat kittenBars = getKitten();
        Cat kittenOskar = getKitten();
        Cat kittenRoxy = getKitten();

        //выводим в консоль статусы и вес котят
        System.out.println("котенок барс " + kittenBars.getStatus());
        System.out.println("вес барса - " + kittenBars.getWeight());
        System.out.println("котенок оскар " + kittenOskar.getStatus());
        System.out.println("вес оскара - " + kittenOskar.getWeight());
        System.out.println("котенок рокси " + kittenRoxy.getStatus());
        System.out.println("вес рокси - " + kittenRoxy.getWeight());
        System.out.println("===============================================");

        // Создание и кормление Виталия, вывод веса до и после
        Cat vitalii = new Cat();
        System.out.println("вес Виталия - " + vitalii.getWeight());

        vitalii.feed(2000.0);
        vitalii.drink(200.0);
        System.out.println("Вес Виталия после кормежки и напитка - " + vitalii.getWeight());

        // Вывод общего количества живых кошек, используя метод countCats.
        System.out.println("количество живых кошек - " + Cat.countCats);
        System.out.println("вес виталия - " + vitalii.getWeight());


        //создаем нового кота используя конструктор копированич, и проверяем работает ли он
        Cat ben = new Cat(vitalii);
        System.out.println("вес виталия - " + vitalii.getWeight());
        System.out.println("вес бена - " + ben.getWeight());
        ben.feed(1000.0);
        System.out.println("вес виталия после кормления боба - " + vitalii.getWeight());
        System.out.println("вес бена после кормления - " + ben.getWeight());

    }
}