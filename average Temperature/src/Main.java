public class Main {
    public static final int COUNT_PATIENTS = 30;
    public static final int MIN_TEMPERATURE = 32;
    public static final int MAX_TEMPERATURE = 40;
    public static final float MIN_HEALTHY_TEMPERATURE = 36.2F;
    public static final float MAX_HEALTHY_TEMPERATURE = 36.9F;

    public static void main(String[] args) {
        int healthyPatients = 0;
        double sumTemperature = 0;
        float [] patients = new float [COUNT_PATIENTS];{

            for(int i = 0; i < patients.length; i++)
            {
                //использует метод Math.random() для генерации числа от 0 до 1,
                // затем масштабирует его (от 32 до 40) к нужному диапазону
                // (умножаем результат на длину диапазона, то есть 40 - 32 = 0.8
                // а затем добавить минимальное значение диапазона (32))
                float temperature = (float) (Math.random() * (MAX_TEMPERATURE - MIN_TEMPERATURE) + MIN_TEMPERATURE);

                patients[i] = temperature;
                sumTemperature += patients[i];

                //проверяем количество здрововых пациентов и если температура подходит то увеличиваем переменную.
                if(patients[i] >= MIN_HEALTHY_TEMPERATURE && patients[i] <= MAX_HEALTHY_TEMPERATURE){healthyPatients++;}

                System.out.println( (i + 1) + " пациэнт с температурой - " + patients[i]);


            }
            //Для нахождения среднего значения массива чисел необходимо сложить все
            //элементы массива и разделить полученную сумму на количество элементов.
            System.out.println("Средняя температура по больнице - " + (sumTemperature / (double) COUNT_PATIENTS));
            System.out.println("Количество здоровых пациентов - " + healthyPatients);
        }
    }
}