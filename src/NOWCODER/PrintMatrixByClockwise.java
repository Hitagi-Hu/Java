package NOWCODER;

import java.util.ArrayList;
public class PrintMatrixByClockwise {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int x = 0, y = 0;
        int i,j;
        ArrayList<Integer> numbers = new ArrayList<>();
        while (x < row && y < col){ //每一圈的操作,一次循环过后减少了两行两列
            for (j = y; j < col; j++)
                numbers.add(matrix[x][j]);
            for (i = x + 1; i < row; i++)
                numbers.add(matrix[i][col - 1]);
            if (x + 1 >= row) break;
            else {
                for (j = col - 2; j >= y; j--)
                    numbers.add(matrix[row - 1][j]);
            }
            if (y + 1 >= col) break;
            else {
                for (i = row - 2; i > x; i--)
                    numbers.add(matrix[i][y]);
            }
            row--;
            col--;
            x++;
            y++;
        }
        return numbers;

    }

}