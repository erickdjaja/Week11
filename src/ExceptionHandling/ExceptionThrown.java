// Program Java untuk mendemonstrasikan exception dilempar
// bagaimana sistem run-time mencari call stack
// untuk mencari penangan exception yang sesuai.
public class ExceptionThrown {

    // Melempar Exception (ArithmeticException)
    // Appropriate Exception handler is not found within this method
    static int divideByZero(int a, int b) {
        // Statement ini akan menyebabkan ArithmeticException (/ oleh nol)
        int i = a / b;
        return i;
    }

    // Sistem run-time mencari penangan exception yang sesuai
    // dalam method ini juga tetapi tidak bisa ditemukan.
    // Jadi melihat ke depan pada call stack
    static int computeDivision(int a, int b) {
        int res = 0;
        try {
            res = divideByZero(a, b);
        }
        // tidak sesuai dengan ArithmeticException
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException is occured");
        }
        return res;
    }

    // Dalam method ini mencari Exception handler yang sesuai
    // yaitu mencocokan blok catch.
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        try {
            int i = computeDivision(a, b);
        }
        catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
