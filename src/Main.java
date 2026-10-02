import java.util.Scanner; //ввод данных с клавиатуры
void main() {
    Scanner in = new Scanner(System.in);
    String PINK   = "\u001B[35m";
    String RESET  = "\u001B[0m";
    String GREEN  = "\u001B[32m";
    System.out.println("выберете операцию:");
    System.out.println("1 - сложение двух матриц");
    System.out.println("2 - перемножение двух матриц");
    System.out.println("3 - деление двух матриц");
    System.out.println("4 - транспонирование матрицы");
    System.out.println("5 - вычисление определителя матрицы");
    System.out.print("ваш выбор: ");
    int choose = in.nextInt();
    System.out.println();

    switch (choose) {
        case 1: {
            System.out.print("для первой матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                return;
            }

            System.out.println();
            System.out.println("введенное количество строк и столбцов  для второй матрицы " + PINK + "ДОЛЖНЫ СОВПАДАТЬ" + RESET + " с количеством строк и столбцов первой матрицы,\nиначе операция " + PINK + "НЕ ВЫПОЛНИТСЯ" + RESET + ", и ваши труды будут напрасны");
            System.out.println("количество строк первой матрицы: " + GREEN + a.columns + RESET + " количество столбцов первой матрицы: " + GREEN + a.rows + RESET);
            System.out.println();

            System.out.print("для второй матрицы\n");
            Complex_Matrix b = Complex_Matrix.read(in);
            if(b == null){
                System.out.println("программа завершена");
                return;
            }
            Complex_Matrix c = a.sum_matrix(b);
            if(c != null){
                System.out.println();
                System.out.print("результат:\n");
                c.print();
            }
            else{
                System.out.println("операция не выполнена, количество столбцов и строк двух матриц " + PINK + "НЕ СОВПАДАЮТ\n" + RESET);
                System.out.println("я вас предупреждала");
            }
            break;
        }
        case 2: {
            System.out.print("для первой матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                return;
            }

            System.out.println();
            System.out.println("введенное количество столбцов для второй матрицы " + PINK + "ДОЛЖНЫ СОВПАДАТЬ" + RESET + " с количеством строк первой матрицы,\nиначе операция " + PINK + "НЕ ВЫПОЛНИТСЯ" + RESET + ", и ваши труды будут напрасны");
            System.out.println("количество строк первой матрицы: " + GREEN + a.columns + RESET);
            System.out.println();

            System.out.print("для второй матрицы\n");
            Complex_Matrix b = Complex_Matrix.read(in);
            if(b == null){
                System.out.println("программа завершена");
                return;
            }
            Complex_Matrix c = a.multiplication_matrix(b);
            if(c != null){
                System.out.println();
                System.out.print("результат:\n");
                c.print();
            }
            else{
                System.out.println("операция не выполнена, введенное количество столбцов для второй матрицы  " + PINK + "НЕ СОВПАДАЕТ\n" + RESET + " с количеством строк первой матрицы");
                System.out.println("я вас предупреждала");
            }
            break;
        }
        case 3: {
            System.out.println("для данной операции " + PINK + "ОБЕ МАТРИЦЫ" + RESET + " должны быть " + PINK + "КВАДРАТНЫЕ" + RESET + ", т.е. количество строк должно совпадать с количеством столбцов");
            System.out.println();

            System.out.print("для первой матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                return;
            }

            System.out.println();
            System.out.println("введенное количество строк и столбцов  для второй матрицы " + PINK + "ДОЛЖНЫ СОВПАДАТЬ" + RESET + " с количеством строк и столбцов первой матрицы,\nиначе операция " + PINK + "НЕ ВЫПОЛНИТСЯ" + RESET + ", и ваши труды будут напрасны");
            System.out.println("количество строк первой матрицы: " + GREEN + a.columns + RESET + " количество столбцов первой матрицы: " + GREEN + a.rows + RESET);
            System.out.println();

            System.out.print("для второй матрицы\n");
            Complex_Matrix b = Complex_Matrix.read(in);
            if(b == null){
                System.out.println("программа завершена");
                return;
            }
            Complex_Matrix c = a.del_matrix(b);
            if(c != null){
                System.out.println();
                System.out.print("результат:\n");
                c.print();
            }
            else{
                System.out.println("операция не выполнена,"  +  PINK + "внимательно" + RESET + " прочитайте условия выполнения операции");
                System.out.println("я вас предупреждала");
            }
            break;
        }
        case 4: {
            System.out.print("для матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                return;
            }
            a.transponirovanie();
            System.out.println();
            System.out.print("результат:\n");
            a.print();
            break;
        }
        case 5: {
            System.out.println("для выбранной операции матрица должна быть " + PINK + "КВАДРАТНОЙ" + RESET + ", т.е. количество строк должно совпадать с количеством столбцов\nиначе операция " + PINK + "НЕ ВЫПОЛНИТСЯ" + RESET + ", и ваши труды будут напрасны");
            System.out.println();
            System.out.print("для матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                System.out.println("я вас предупреждала");
                return;
            }
            a.determinate();
            System.out.println();
            System.out.print("результат:\n");
            a.print_determinate();
            break;
        }
    }
}