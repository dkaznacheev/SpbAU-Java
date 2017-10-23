import java.util.Arrays;
import java.util.Comparator;

/**Class for simple matrix manipulations.
 * Comes with two static methods.
 */
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
    public static void sortColumns(int[][] matrix) throws UnevenRowsException, NullRowException {
        if (matrix.length == 0) {
            return;
        }
        if (hasNullRow(matrix)) {
            throw new NullRowException();
        }
        if (!equalRows(matrix)) {
            throw new UnevenRowsException();
        }
        int[][] transposed = new int[matrix[0].length][];
        for (int i = 0; i < matrix[0].length; i++) {
            transposed[i] = new int[matrix.length];
        }
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
     * Checks if matrix has null row
     * @param matrix matrix
     * @return if matrix has null row
     */
    private static boolean hasNullRow(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null)
                return true;
        }
        return false;
    }

    /**
     * Checks if the matrix has rows of equal length.
     * @param matrix given matrix
     * @return true if the matrix has rows of equal length, false otherwise
     */
    private static boolean equalRows(int[][] matrix) {
        int length = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != length)
                return false;
        }
        return true;
    }

    /**
     * Prints matrix's elements in a spiral, starting from the center.
     * @param matrix The given matrix
     */
    public static String printSpiral(int[][] matrix) {
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


