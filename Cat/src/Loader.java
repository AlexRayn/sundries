
public class Loader
{
    public static void main(String[] args)
    {
        Cat felix = new Cat();
        System.out.println("феликс " + felix.getStatus());


        Cat bob = new Cat();
        System.out.println("Боб " + bob.getStatus());


        bob.feed(6000.0);
        System.out.println("bob " + bob.getStatus());


        //прекармливаем животное т.е countCats -1
        Cat alex = new Cat();
        alex.feed(100000.0);
        System.out.println("alex " + alex.getStatus());

        Cat murka = new Cat();
        System.out.println("вес мурки - " + murka.getWeight());
        murka.meow();
        System.out.println("вес мурки - " + murka.getWeight());

        //создали цикл пока вес не будет равен 1000, происходит метод мяу уменьшающий вес на 1 грамм.
        while(murka.getWeight() >= 1000 ) {murka.meow();}


        Cat tom = new Cat();
        System.out.println("вес тома " + tom.getWeight());
        tom.feed(500.0);
        tom.drink(10.0);
        System.out.println("вес тома " + tom.getWeight());
        System.out.println("том " + tom.getStatus());


        Cat bars = new Cat();
        System.out.println("барс " + bars.getStatus());


        Cat oskar = new Cat();
        System.out.println("оскар " + oskar.getStatus());


        Cat lama = new Cat();
        System.out.println("лама " + lama.getStatus());
        System.out.println("===============================================");


        Cat vitalii = new Cat();
        System.out.println("виталий - " + vitalii.getWeight());
        System.out.println("том - " + tom.getWeight());
        vitalii.feed(2000.0);
        vitalii.drink(200.0);
        System.out.println("===============================================");
        System.out.println("виталий - " + vitalii.getWeight());
        System.out.println("том - " + tom.getWeight());

        //выводим в консоль количество живых кошек, используя метод countCats.
        System.out.println("количество живых кошек - " + Cat.countCats);

    }
}