

import Database.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class servNearestVc extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                double lat = Double.parseDouble(request.getParameter("lat"));
                System.out.println("Latitude = " + lat);
                
                double lon = Double.parseDouble(request.getParameter("lon"));
                System.out.println("Longitude = " + lon);
                
                DBQuery db = new DBQuery();
                ArrayList<String> arr = db.getVcLoc(lat, lon);
                
                if(arr.size()>0){
                    
                    String[] vccnNames = new String[arr.size()];
                    double[] distances = new double[arr.size()];
                    String[] latitudes = new String[arr.size()];
                    String[] longitudes = new String[arr.size()];
                    
                    
                    for(int i = 0; i<arr.size(); i++){
                        String str = arr.get(i);
                        
                        String[]st = str.split(":");
                        String vcn = st[0];
                        double dst = Double.parseDouble(st[1]);
                        String latd = st[2];
                        String lond = st[3];
                        
                        vccnNames[i] = vcn;
                        distances[i] = dst;
                        latitudes[i] = latd;
                        longitudes[i] = lond;
                    }
                    
                    int distLen = distances.length;
                    double tmp1 = 0;
                    String tmp2 = "";
                    String tmp3 = "";
                    String tmp4 = "";
                    
                    for(int i = 0; i<distLen-1; i++){
                        
                        for(int j = i; j<distLen; j++){
                            if(distances[j]<distances[i]){
                                tmp1 = distances[j];
                                distances[j] = distances[i];
                                distances[i] = tmp1;
                                
                                tmp2 = vccnNames[j];
                                vccnNames[j] = vccnNames[i];
                                vccnNames[i] = tmp2;
                                
                                tmp3 = latitudes[i];
                                latitudes[i] = latitudes[j];
                                latitudes[j] = tmp3;
                                
                                tmp4 = longitudes[i];
                                longitudes[i] = longitudes[j];
                                longitudes[j] = tmp3;
                                
                            }
                        }
                        
                    }
                    
                    String str = "";
                    
                    for(int i = 0; i<vccnNames.length; i++){
                        str += vccnNames[i] + ":" + distances[i] + ":" + latitudes[i] + ":" + longitudes[i] + ">";
                        
                    }
                    System.out.print("*******************"+str);
                    out.println(str);
                    
                }
                
                else{
                    out.println("No vaccination centers available");
                }
                    
        } 
        
        
        finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servNearestVc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(servNearestVc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(servNearestVc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(servNearestVc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
