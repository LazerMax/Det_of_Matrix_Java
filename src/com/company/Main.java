package com.company;

import java.util.Scanner;

public class Main {

    //рекурсивная функция вычисления определителя матрицы
    static int getDet(int[][] source, int size) {
        //при размерности матрицы 1 и 2
        if (size == 1)
            return source[0][0];
        if (size == 2)
            return source[0][0] * source[1][1] - source[0][1] * source[1][0];
        //в иных случаях
        int det = 0;
        for (int i = 0; i < size; i++){
            int[][] minor = getMinor(source, size, 0, i);
            //получаем минор для текущего столбца
            //определяем знак множителя (от четности строки)
            int sign = -1;
            if (i % 2 == 0)
                sign = 1;
            det += sign * source[0][i] * getDet(minor, size - 1); //суммируем произведение
        }
        return det;
    }

    //получить минор, исключив из матрицы строку row и столбец col
    static int[][] getMinor(int[][] source, int size, int row, int col) {
        int[][] minor = new int[size - 1][size - 1];
        int im = 0;
        for (int i = 0; i < size; i++) {
            int jm = 0;
            if (i != row) {
                for (int j = 0; j < size; j++) {
                    if (j != col) {
                        minor[im][jm] = source[i][j];
                        jm++;
                    }
                }
                im++;
            }
        }
        return minor;
    }

    public static void main(String[] args) {
        System.out.println("Укажите размер матрицы:");
        int m;
        Scanner scan = new Scanner(System.in);
        //считываем размеры
        m = scan.nextInt();
        scan.nextLine();
        int[][] A = new int[m][m];
        //вводим массив
        System.out.println("Укажите элементы матрицы:");
        for (int i = 0; i < m; i++) {
            String values = scan.nextLine();
            String[] valuesSplit = values.split(" ");
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(valuesSplit[j]);
            }
        }
        int det = getDet(A, m); //вызов функции вычисление определителя
        System.out.print("Определитель данной матрицы = " + det);

    }
}
