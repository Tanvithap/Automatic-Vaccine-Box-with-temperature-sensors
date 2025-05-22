//package Logic;
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.HttpURLConnection;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URL;
//import java.net.URLConnection;
//import java.security.NoSuchAlgorithmException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author sumit
// */
//public class listener extends Thread{
//    
//    int port=0;
//    String user="";
//    public listener(){
//    
//    start();
//    }
//    
//    public void run(){
//        try {
//            ServerSocket ss=new ServerSocket();
//            while(true)
//            {
//                Socket soc=ss.accept();
//                DataInputStream oin=new DataInputStream(soc.getInputStream());
//                String task=oin.readUTF();
//                System.out.println(task);
//                if(task.equals("save"))
//                {
//                String data=oin.readUTF();
//                oin.close();
//                soc.close();
//                add_data(data,info.storage_fname);
//                
//                }
//                
//                else if(task.equals("get_data"))
//                {
//               // String usn=oin.readUTF();
//                    String file_data="";
//                    
//                         try {
//                                FileReader reader = new FileReader(info.storage_fname);
//                              int character;
//
//                              while ((character = reader.read()) != -1) {
//                                  file_data+=(char) character;
//                              }
//                              reader.close();
//             
//            
//                              
//                              
//                              
//                              
//                              
//                             Socket soc1 =new Socket(info.server_ip,info.server_port);
//                            DataOutputStream outs=new DataOutputStream(soc1.getOutputStream());
//                            outs.writeUTF(info.node_name);
//                             System.out.println("sending data to server");
//                             System.out.println(file_data);
//                            outs.writeUTF(file_data);
//
//                            outs.close();
//                            soc1.close();
//            
//                        
//                        
//                        
//                        
//                        } catch (Exception ex) {
//                            Logger.getLogger(listener.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    
//                }
//                if(task.equals("delete"))
//                {
//                 File f=new File(info.storage_fname);
//                 FileWriter fw=new FileWriter(f);
//                 fw.write("");
//                 fw.close();
//                 System.out.println("Data Discarded");
//            
//                
//                
//                }
//               
//                
//           
//                
//                
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(listener.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    
//    
//    }
//     public static void add_data(String data,String file)
//            
//    {
//    FileInputStream fin=null;
//        try {
//            String odata="";
//           BufferedReader br = null;
//		FileReader fr = null;
//boolean flag=false;
//		try {
//
//			//br = new BufferedReader(new FileReader(FILENAME));
//			fr = new FileReader(file);
//			//fr = new FileReader("/home/pi/Desktop/code/data.txt");
//			br = new BufferedReader(fr);
//
//			String sCurrentLine;
//
//			while ((sCurrentLine = br.readLine()) != null) {
//				System.out.println(sCurrentLine);
//                                
//                               
//                                odata+=sCurrentLine+"\n";
//			}
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				if (br != null)
//					br.close();
//
//				if (fr != null)
//					fr.close();
//
//			} catch (IOException ex) {
//
//				ex.printStackTrace();
//
//			}
//
//		}
//                System.out.println("odata="+odata);
//                
//               
//                
//                odata+=data;
//                
//                System.out.println("...odata="+odata);
//               File f=new File(file);
//               // File f=new File("/home/pi/Desktop/code/data.txt");
//                FileOutputStream fout=new FileOutputStream(f);
//                fout.write(odata.getBytes());
//                fout.close();
//            
//        } catch (Exception ex) {
//          
//        } 
//    }
//    public static void main(String[] args) {
//        new listener();
//    }
////}
