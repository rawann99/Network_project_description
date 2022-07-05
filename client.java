import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class client {
    public static void main(String[] args) {
        InetAddress ip;
        try {
            String client_path = "E:\\Download_folder\\";
            List<String> users = new ArrayList<String>();
            users.add("user1");
            users.add("user2");
            users.add("user3");
            users.add("user4");
            ip = InetAddress.getByName("localhost");
            Socket clientSocket = new Socket(ip, 5000);
            Scanner scanner = new Scanner(System.in);
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            String conn = input.readUTF();
            System.out.println("server: " + conn);
            while (true) {
                String client_name = " ";
                System.out.println("server: Please choose ‘LOGIN’ or ‘QUIT’");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("quit")) {
                    clientSocket.close();
                    System.out.println("The connection is closed");
                    break;
                } else {
                    System.out.println("server: please enter UserName ");
                    String name = scanner.nextLine();
                    output.writeUTF(name);
                    String name_check = input.readUTF();
                    if (name_check.equalsIgnoreCase("Username_OK")) {
                        client_name = name;
                        System.out.println("server:  " + name_check);
                        System.out.println("server: please Enter Password");
                        String pass = scanner.nextLine();
                        output.writeUTF(pass);
                        String pass_check = input.readUTF();
                        if (pass_check.equalsIgnoreCase("password_ok")) {
                            System.out.println("server: Login successfully");
                        } else {
                            System.out.println("server: login Faild Please re login");
                            continue;
                        }
                    } else {
                        System.out.println("server: login Faild Please re login");
                        continue;
                    }

                }
                while (true) {
                    String req = input.readUTF();
                    System.out.println("server:  " + req);
                    String response = scanner.nextLine();
                    output.writeUTF(response);
                    if (response.equalsIgnoreCase("quit")) {
                        clientSocket.close();
                        System.out.println("The connection is closed");
                        break;
                    }
                    else if (response.equalsIgnoreCase("SHOW DIRECTORY")) {
                        String req1 = input.readUTF();
                        System.out.println("server:  " + req1);
                        String response1 = scanner.nextLine();
                        output.writeUTF(response1);
                        String response2 = input.readUTF();
                        System.out.println("server: The available files are:  " + response2);
                        continue;
                    } else if (response.equalsIgnoreCase("DOWNLOAD FILE")) {
                        String req2 = input.readUTF();
                        System.out.println("server:  " + req2);
                        String response3 = scanner.nextLine();                       
                        output.writeUTF(response3);
                        String[] files = response3.split(",");  
                        String path = client_path + client_name + "\\";
                        String temp_path=path;
                        for(int i=0;i<files.length;i++) 
                        {   
                        path = path +files[i]; 
                        byte[] b = input.readUTF().getBytes();
                        FileOutputStream file_read = new FileOutputStream(path);
                        file_read.write(b, 0, b.length);                     
                        path=temp_path; 
                        } 
                        System.out.println("server: The Desired Files Are downloaded"); 
                        continue;
                    } else if (response.equalsIgnoreCase("UPLOAD FILE")) {
                        String path = client_path + client_name + "\\"; 
                        System.out.println("server:  " + input.readUTF());
                        System.setProperty("user.dir", path);
                        File f = new File(System.getProperty("user.dir"));
                        String[] direc = f.list();
                        for (String list : direc) {
                            System.out.println(list);
                        }  
                        String choosen_file = scanner.nextLine();
                        output.writeUTF(choosen_file);
                        path = path + choosen_file;
                        FileInputStream fileread = new FileInputStream(path);
                        byte file_byte[] = new byte[20002];
                        fileread.read(file_byte, 0, file_byte.length);
                        String uploaded_file = new String(file_byte);
                        output.writeUTF(uploaded_file); 
                        System.out.println("server: The Desired File is uploaded");
                    }
                    else if (response.equalsIgnoreCase("SEND FILE TO ANOTHER USER")) 
                    {
                        String path = client_path + client_name + "\\";
                        System.out.println("server:  " + input.readUTF());
                        System.setProperty("user.dir", path);
                        File f = new File(System.getProperty("user.dir"));
                        String[] direc = f.list();
                        for (String list : direc) {
                            System.out.println(list);
                        }
                        String choosen_file = scanner.nextLine();  
                        output.writeUTF(choosen_file); 
                        path=path+choosen_file; 
                        System.out.println("server: please choose a user to send for ");                      
                        for (int i = 0; i < users.size(); i++) { 
                            if(!(users.get(i).equalsIgnoreCase(client_name)))
                            {
                                System.out.print(users.get(i)+" ");
                            }                                                 
                        }  
                        System.out.println();
                        String choosen_user=scanner.nextLine();  
                        output.writeUTF(choosen_user);  
                        FileInputStream fileread = new FileInputStream(path);
                        byte file_byte[] = new byte[20002];
                        fileread.read(file_byte, 0, file_byte.length);
                        String uploaded_file = new String(file_byte);
                        output.writeUTF(uploaded_file);
                        System.out.println("server: uploaded successfully");
                    } else if (response.equalsIgnoreCase("SHOW DIRECTORIES")) {
                        String response2 = input.readUTF();
                        System.out.println("server:  The available directories are:  " + response2);
                        continue;
                    }
                }

            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
