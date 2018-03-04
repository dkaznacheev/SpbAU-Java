package ru.spbau.matrixtask;

public class Main {
    public static void main(String[] args) {
        int matrix[][] = {{9, 2, 3}, {8, 1, 4}, {7, 6 , 5}};
        System.out.println(MatrixTask.printSpiral(matrix));
        int matrix2[][] = {{1}};
        System.out.println(MatrixTask.printSpiral(matrix2));
    }
}
