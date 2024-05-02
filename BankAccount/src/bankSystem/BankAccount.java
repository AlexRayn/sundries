package bankSystem;

public class BankAccount {
    public double balance;// Хранит текущий остаток на счёте

    public BankAccount(double initialBalance) {
        this.balance = initialBalance; // Устанавливает начальный баланс
    }

    // Метод для внесения денег на счёт
    public void depositToAccount(double count)
    {
        balance = balance + count;
        System.out.println("Вы пополнили счёт на сумму " + count + " рублей");
    }

    // Метод для снятия денег со счёта
    public void withdrawalFromAccount(double count)
    {
        // Проверяем, достаточно ли денег для снятия
        if(balance < count)
        {
            System.out.println("На вашем счете недостаточно денежных средств");
        }
        // Если достаточно, уменьшаем баланс
        else
        {
            balance = balance - count;
            System.out.println("С вашего счета было списано " + count + " рублей");
        }
    }

    //метод для проверки остатка на счете
    public double getBalance() {
        return balance;
        //System.out.println("Баланс вашего счета составляет - " + balance + " рублей.");
    }

    //Метод для отправки денег с одного счёта на другой. Метод должен вернуть true, если деньги успешно переведены.
    public boolean send(BankAccount receiver, double amount)
    {
        if(this.balance >= amount)
        {
            this.withdrawalFromAccount(amount);//снимаем деньги с аккаунта
            receiver.depositToAccount(amount);//Внести на счет-получатель
            return true;
        }
        else
        {
            System.out.println("На вашем счете недостаточно денежных средств");//иначе сообщение об ошибке
            return false;
        }
    }
}
