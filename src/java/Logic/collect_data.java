/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author fgfdg
 */
public class collect_data extends Thread{
    boolean flag= false;
    String snode="";
   public static int countajp=0,countbjp=0;
    public static  String ALGO = "AES";
public static  byte[] keyValue = 
new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
String f_sem="",s_sem="",t_sem="",fo_sem="",fi_sem="",si_sem="",se_sem="",e_sem="";
    public collect_data(){
    
    start();
    
    }
    public void run(){
        try {
        ServerSocket ss=new ServerSocket(6000);
        while(true)
        {


                flag= false;
                Socket soc=ss.accept();
                DataInputStream oin=new DataInputStream(soc.getInputStream());
                String node=oin.readUTF();
                String data=oin.readUTF();
                System.out.println("*********");
                System.out.println(data);
                
                flag=verify(node,data);
                System.out.println(">>>>>>>"+flag);

                if(flag==true)
                {
                     Socket soc1 =null;
                    if(node.equalsIgnoreCase("node1"))
                    {
                    
                    }
                    else if(node.equalsIgnoreCase("node2"))
                    {
                      
                    }
                            
                            DataOutputStream outs=new DataOutputStream(soc1.getOutputStream());
                            outs.writeUTF("delete");
                            

                            outs.close();
                            soc1.close();                         

                }
                else{
//                String n1data =info.node1data;
//                String n2data =info.node2data;
//                    System.out.println("n1data="+n1data);
//                    System.out.println("n2data="+n2data);
//                    if(!n1data.equalsIgnoreCase(""))
//                    {
//                        String nd[]=n1data.split("\n");
//                        for(int i=0;i<nd.length;i++)
//                        {
//                        
//                        
//                        }
                        System.out.println("writing result");
                        File f  =new File("");
                        FileWriter fw=new FileWriter(f);
                        if(f_sem.equals("")){f_sem="No";}
                        if(s_sem.equals("")){s_sem="No";}
                        if(t_sem.equals("")){t_sem="No";}
                        if(fo_sem.equals("")){fo_sem="No";}
                        if(fi_sem.equals("")){fi_sem="No";}
                        if(si_sem.equals("")){si_sem="No";}
                        if(se_sem.equals("")){se_sem="No";}
                        if(e_sem.equals("")){e_sem="No";}
                        fw.write(f_sem+"@@"+s_sem+"@@"+t_sem+"@@"+fo_sem+"@@"+fi_sem+"@@"+si_sem+"@@"+se_sem+"@@"+e_sem);
                        fw.close();
                        
                        
                        
                   // }
                
                }
        }
        }catch(Exception e)
         {
         e.printStackTrace();
         }
    
    }
    
    public static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }
        public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
        public String get_hash_value(String value) throws NoSuchAlgorithmException{
   
   String password = value;
 
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        System.out.println("Digest(in hex format):: " + sb.toString());
 
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	System.out.println("Digest(in hex format):: " + hexString.toString());
        return hexString.toString();
   }
    public boolean verify(String node,String data)
    {
    String d[]=data.split("&&");
    String fdata="";
    for(int i=0;i<d.length;i++)
    {
        if(!d[i].equals(""))
        {
            System.out.println(d[i]);
              String d1[]=d[i].split("@@");
              byte b[]=new byte[32];
              b= d1[1].substring(0, 16).getBytes();
              keyValue=b;
        try {
            String dec_data= decrypt(d1[0]);
           
            String hash="";
            try {
                            hash = get_hash_value(dec_data);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
              System.out.println("dec_data="+dec_data);
              System.out.println("hash value="+hash);
              
              String st1="",usn=""; 
                 File file = new File(""); 
                 BufferedReader br = new BufferedReader(new FileReader(file)); 
            
            
                   
                   while ((st1 = br.readLine()) != null) 
                   {
                   System.out.println(st1);
                   usn=st1;
                  
                   }
                   System.out.println("usn "+usn);
              
              
              
              if(hash.equals(d1[1])){
              
                  System.out.println("Data is safe in node "+node);
                  String aa[]=dec_data.split("##");
                  System.out.println("aa[0]---"+aa[0]);
                  if(aa[0].equalsIgnoreCase(usn))
                  {
                      System.out.println("aa[1]---"+aa[1]);
                      if(aa[1].equalsIgnoreCase("1"))
                      {
                      f_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("2"))
                      {
                      s_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("3"))
                      {
                      t_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("4"))
                      {
                      fo_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("5"))
                      {
                      fi_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("6"))
                      {
                      si_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("7"))
                      {
                      se_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                      if(aa[1].equalsIgnoreCase("8"))
                      {
                      e_sem=aa[2]+"##"+aa[3]+"##"+aa[4]+"##"+aa[5]+"##"+aa[6]+"##"+aa[7]+"##"+aa[8]+"##"+aa[9];
                      }
                  
                  }
                  
                  
              }
              else{
              System.out.println("Data is tempered in node "+node);
              flag=true;
              snode=node;
              break;
              }
        } catch (Exception ex) {
            Logger.getLogger(collect_data.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//end for
    if(flag==false)
    {
        
        
    if(node.equalsIgnoreCase("node1"))
                  {
                      
               
                  }
    if(node.equalsIgnoreCase("node2"))
                  {
                      
                 
                  }
    }
    return flag;
    }
    public static void main(String[] args) {
        new collect_data();
    }
}
