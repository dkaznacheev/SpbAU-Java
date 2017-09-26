import java.util.Arrays;
import java.util.Comparator;


public class MatrixTask {
    /**
     * Copies and transposes the matrix to another matrix.
     * @param matrix The given matrix
     * @param dest The destination matrix
    **/
    private static void copyTransposed(int[][] matrix, int[][] dest) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dest[j][i] = matrix[i][j];
            }
        }

    }

    /**
     * Sorts matrix's columns by their first elements.
     * @param matrix The given matrix
     */
    public static void sortColumns (int[][] matrix) {
        if (matrix.length == 0)
            return;
        int[][] transposed = new int[matrix[0].length][];
        for (int i = 0; i < matrix[0].length; i++)
            transposed[i] = new int[matrix.length];
        copyTransposed(matrix, transposed);
        Arrays.sort(transposed, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        copyTransposed(transposed, matrix);
    }

    /**
     * Prints matrix's elements in a spiral, starting from the center.
     * @param matrix The given matrix
     */
    public static String printSpiral (int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        int n = matrix.length;
        int step = 1;
        int row = n / 2;
        int column = n / 2;
        builder.append(matrix[row][column]);

        while (!(column == 0 && step >= n)) {

            for (int d = 1; d <= step; d++) {
                row--;
                builder.append(matrix[row][column]);
            }

            for (int d = 1; d <= step; d++) {
                column++;
                builder.append(matrix[row][column]);
            }

            step++;

            for (int d = 1; d <= step; d++) {
                row++;
                builder.append(matrix[row][column]);
            }

            for (int d = 1; d <= step; d++) {
                column--;
                builder.append(matrix[row][column]);
            }

            step++;

        }
        while (row > 0) {
            row--;
            builder.append(matrix[row][column]);
        }
        return builder.toString();
    }
}


