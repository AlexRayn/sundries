import BankSystem.Business;
import BankSystem.Entrepreneur;
import BankSystem.Individual;

public class Main {
    public static void main(String[] args)
    {
        //создаем счет физического лица, и вызываем у него различные методы
        Individual individualAccount = new Individual();
        System.out.println(individualAccount.getInformationAccount());
        individualAccount.depositToAccount(1000);
        individualAccount.withdrawalFromAccount(400);
        System.out.println(individualAccount.getInformationAccount());

        //создаем счет юридического лица, и вызываем у него различные методы
        Business businessAccount = new Business();
        businessAccount.depositToAccount(4000);
        businessAccount.withdrawalFromAccount(2500);
        System.out.println(businessAccount.getInformationAccount());

        //создаем счет индивидуального предпринимателя, и вызываем у него различные методы
        Entrepreneur entrepreneurAccount = new Entrepreneur();
        System.out.println(entrepreneurAccount.getInformationAccount());
        entrepreneurAccount.depositToAccount(900);
        entrepreneurAccount.depositToAccount(4100);
        entrepreneurAccount.withdrawalFromAccount(5000);
        entrepreneurAccount.withdrawalFromAccount(4900);
        System.out.println(entrepreneurAccount.getInformationAccount());
    }
}