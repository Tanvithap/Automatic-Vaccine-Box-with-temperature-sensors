package Logic;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Receiver {
    
 public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(9091);
          try{
            while(true){
                try{
                    Socket socket = listener.accept();
                    socket.setKeepAlive(true);
               
                
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String status=in.readLine();
                    System.out.println("Client response: " + status);

                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                   
                  
                 
                    String ins="";
                    File file1 = new File("C:/Users/ASUS/Downloads/code/vaccine.txt"); 

                    BufferedReader br = new BufferedReader(new FileReader(file1)); 

                    String st; 
                    while ((st = br.readLine()) != null) {
                    System.out.println(st); 
                    ins=st;
                    }
                    out.write(ins);

              FileWriter fw=new FileWriter(file1);
              fw.write("");
              fw.close();
                    



                    out.flush();
                    socket.close();
                    } catch (Exception ex) {
                        Logger.getLogger(Receiver.class.getName()).log(Level.SEVERE, null, ex);
                    }  
            }
            
        } finally {
            listener.close();
        }
    }

}
