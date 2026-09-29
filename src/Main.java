import java.util.Scanner; //ввод данных с клавиатуры
void main() {
    Scanner in = new Scanner(System.in);
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
                System.out.println("операция не выполнена, введите корректные данные");
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
                System.out.println("операция не выполнена, введите корректные данные");
            }
            break;
        }
        case 3: {
            System.out.print("для первой матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
                return;
            }
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
                System.out.println("операция не выполнена, введите корректные данные");
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
            System.out.print("для матрицы\n");
            Complex_Matrix a = Complex_Matrix.read(in);
            if(a == null){
                System.out.println("программа завершена");
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