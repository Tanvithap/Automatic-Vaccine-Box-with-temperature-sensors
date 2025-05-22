package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Logic.FindDistance;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery {

    public int add_user(String name, String aadhar, String dob, String mobile,String email,String city,String gender,String pass) throws ClassNotFoundException, SQLException{
        
        String qry = "insert into user_details values('" + name + "','" + aadhar + "','" + dob + "','" + mobile + "','" + email + "','" + city + "','" + gender + "','NA','NA')";
        
        Connection con = ConnectionDB.getConnection();
        Statement stat = con.createStatement();
              int rows = stat.executeUpdate(qry);
//        String q="select MAX(ch_id) from user_details";
//        ResultSet rs=stat.executeQuery(q);
//        if(rs.next())
//        {
//        rows=rs.getInt(1);
//        }
         String q1 = "insert into login values('" + mobile + "','" + pass + "','user')";
        stat.executeUpdate(q1);
              return rows;
    }
     public String user_login_check(String u,String p){
      String utype="";
        try {
            
            String q="select * from login where userid='"+u+"' and password='"+p+"' ";
            System.out.println(q);
           Connection con = ConnectionDB.getConnection();
           Statement stat = con.createStatement();
           ResultSet rs=stat.executeQuery(q);
            if(rs.next())
            {
            utype=rs.getString("utype");
            }
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utype;
    }
        public String  vc_login_check(String u,String p){
     String name="";
        try {
            
            String q="select * from vc_reg where vc_id='"+u+"' and password='"+p+"' ";
            System.out.println(q);
           Connection con = ConnectionDB.getConnection();
           Statement stat = con.createStatement();
           ResultSet rs=stat.executeQuery(q);
            if(rs.next())
            {
           name=rs.getString("vc_name");
            }
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    public String get_user_details(String u){
       String det="";
        try {
            
            String q="select * from user_details where mobile='"+u+"' ";
            System.out.println(q);
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(q);
            if(rs.next())
            {
             det=rs.getString("name")+"##"+rs.getString("aadhar")+"##"+rs.getString("dob")+"##"+rs.getString("mobile")+"##"+rs.getString("email")+"##"+rs.getString("city")+"##"+rs.getString("gender")+"##"+rs.getString("status")+"##"+rs.getString("vaccine_details");       
            
            
            }
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return det;
    }
    
    
     public ArrayList get_user_details(){
       String det="";
       ArrayList al=new ArrayList();
        try {
            
            String q="select * from user_details";
            System.out.println(q);
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(q);
            while(rs.next())
            {
             det=rs.getString("name")+"##"+rs.getString("aadhar")+"##"+rs.getString("dob")+"##"+rs.getString("mobile")+"##"+rs.getString("email")+"##"+rs.getString("city")+"##"+rs.getString("gender")+"##"+rs.getString("status")+"##"+rs.getString("vaccine_details");       
             al.add(det);
            
            }
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    
    
    public int update_status(String mob,String status)
            
    {
        int i=0;
        try {
            
            String q="update user_details set  status= '"+status+"' where mobile='"+mob+"'";
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            
            i=stat.executeUpdate(q);
            
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    } 
      
    
    
    
    
    
    
    
    public int regVcCenter(String name, String lat, String lon) throws ClassNotFoundException, SQLException{
        
        String qry1 = "select count(*) from vc_reg where vc_name = '" + name + "'";
        
        Connection con = ConnectionDB.getConnection();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(qry1);
        
        int count = 0;
        
        if(rset.next()){
            count = rset.getInt(1);
        }
        rset.close();
        
        if(count>0){
            return 0;
        }
        
        else{
            String qry2 = "insert into vc_reg(vc_name, vc_lat, vc_lon) values('" + name + "','" + lat + "','" + lon + "')";
              int rows = stat.executeUpdate(qry2);
              return rows;
        }
        
    }
    
    public ArrayList<String> getVcLoc(double lat, double lon) throws ClassNotFoundException, SQLException{
        
        String qry1 = "select count(*) from vc_reg";
        
        Connection con  = ConnectionDB.getConnection();
        Statement stat  = con.createStatement();
        ResultSet rset1 = stat.executeQuery(qry1);
        
        int count = 0;
        
        if(rset1.next()){
            count = rset1.getInt(1);
        }
        rset1.close();
        
        if(count>0){
            String qry2 = "select * from vc_reg";
            ResultSet rset2 = stat.executeQuery(qry2);
            
            ArrayList<String> arr = new ArrayList<String>();
            
            while(rset2.next()){
                
                String vcName = rset2.getString("vc_name");
                String vcLatd = rset2.getString("vc_lat");
                String vcLond = rset2.getString("vc_lon");
                
                double vcLat = Double.parseDouble(vcLatd);
                double vcLon = Double.parseDouble(vcLond);
                
                 double dist = FindDistance.distance(lat, lon, vcLat, vcLon, 'K');
                 
                  String str = vcName + ":" + dist + ":" + vcLat + ":" + vcLon;
                  arr.add(str);
            }
            rset2.close();
            
            return arr;
        }
        
        else{
            return null;
        }
    }
    
    
     public String login_check(String u,String p){
       String utype="";
        try {
            
            String q="select * from login_details where userid='"+u+"' and password='"+p+"' ";
            System.out.println(q);
            Connection con=ConnectionDB.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(q);
            if(rs.next())
            {
             utype=rs.getString("utype");
            }
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utype;
    }
     
     
     public int add_vaccine_details(String userid, String vaccine_center_name, String vdate) throws ClassNotFoundException, SQLException{
        
        
        Connection con = ConnectionDB.getConnection();
        Statement stat = con.createStatement();
       
        int rid=0;
        
        String qry2 = "insert into vaccine_request_details set userid='" + userid + "',vaccine_center_name='" + vaccine_center_name + "',vdate='" + vdate + "',vtime='',status='pending'";
        int rows = stat.executeUpdate(qry2);
        
        String q="select MAX(rid) from vaccine_request_details";
        ResultSet rs=stat.executeQuery(q);
        if(rs.next())
        {
        rid=rs.getInt(1);
        }
        
        return rows;
        
        
    }
 
     public String get_all_requests(){
       String det="",details="",status=""; 
       int uid=0;
        try {
            
            String q="select * from vaccine_request_details";
            System.out.println(q);
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(q);
            while(rs.next())
            {
              
             

            det=rs.getInt("rid")+"#"+rs.getString("userid")+"#"+rs.getString("vaccine_center_name")+"#"+rs.getString("vdate")+"#"+rs.getString("vtime")+"#"+rs.getString("status");
            System.out.println("det="+det);
            details=det+"@@"+details;
            }
            
            System.out.println(det);
            
            
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return details;
    }
     
     
     public ArrayList get_requests(){
       String det="";
       ArrayList al=new ArrayList();
        try {
            
            String q="select * from vaccine_request_details";
            System.out.println(q);
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(q);
            while(rs.next())
            {
            det=rs.getInt("rid")+"#"+rs.getString("userid")+"#"+rs.getString("vaccine_center_name")+"#"+rs.getString("vdate")+"#"+rs.getString("vtime")+"#"+rs.getString("status");
             al.add(det);
            
            }
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
      public int update_request_status(String rid,String time)
             
    {
        int i=0;
        try {
            
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            
            String q="update  vaccine_request_details set vtime='"+time+"' where  rid='"+rid+"'";
            
            stat.executeUpdate(q);
           
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
      
    public String get_vaccination_details(String userid)
             
    {
        String det="",details="";
        int i=0;
        try {
            
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            
            String q="select * from  vaccine_request_details where userid='"+userid+"'";
            
            ResultSet rs=stat.executeQuery(q);
            if(rs.next())
            {
            det=rs.getInt("rid")+"#"+rs.getString("userid")+"#"+rs.getString("vaccine_center_name")+"#"+rs.getString("vdate")+"#"+rs.getString("vtime")+"#"+rs.getString("status");
            System.out.println("det=="+det);
           // details=det+"@@"+details;
            }
           
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return det;
    }
    
    
    
     public ArrayList get_requests(String vcname,String date){
       String det="";
       ArrayList al=new ArrayList();
        try {
            
            String q="select * from vaccine_request_details inner join user_details on user_details.mobile=vaccine_request_details.userid where vaccine_request_details.vdate='"+date+"' and vaccine_request_details.vaccine_center_name='"+vcname+"'  ";
            System.out.println(q);
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            ResultSet rs=stat.executeQuery(q);
            while(rs.next())
            {
            det=rs.getInt("rid")+"#"+rs.getString("userid")+"#"+rs.getString("vaccine_center_name")+"#"+rs.getString("vdate")+"#"+rs.getString("vtime")+"#"+rs.getString("status")+"#"+rs.getString("vaccine_details");
             al.add(det);
            
            }
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    
    
    
    
    
    
    
    
      public String get_child_details(String userid)
             
    {
        String det="",details="";
        int i=0;
        try {
            
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            
            String q="select * from  child_info where ch_id='"+userid+"'";
            
            ResultSet rs=stat.executeQuery(q);
            if(rs.next())
            {
            det=rs.getString("ch_name")+"#"+rs.getString("ch_dob")+"#"+rs.getString("ch_gender")+"#"+rs.getString("ch_city");
            System.out.println("det1="+det);
           // details=det+"@@"+details;
            }
           
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return det;
    }
      
      
    public int update_vaccine_status(String mob,String status)
            
      {
        int i=0;
        try {
            
            String q="update user_details set  vaccine_details= '"+status+"' where mobile='"+mob+"'";
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
           
            i=stat.executeUpdate(q);
            
           
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    } 
      public int update_request_st(String rid,String status)
             
    {
        int i=0;
        try {
            
            Connection con = ConnectionDB.getConnection();
            Statement stat = con.createStatement();
            
            String q="update  vaccine_request_details set status='"+status+"' where  rid='"+rid+"'";
            
            stat.executeUpdate(q);
           
            
        } catch (Exception ex) {
            Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
}
