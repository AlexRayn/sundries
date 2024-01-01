
public class Cat
{
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;
    public static final int EYES_COUNT = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;

    static double returnMassFood;
    static int countCats = 0;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        countCats += 1;

    }

    public Cat(Double weight)
    {
        this();
        this.weight = weight;
    }

    public void meow()
    {
        if(weight >= MIN_WEIGHT && weight <=MAX_WEIGHT)
        {
            weight = weight - 1;System.out.print("Мявк ");
            if(weight <= MIN_WEIGHT){System.out.println("ДОМЯУКАЛАСЬ");}
        }
    }

    public void feed(Double amount)
    {
        if(weight >= MIN_WEIGHT && weight <=MAX_WEIGHT){weight = weight + amount;}
    }

    public void drink(Double amount)
    {
        if(weight >= MIN_WEIGHT && weight <=MAX_WEIGHT){weight = weight + amount;}
    }

    public Double getWeight()
    {
        return weight;
    }
    public void getWeightFood ()
    {
        returnMassFood = weight - originWeight;
        System.out.println("масса седенной еды -  " + returnMassFood);
    }

    public void toilet ()
    {
        if (getWeight() >= 1000 && getWeight() <= 9000)
        {
            weight = weight - 100;
            System.out.println("животное сходило в туалет");
        }
        else {System.out.println("мёртвое животное не может ходить в туалет");
        }
    }
     public static int getCountCats(){return countCats;}

    public String getStatus()
    {
        if(weight < minWeight) {
            countCats -= 1;
            return "умер(ла)";
        }
        else if(weight > maxWeight) {
            countCats -= 1;
            return "взорван(а)";
        }
        else if(weight > originWeight) {
            return "спит";
        }
        else {
            return "играет";
        }
    }
}