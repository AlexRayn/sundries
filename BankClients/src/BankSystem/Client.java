package BankSystem;

import java.sql.SQLOutput;

abstract class Client
{
    double balance;//хранит текущий остаток на счёте
    /*
    1. Создайте классы, представляющие клиентов банка: абстрактный класс Client,
     классы для физических лиц, юридических лиц и индивидуальных предпринимателей.

     У каждого клиента есть сумма денег на счету (число). Деньги можно положить на счёт,
     снять и вернуть остаток на счёте. Каждый класс должен содержать метод, который выводит информацию
     в консоль о счёте: условие пополнения, условие снятия и баланс.
     */

    //метод установки баланса
    public void clients(double balance)
    {
        this.balance = balance;
    }
    // Метод для внесения денег на счёт
    abstract public void depositToAccount(double count);

    abstract public void withdrawalFromAccount(double count);

    //метод для проверки остатка на счете
//    public double getBalance()
//    {
//        System.out.print("баланс счета составляет - ");
//        return balance;
//    }

    abstract public double getInformationAccount();
}