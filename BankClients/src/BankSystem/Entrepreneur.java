package BankSystem;

public class Entrepreneur extends Client
{
    //У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей. И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.


    @Override
    public void depositToAccount(double count) {
        //коммиссия не учитывается при определеннии суммы пополнения
        if((count - (count /100) < 1000)){balance = balance + (count - (count /100));
            System.out.println("Вашь счет ИП был пополнен на сумму " + (count - (count /100)) + " рублей");
            System.out.println("комиссия составила - " + (count / 100) + " рублей");
        }
        else {balance = balance + (count - (count /200));
            System.out.println("Вашь счет ИП был пополнен на сумму " + (count - (count /200)) + " рублей");
            System.out.println("комиссия составила - " + (count / 200) + " рублей");
        }

    }

    @Override
    public void withdrawalFromAccount(double count) {
        if(balance < count){
            System.out.println("На счете ИП недостаточно денежных средств");
        }
        else
        {
            balance = balance - count;
            System.out.println("с вашего счета ИП  было списанно - " + count + " рублей");
        }
    }
    @Override
    public double getInformationAccount() {
        System.out.println("данный счет является счетом для индивидуальных предпринимателей,");
        System.out.println("снятие со счета выполняется без комиссии");
        System.out.println("снятие выполняется с комиссией 1 процент елси сума меньше 1000, ");
        System.out.println("и с комиссией 0,5 процентов если сумма больше 1000");
        System.out.print("баланс счета составляет - ");
        return balance;
    }
}