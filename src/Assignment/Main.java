import java.util.ArrayList;
import java.util.Scanner;

import exceptions.AuthenticationException;
import exceptions.ExcessiveFailedLoginException;
import exceptions.InvalidPropertyException;

public class Main {

    static ArrayList<User> listOfUser = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void initialize() {
        try {
            listOfUser.add(new User(
                    "John",
                    "Doe",
                    'L',
                    "Jl. Merpati No. 1 RT 1 RW 1, Banten",
                    "admin",
                    "admin"
            ));
        } catch (InvalidPropertyException e) {
            System.out.println("Initialization error: " + e.getMessage());
        }
    }

    public static void handleLogin() {
        boolean success = false;

        for (User u : listOfUser) {

            System.out.print("Username : ");
            String un = sc.nextLine();

            System.out.print("Password : ");
            String pw = sc.nextLine();

            try {
                if (u.login(un, pw)) {
                    System.out.println(u.greeting());
                    success = true;
                    break;
                }
            }
            catch (ExcessiveFailedLoginException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        if (!success) {
            try {
                throw new AuthenticationException("Login gagal.");
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void handleSignUp() {
        try {
            System.out.print("Nama Depan : ");
            String fn = sc.nextLine();

            System.out.print("Nama Belakang : ");
            String ln = sc.nextLine();

            System.out.print("Jenis Kelamin (L/P) : ");
            Character g = sc.nextLine().charAt(0);

            System.out.print("Alamat : ");
            String ad = sc.nextLine();

            System.out.print("Username : ");
            String un = sc.nextLine();

            if (un.length() <= 8) {
                System.out.println("Username harus lebih dari 8 karakter");
                return;
            }

            System.out.print("Password : ");
            String pw = sc.nextLine();

            if (!pw.matches("^(?=.*[A-Z])(?=.*[0-9]).{6,16}$")) {
                System.out.println("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
                return;
            }

            User u = new User(fn, ln, g, ad, un, pw);
            listOfUser.add(u);

            System.out.println("User telah berhasil didaftarkan");

        } catch (InvalidPropertyException e) {
            System.out.println("Input tidak valid: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        initialize();

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");

            String opt = sc.nextLine();

            if (opt.equals("1")) {
                handleLogin();
            }
            else if (opt.equals("2")) {
                handleSignUp();
            }
            else {
                System.out.println("Input tida
