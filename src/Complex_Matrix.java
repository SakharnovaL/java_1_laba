import java.util.InputMismatchException;
import java.util.Scanner;

class Complex_Matrix{
    static Scanner in = new Scanner(System.in);
    int columns;                                    //строки
    int rows;                                       //столбцы
    String operation;                               //операция
    double[][][] matrix;                              //матрица

    Complex_Matrix(int n, int m, String operation){
        columns = n;
        rows = m;
        this.operation = operation;
        this.matrix = new double[columns][rows][2];
    }

    Complex_Matrix(){
        this(0, 0, "determinate");
    }

    Complex_Matrix(int n, int m){
        this(n, m , "determinate");
    }

    Complex_Matrix(String operation){
        this(0, 0, operation);
    }

    void create(){                                                                      //заполнение матрицы данными введенными с клавиатуры
        this.matrix = new double[columns][rows][2];
        System.out.println();
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                System.out.printf("введите значение для ячейки [%d][%d] ", i, j);
                String numm = in.nextLine();
                Complex_Num input_num = new Complex_Num();
                input_num.num = numm;
                double[] parts = input_num.get_res();
                matrix[i][j][0] = parts[0];
                matrix[i][j][1] = parts[1];
            }
        }
    }

    static Complex_Matrix read(Scanner in){
        int n;
        int m;
        try{
            System.out.print("введите целое число количества строк: ");
            n = in.nextInt();
            System.out.print("введите целое число количества столбцов: ");
            m = in.nextInt();
            in.nextLine();
        } catch(InputMismatchException e){
            System.out.println("ошибка: должно быть введено целое число");
            in.nextLine();
            return null;
        }

        Complex_Matrix a = new Complex_Matrix(n, m);
        a.create();
        return a;
    }

    void print(){                                                                       //вывод матрицы на экран
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                double real_part = matrix[i][j][0];
                double mnim_part = matrix[i][j][1];
                if(mnim_part >= 0){
                    System.out.printf("%.2f + %.2fi ", real_part, mnim_part);
                }
                else{
                    System.out.printf("%.2f - %.2fi ", real_part, mnim_part * (-1));
                }
            }
            System.out.println();
        }
    }

    Complex_Matrix sum_matrix(Complex_Matrix matrix_2){
        if(this.columns != matrix_2.columns || this.rows != matrix_2.rows){
            System.out.printf("нельзя сложить матрицы, так как их размерность не совпадает");
            return null;
        }

        int n = this.columns;
        int m = this.rows;
        Complex_Matrix res = new Complex_Matrix(n, m);
        double[][][] m1 = this.matrix;
        double[][][] m2 = matrix_2.matrix;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                res.matrix[i][j][0] = m1[i][j][0] + m2[i][j][0];
                res.matrix[i][j][1] = m1[i][j][1] + m2[i][j][1];
            }
        }
        return res;
    }

    Complex_Matrix multiplication_matrix(Complex_Matrix matrix_2){
        if(this.columns != matrix_2.rows){
            System.out.println("нельзя сделать умножение, так как количество строк первой матрицы не совпадает с количеством" +
                    " столбцов второй матрицы");
            return null;
        }
        int n = this.columns;
        int m = matrix_2.rows;
        Complex_Matrix res = new Complex_Matrix(n, m);
        double[][][] m1 = this.matrix;
        double[][][] m2 = matrix_2.matrix;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                res.matrix[i][j][0] = 0;
                for(int k = 0; k < n; k++){
                    //System.out.printf("i = % d, j = %d, k = %d\n", i, j, k);
                    res.matrix[i][j][0] += this.matrix[i][k][0] * matrix_2.matrix[k][j][0] - this.matrix[i][k][1] * matrix_2.matrix[k][j][1];
                    res.matrix[i][j][1] += this.matrix[i][k][0] * matrix_2.matrix[k][j][1] + matrix_2.matrix[k][j][0] * this.matrix[i][k][1];
                }
            }
        }
        return res;
    }

    double[] determinate(){
        if(this.rows != this.columns){
            System.out.println("матрица не квадратная, детерминант нельзя посчитать");
            return null;
        }
        double[] det = {0, 0};
        int n = this.rows;
        if(n == 1){
            return new double[]{this.matrix[0][0][0], this.matrix[0][0][1]};
        }
        if(n == 2){
            double des_part = this.matrix[0][0][0] * this.matrix[1][1][0] - this.matrix[0][0][1] * this.matrix[1][1][1] - this.matrix[0][1][0] * this.matrix[1][0][0] + this.matrix[0][1][1] * this.matrix[1][0][1];
            double mni_part = this.matrix[0][0][0] * this.matrix[1][1][1] + this.matrix[0][0][1] * this.matrix[1][1][0] - this.matrix[0][1][0] * this.matrix[1][0][1] - this.matrix[1][0][0] * this.matrix[0][1][1];
            return new double[]{des_part, mni_part};
        }
        for(int j = 0; j < n; j++){
            Complex_Matrix minor = new Complex_Matrix(n - 1, n - 1);
            int row_minor = 0;
            for(int r = 1; r < n; r++){
                int col_minor = 0;
                for(int c = 0; c < n; c++){
                    if(j == c){
                        continue;
                    }
                    minor.matrix[row_minor][col_minor][0] = this.matrix[r][c][0];
                    minor.matrix[row_minor][col_minor][1] = this.matrix[r][c][1];
                    col_minor += 1;
                }
                row_minor += 1;
            }
            double[] minor_det = minor.determinate();
            double a0 = this.matrix[0][j][0];
            double a1 = this.matrix[0][j][1];
            double p0 = a0 * minor_det[0] - a1 * minor_det[1];
            double p1 = a0 * minor_det[1] + a1 * minor_det[0];

            if(j % 2 == 0){
                det[0] += p0;
                det[1] += p1;
            }
            else{
                det[0] -= p0;
                det[1] -= p1;
            }
        }
        return det;
    }

    void print_determinate(){
        double[] det = this.determinate();
        if(det == null){
            System.out.println("детерминант не вычеслен");
            return;
        }
        if(det[1] >= 0){
            System.out.printf("det = %.2f + %.2fi", det[0], det[1]);
        }
        else{
            System.out.printf("det = %.2f - %.2fi", det[0], det[1]);
        }
    }

    Complex_Matrix transponirovanie(){
        int n = this.columns;
        int m = this.rows;
        Complex_Matrix transport_matrix = new Complex_Matrix(m, n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                transport_matrix.matrix[j][i] = this.matrix[i][j];
            }
        }
        return transport_matrix;
    }

    Complex_Matrix inverse_matrix() {
        if(this.columns != this.rows){
            System.out.println("нельзя найти обратную матрицу, матрица не квадратная");
            return null;
        }

        double[] det = this.determinate();
        if(det == null){
            return null;
        }
        if(det[0] == 0 && det[1] == 0){
            System.out.println("нельзя найти обратную матрицу, определитель равен нулю");
            return null;
        }

        int n = this.columns;
        Complex_Matrix res = new Complex_Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Complex_Matrix minor = new Complex_Matrix(n - 1, n - 1);
                int rm = 0;
                for (int r = 0; r < n; r++) {
                    if (r == j) continue;
                    int cm = 0;
                    for (int c = 0; c < n; c++) {
                        if (c == i) continue;
                        minor.matrix[rm][cm][0] = this.matrix[r][c][0];
                        minor.matrix[rm][cm][1] = this.matrix[r][c][1];
                        cm++;
                    }
                    rm++;
                }

                double[] minorDet;
                if (n == 2) {
                    minorDet = new double[]{ minor.matrix[0][0][0], minor.matrix[0][0][1] };
                } else {
                    minorDet = minor.determinate();
                }

                int sign = 0;
                if((i + j) % 2 == 0){
                    sign = 1;
                }
                else{
                    sign = -1;
                }
                double a0 = sign * minorDet[0];
                double a1 = sign * minorDet[1];

                double c0 = det[0], c1 = det[1];
                double den = c0 * c0 + c1 * c1;

                double r0 = (a0 * c0 + a1 * c1) / den;
                double r1 = (a1 * c0 - a0 * c1) / den;

                res.matrix[i][j][0] = r0;
                res.matrix[i][j][1] = r1;
            }
        }
        return res;
    }

    Complex_Matrix del_matrix(Complex_Matrix matrix_1){
        if(this.columns != this.rows && matrix_1.columns != matrix_1.rows){
            System.out.println("матрицы не квадратные, нельзя выполнить деление");
            return null;
        }

        if(this.rows != matrix_1.columns){
            System.out.println("нельзя поделить матрицы, так как у них не одинаковая размерность");
            return null;
        }

        Complex_Matrix inverse_matrix_1 = matrix_1.inverse_matrix();
        if(inverse_matrix_1 == null){
            System.out.println("нельзя выполнить деление, та как нельзя посчитать обратную матрицу для второй матрицы");
            return null;
        }
        return this.multiplication_matrix(inverse_matrix_1);
    }
}