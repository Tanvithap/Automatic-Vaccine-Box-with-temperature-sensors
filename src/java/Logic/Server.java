package Logic;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws IOException {

      
       
            send_ins si=new send_ins();
            while(true){
               try{
              
                String dht=si.execute("dht");
                System.out.println("dht "+dht);
                if(dht.length()>3)
                {
                String th[]=dht.split("#");
                
                if(Double.parseDouble(th[0])>34)
                {
                    
                   si.execute("fon");
                    si.execute("von");
                    si.execute("pon");
                       si.execute("loff");
                }
//                else if(Double.parseDouble(th[0])<=34 & Double.parseDouble(th[0])>=22 )
//                {
//                    
//                   si.execute("fon");
//                    si.execute("von");
//                    si.execute("pon");
//                     si.execute("loff");
//                }
                else{
                si.execute("foff");
                    si.execute("voff");
                    si.execute("poff");
                     si.execute("lon");
                }
                }
                
                
                
                
                
                
                
String voltage=si.execute("voltage");
                   System.out.println("voltage "+voltage);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                } catch(Exception e)
                {
                e.printStackTrace();
                }
            }
                    
        
    }

}