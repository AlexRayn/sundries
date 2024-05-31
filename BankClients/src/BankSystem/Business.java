package BankSystem;

public class Business extends Client
{


    public void depositToAccount(double count) {
        balance = balance + count;
        System.out.println("");
    }

    //У юридических лиц — снятие с комиссией 1%.
    public void withdrawalFromAccount(double count) {
        if(balance < (count + (count / 100))){
            System.out.println("На счете недостаточно денежных средств для снятия с комиссией");
        }
        else
        {
            balance = balance - (count + (count / 100));
            System.out.println("с вашего счета было списанно - " + count + " рублей");
            System.out.println("комиссия составила - " + (count / 100) + " рублей");
        }
    }
    @Override
    public double getInformationAccount() {
        System.out.println("данный счет является счетом для юридических лиц,");
        System.out.println("поплнение без комиссии, снятие выполняется с комиссией 1 %");
        System.out.print("баланс счета составляет - ");
        return balance;
    }
}