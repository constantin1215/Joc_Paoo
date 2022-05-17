package helperMethods;

import java.util.ArrayList;

public class Utility {
    public static int[][] arrayListToMatrix(ArrayList<Integer> list, int n, int m) {
        int[][] newMatrix = new int[n][m];

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                int index = i * n + j;
                newMatrix[i][j] = list.get(index);
            }
        }
        return newMatrix;
    }

    public static int[] matrixToArr(int[][] matrix) {
        int[] result = new int[matrix.length * matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int index = i * matrix.length + j;
                result[index] = matrix[i][j];
            }
        }
        return result;
    }
}