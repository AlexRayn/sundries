package BankSystem;

public class Individual extends Client
{
    //У физических лиц пополнение и снятие происходит без комиссии.
    @Override
    public void depositToAccount(double count) {
        balance = balance + count;
        System.out.println("");
    }

    @Override
    public void withdrawalFromAccount(double count) {
        if(balance < count){
            System.out.println("На счете недостаточно денежных средств");
        }
        else
        {
            balance = balance - count;
            System.out.println("с вашего счета было списанно - " + count + " рублей");
        }

    }

    @Override
    public double getInformationAccount() {
        System.out.println("данный счет является счетом для физических лиц,");
        System.out.println("поплнение и снятие с данного счета выполняется без комиссии");
        System.out.print("баланс счета составляет - ");
        return balance;
    }
}