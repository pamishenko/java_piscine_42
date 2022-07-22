package ex01;

import java.util.HashMap;

import java.util.Map;

import java.util.Set;



/**

 * Алгоритм сопоставления сходства строк

 */

public class CosDemo {

// Анализ структуры данных: <words, двумерный массив>, где слова представляют общие слова,

    //Одно измерение двумерного массива представляет вектор предложения одно, а другое измерение представляет вектор предложения два

    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();



    int[] tempArray = null;



    public CosDemo(String string1, String string2) {



        for (Character character1 : string1.toCharArray()) {

            if (vectorMap.containsKey(character1)) {

                vectorMap.get(character1)[0]++;

            } else {

                tempArray = new int[2];

                tempArray[0] = 1;

                tempArray[1] = 0;

                vectorMap.put(character1, tempArray);

            }

        }

        for (Character character2 : string2.toCharArray()) {

            if (vectorMap.containsKey(character2)) {

                vectorMap.get(character2)[1]++;

            } else {

                tempArray = new int[2];

                tempArray[0] = 0;

                tempArray[1] = 1;

                vectorMap.put(character2, tempArray);

            }

        }



        for (Map.Entry<Character, int[]> entry : vectorMap.entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()[0] +","+entry.getValue()[1]);

        }

    }

// // Находим косинус сходства

    public double sim() {

        double result = 0;

        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);

        return result;

    }



    private double sqrtMulti(Map<Character, int[]> paramMap) {

        double result = 0;

        result = squares(paramMap);

        result = Math.sqrt(result);

        return result;

    }



// // найти сумму квадратов

    private double squares(Map<Character, int[]> paramMap) {

        double result1 = 0;

        double result2 = 0;

        Set<Character> keySet = paramMap.keySet();

        for (Character character : keySet) {

            int temp[] = paramMap.get(character);

            result1 += (temp[0] * temp[0]);

            result2 += (temp[1] * temp[1]);

        }

        return result1 * result2;

    }



// // умножение точек

    private double pointMulti(Map<Character, int[]> paramMap) {

        double result = 0;

        Set<Character> keySet = paramMap.keySet();

        for (Character character : keySet) {

            int temp[] = paramMap.get(character);

            result += (temp[0] * temp[1]);

        }

        return result;

    }



    public static void main(String[] args) {







        String s1 = "Я люблю площадь Тяньаньмэнь в Пекине";

        String s2 = "Я люблю есть утку по-пекински";

        // Первый шаг, предварительная обработка, главным образом состоит в том, чтобы выполнить сегментацию китайского слова и остановить слова и сегментацию слова.



        // Второй шаг - перечислить все слова.

// // Публичные слова: я люблю пекинскую площадь как жареную утку



        // Третий шаг - вычислить частоту слова и записать вектор частоты слова.

// // Вектор 1: <1,1,1,1,1,1,1,0,0,0,0,0>

// Вектор 2: <1,0,1,1,0,0,0,1,1,1,1,1>

// 3/6> cos = 3 / число корня 42> 3/7 означает, что результат находится между 3/6 и 3/7

        CosDemo similarity = new CosDemo(s1, s2);

        System.out.println(similarity.sim());

    }



}