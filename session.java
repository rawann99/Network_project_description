
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class session extends Thread {

    Socket clientSocket;

    public session(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        String path = "E:\\Directories\\";
        Scanner scanner = new Scanner(System.in);
        DataInputStream input;
        try {
            input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            output.writeUTF("Conneted");
            while (true) {
                String download_path = " ";
                String name = input.readUTF();
                String fileName = "Credentials.txt";
                File file = new File(fileName);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                int counter = 0;
                boolean flag = false;
                boolean flag2 = false;
                while ((line = br.readLine()) != null) {
                    if (line.equalsIgnoreCase(name)) {
                        output.writeUTF("Username_OK");
                        flag = true;
                        break;
                    } else {
                        counter++;
                    }
                }
                if (flag == false) {
                    output.writeUTF("login Faild please re login");
                    continue;
                }
                String pass = input.readUTF();
                int temp = 0;
                FileReader fir = new FileReader(file);
                BufferedReader bir = new BufferedReader(fir);
                String line1;
                while ((line1 = bir.readLine()) != null) {
                    if (line1.equalsIgnoreCase(pass) && temp == counter + 1) {
                        output.writeUTF("password_OK");
                        flag2 = true;
                        break;
                    } else {
                        temp++;
                    }
                }
                if (flag2 == false) {
                    output.writeUTF("login Faild please re login");
                    continue;
                }
                while (true) {

                    output.writeUTF("Please choose: ‘SHOW DIRECTORIES’ or ‘SHOW DIRECTORY’ or ‘DOWNLOAD FILE’ or ‘UPLOAD FILE’ or‘SEND FILE TO ANOTHER USER’ or ‘’ ‘QUIT’");
                    String response = input.readUTF();
                    if (response.equalsIgnoreCase("quit")) {
                        break;
                    }
                    String newpath = path + name + "\\";
                    String newpath_2 = newpath;
                    System.setProperty("user.dir", newpath);
                    System.out.println("new path" + newpath);
                    if (response.equalsIgnoreCase("SHOW DIRECTORIES")) {
                        File f = new File(System.getProperty("user.dir"));
                        String[] direc = f.list();
                        String list_string = " ";
                        for (String list : direc) {
                            list_string = list_string + list + " ";
                            System.out.println(list);
                        }
                        output.writeUTF(list_string);
                    } else if (response.equalsIgnoreCase("SHOW DIRECTORY")) {
                        String list_string = " ";
                        output.writeUTF("Please enter the desired directory");
                        String response1 = input.readUTF();
                        newpath_2 = newpath_2 + response1 + "\\";
                        download_path = newpath_2;
                        System.setProperty("user.dir", newpath_2);
                        File f = new File(System.getProperty("user.dir"));
                        String[] direc = f.list();
                        for (String list : direc) {
                            list_string = list_string + list + " ";
                            System.out.println(list);
                        }
                        output.writeUTF(list_string);
                    } else if (response.equalsIgnoreCase("DOWNLOAD FILE")) {
                        output.writeUTF("Please choose the desired files to download");
                        String response3 = input.readUTF();
                        String[] files = response3.split(",");
                        String temp_path = download_path;
                        for (int i = 0; i < files.length; i++) {
                            download_path = download_path + files[i];
                            System.out.println("path" + download_path);
                            FileInputStream fileread = new FileInputStream(download_path);
                            byte file_byte[] = new byte[20002];
                            fileread.read(file_byte, 0, file_byte.length);
                            String test = new String(file_byte);
                            System.out.println("file" + test);
                            output.writeUTF(test);
                            download_path = temp_path;
                        }
                        download_path = " ";
                    } else if (response.equalsIgnoreCase("UPLOAD FILE")) {
                        String upload_path = path + name + "\\uploads\\";
                        output.writeUTF("Please choose file to upload");
                        String choosen_file = input.readUTF();
                        upload_path = upload_path + choosen_file;
                        byte[] b = input.readUTF().getBytes();
                        FileOutputStream file_read = new FileOutputStream(upload_path);
                        file_read.write(b, 0, b.length);
                    } else if (response.equalsIgnoreCase("SEND FILE TO ANOTHER USER")) {
                        output.writeUTF("Please choose file to upload");
                        String upload_path_to_user = path + "Others" + "\\";
                        String choosen_file = input.readUTF();
                        String choosen_user = input.readUTF();
                        upload_path_to_user = upload_path_to_user + choosen_user + "\\";
                        upload_path_to_user = upload_path_to_user + choosen_file;
                        byte[] b = input.readUTF().getBytes();
                        FileOutputStream file_read = new FileOutputStream(upload_path_to_user);
                        file_read.write(b, 0, b.length);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
