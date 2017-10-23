import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTaskTest {
    @Test
    public void simpleSortColumns()  {
        int[][] matrix1 = {{9, 2, 3},
                          {8, 1, 4},
                          {7, 6 , 5}};
        int[][] expected1 = {{2, 3, 9},
                            {1, 4, 8},
                            {6 , 5, 7}};
        try {
            MatrixTask.sortColumns(matrix1);
        } catch (UnevenRowsException e) {
            assertTrue(false);
        } catch (NullRowException e) {
            assertTrue(false);
        }
        assertArrayEquals(expected1, matrix1);
        int[][] matrix2 = {{9, 2},
                           {8, 1},
                           {7, 6}};
        int[][] expected2 = {{2, 9},
                             {1, 8},
                             {6, 7}};
        try {
            MatrixTask.sortColumns(matrix2);
        } catch (UnevenRowsException e) {
            assertTrue(false);
        } catch (NullRowException e) {
            assertTrue(false);
        }
        assertArrayEquals(expected2, matrix2);
    }

    @Test
    public void UnevenRowsException()  {
        int[][] matrix = {{9, 2},
                {8, 1, 4},
                {7}};
        try {
            MatrixTask.sortColumns(matrix);
            assertTrue(false);
        } catch (UnevenRowsException e) {
            assertTrue(true);
        } catch (NullRowException e) {
            assertTrue(false);
        }
    }

    @Test
    public void NullRowException()  {
        int[][] matrix = {{9, 2, 3},
                {8, 1, 4},
                null};
        try {
            MatrixTask.sortColumns(matrix);
            assertTrue(false);
        } catch (UnevenRowsException e) {
            assertTrue(false);
        } catch (NullRowException e) {
            assertTrue(true);
        }
    }

    @Test
    public void printSpiral() throws Exception {
        int[][] matrix1 = {{9, 2, 3},
                          {8, 1, 4},
                          {7, 6 , 5}};
        String result = MatrixTask.printSpiral(matrix1);
        assert(result.charAt(0) == '1');
        for (int i = 1; i < result.length(); i++) {
            assert(result.charAt(i) - result.charAt(i - 1) == 1);
        }

        int[][] matrix2 = {{1}};
        result = MatrixTask.printSpiral(matrix1);
        assert(result.charAt(0) == '1');
    }

}