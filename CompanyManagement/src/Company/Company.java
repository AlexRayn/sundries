package Company;
import java.util.Comparator;

import Employees.Employee;
import Employees.Manager;
import Employees.Operator;
import Employees.TopManager;

import java.util.ArrayList;

public class Company {
    //ID Сотрудника
    int numberOfEmployees = 1;
    //Прибыль компании
    private double incomeOfCompany;

    //создаем список сотрудников
    public ArrayList<Employee> employees = new ArrayList<>();

    //Метод найма одного сотрудника
    public void hire(String typeOfEmployee){

        String idOfEmployee = "" + numberOfEmployees;

        if(typeOfEmployee.equalsIgnoreCase("Operator")){
            numberOfEmployees++;
            Operator operator = new Operator(idOfEmployee);
            employees.add(operator);
        }
        else if(typeOfEmployee.equalsIgnoreCase("TopManager")){
            numberOfEmployees++;
            // Передаём ссылку на текущую компанию (this) в конструктор
            TopManager topManager = new TopManager(idOfEmployee, this);
            employees.add(topManager);
        }
        else if(typeOfEmployee.equalsIgnoreCase("Manager")){
            numberOfEmployees++;
            Manager manager = new Manager(idOfEmployee);
            incomeOfCompany += manager.getIndividualContributionIncome();
            employees.add(manager);
        }
        else System.out.println("Введите корректный тип сотрудника Manager, TopManager, Operator");
    }

    //Метод найма списка сотрудников
    public void hire(String typeOfEmployee, int count){

        if (count <= 0){
            System.out.println("Введите корректное количество сотрудников для найба, (больше 0)");
        }
        for (int i = 0; i < count; i++) {
            String idOfEmployee = "" + numberOfEmployees;

            if (typeOfEmployee.equalsIgnoreCase("Operator")) {
                numberOfEmployees++;
                Operator operator = new Operator(idOfEmployee);
                employees.add(operator);
            } else if (typeOfEmployee.equalsIgnoreCase("TopManager")) {
                numberOfEmployees++;
                // Передаём ссылку на текущую компанию (this) в конструктор
                TopManager topManager = new TopManager(idOfEmployee, this);
                employees.add(topManager);
            } else if (typeOfEmployee.equalsIgnoreCase("Manager")) {
                numberOfEmployees++;
                Manager manager = new Manager(idOfEmployee);
                incomeOfCompany += manager.getIndividualContributionIncome();
                employees.add(manager);
            } else System.out.println("Введите корректный тип сотрудника Manager, TopManager, Operator");
        }
    }

    //Метод увольнения сотрудника
    //увольнение сотрудника – fire(),
    public void fire(int number) {
        String id = "" + number; // преобразуем ID в строку
        Employee employeeToRemove = null;

        // Найдём нужного сотрудника
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employeeToRemove = employee;
                break;
            }
        }

        // Если нашли, удаляем
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            System.out.println("Сотрудник ID:" + employeeToRemove.getId() +
                    ", находящийся в должности " + employeeToRemove.getTypeOfEmployee() + ", был уволен.");
        } else {
            // Иначе сообщаем, что сотрудник не найден
            System.out.println("Сотрудник ID:" + id + ", не найден");
        }
    }

    //Создаем метод, возвращающий список, отсортированный по убыванию зп, указанной длины
    public ArrayList<Employee> getLowestSalaryStaff(int count) {
        ArrayList<Employee> sortedLowestSalary = new ArrayList<>();
        sortedLowestSalary.addAll(employees);
        sortedLowestSalary.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getMonthlySalary() > o2.getMonthlySalary()) {
                    return 1;
                } else if (o1.getMonthlySalary() < o2.getMonthlySalary()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        //сокращаем список до нужного размера
        while (sortedLowestSalary.size() > count) {
            sortedLowestSalary.remove(sortedLowestSalary.size() - 1);
        }
        return sortedLowestSalary;
    }

    //Создаем метод, возвращающий список, отсортированный по убыванию зп, указанной длины
    public ArrayList<Employee> getTopSalaryStaff(int count) {
        if (count <= 0 || count > employees.size()) {
            throw new IllegalArgumentException("Некорректное значение count");
        }
        else{
            ArrayList<Employee> sortedTopSalary = new ArrayList<>();
            sortedTopSalary.addAll(employees);
            sortedTopSalary.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    if (o1.getMonthlySalary() > o2.getMonthlySalary()) {
                        return -1;
                    } else if (o1.getMonthlySalary() < o2.getMonthlySalary()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });

            //сокращаем список до нужного размера
            while (sortedTopSalary.size() > count) {
                sortedTopSalary.remove(sortedTopSalary.size() - 1);
            }
            return sortedTopSalary;
        }
    }





    //метод для печати зарплат работников
    public void printSalaries(ArrayList<Employee> employeesToPrint) {
        System.out.println("Список зарплат сотрудников:");
        for (Employee employee : employeesToPrint) {
            System.out.println("Сотрудник ID:" + employee.getId()
                    + ", Должность: " + employee.getTypeOfEmployee()
                    + ", Зарплата: " + (int) employee.getMonthlySalary() + " руб.");
        }
    }


//    public void printSalaries() {
//        System.out.println("Список зарплат сотрудников:");
//        for (Employee employee : employees) {
//            System.out.println("Сотрудник ID:" + employee.getId()
//                    + ", Должность: " + employee.getTypeOfEmployee()
//                    + ", Зарплата: " + employee.getMonthlySalary());
//        }
//    }

    //Метод для получения дохода компании
    public double getIncome(){
        return incomeOfCompany;
    }
}
