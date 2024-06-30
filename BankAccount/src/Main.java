import bankSystem.BankAccount;
import bankSystem.CardAccount;
import bankSystem.DepositAccount;

public class Main {
    public static void main(String[] args)
    {
        //создаем депозитный счет с которого нельзя снимать деньги в течении месяца последнего пополнения
        DepositAccount myDeposit = new DepositAccount(1000);
        myDeposit.getBalance();
        myDeposit.withdrawalFromAccount(100);


        // Создадим разные типы счетов
        BankAccount bankAccount = new BankAccount(3000); // Базовый счёт
        DepositAccount depositAccount = new DepositAccount(1000); // Депозитный счёт
        CardAccount cardAccount = new CardAccount(2000); // Карточный счёт

        // Перевод со счёта на карточный счёт
        bankAccount.send(cardAccount, 500); // Перевод 500 рублей

        // Снятие 1000 с учетом комиссии 1%
        cardAccount.withdrawalFromAccount(1000);//снятие 100 рублей

        // Перевод с карточного счёта на депозитный
        cardAccount.send(depositAccount, 300); // Перевод 300 рублей

        // Проверяем остатки на счетах
        bankAccount.getBalance();
        cardAccount.getBalance();
        depositAccount.getBalance();
    }
}