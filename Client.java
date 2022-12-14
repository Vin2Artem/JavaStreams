import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {
 
    public static void main(String args[]) throws Exception {
      
        int portNumber = 1456;
        System.out.println("Запуск клиента");
        Socket socket = new Socket("localhost", portNumber);
       
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите последовательность файлов через '/', чтобы узнать ваш пароль на сегодня. Пример: one/two/three");
        String[] arr = in.nextLine().split("/");
        for (String str : arr) {
            if (!str.equals("")) {
                pw.println(str);
            }
        }
        pw.println("exit");

        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
 
        br.close();
        pw.close();
        socket.close();
    }
}