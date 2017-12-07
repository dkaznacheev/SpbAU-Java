import org.junit.Test;
import ru.spbau.matrixtask.MatrixTask;
import ru.spbau.matrixtask.NullRowException;
import ru.spbau.matrixtask.UnevenRowsException;

import static org.junit.Assert.*;

public class MatrixTaskTest {
    @Test
    public void simpleSortColumns() throws Exception {
        int[][] matrix1 = {{9, 2, 3},
                          {8, 1, 4},
                          {7, 6 , 5}};
        int[][] expected1 = {{2, 3, 9},
                            {1, 4, 8},
                            {6 , 5, 7}};
        
        MatrixTask.sortColumns(matrix1);
        assertArrayEquals(expected1, matrix1);
        int[][] matrix2 = {{9, 2},
                           {8, 1},
                           {7, 6}};
        int[][] expected2 = {{2, 9},
                             {1, 8},
                             {6, 7}};
        
        MatrixTask.sortColumns(matrix2);
        assertArrayEquals(expected2, matrix2);
    }

    @Test (expected = UnevenRowsException.class)
    public void UnevenRowsException() throws Exception  {
        int[][] matrix = {{9, 2},
                {8, 1, 4},
                {7}};

        MatrixTask.sortColumns(matrix);

    }

    @Test (expected = NullRowException.class)
    public void NullRowException() throws Exception {
        int[][] matrix = {{9, 2, 3},
                {8, 1, 4},
                null};
        MatrixTask.sortColumns(matrix);
    }

    @Test
    public void printSpiral() throws Exception {
        int[][] matrix1 = {{9, 2, 3},
                          {8, 1, 4},
                          {7, 6 , 5}};
        String result = MatrixTask.printSpiral(matrix1);
        assert(result.charAt(0) == '1');
        for (int i = 1; i < result.length(); i++) {
            assertEquals(1, result.charAt(i) - result.charAt(i - 1));
        }

        int[][] matrix2 = {{1}};
        result = MatrixTask.printSpiral(matrix2);
        assert(result.charAt(0) == '1');
    }

}