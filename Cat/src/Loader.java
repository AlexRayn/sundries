
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


        Cat alex = new Cat();
        alex.feed(100000.0);
        alex.getStatus();

        Cat murka = new Cat();
        System.out.println("вес мурки - " + murka.getWeight());
        murka.meow();
        System.out.println("вес мурки - " + murka.getWeight());
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
        System.out.println("количество живых кошек - " + Cat.countCats);

    }
}