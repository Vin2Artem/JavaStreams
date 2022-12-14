import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String args[]) {

        int port = 1456;

        try {
            ServerSocket servSocket = new ServerSocket(port);
            System.out.println(time() + "Сервер запущен на порте: " + port);
            while (true) {
                System.out.println(time() + "Ждём подключения...");
                Socket localSocket = servSocket.accept();
                PrintWriter pw = new PrintWriter(localSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));

                String str;
                while ((str = br.readLine()) != null) {
                    String[] arr = str.split(" ");
                    if (arr[0].equals("exit")) {
                        localSocket.close();
                        System.out.println(time() + "Отработано");
                        break;
                    }
                    File file = new File("C://" + str + ".txt");
                    if (!file.exists()) {
                        System.out.println(time() + "Запрошено содержимое файла " + str + " (отсутствует)");
                        pw.println("Файл " + file + " не найден");
                        continue;
                    }
                    System.out.println(time() + "Запрошено содержимое файла " + str);
                    FileReader fr = new FileReader(file);
                    BufferedReader br1 = new BufferedReader(fr);
                    String line;
                    pw.println("Содержимое файла " + str + ":");
                    while ((line = br1.readLine()) != null) {
                        pw.println(line);
                    }
                    br1.close();
                    fr.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static String time() {
        return new SimpleDateFormat("[dd.MM.yyy hh:mm:ss] ").format(new Date());
    }
}