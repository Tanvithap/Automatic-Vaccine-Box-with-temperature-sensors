/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import Logic.info;
import Logic.send_ins;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class update_status extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String rid=request.getParameter("rid");
            String vstatus=request.getParameter("vstatus");
            String mobile=request.getParameter("mobile");
            System.out.println("rid="+rid);
            System.out.println("vstatus="+vstatus);
            System.out.println("mobile="+mobile);
             DBQuery db=new DBQuery();
            if(vstatus.equalsIgnoreCase("") || vstatus.equalsIgnoreCase("NA"))
            {
            vstatus="Dose 1";
            db.update_vaccine_status(mobile, vstatus);
            }
            else{
            vstatus="Dose 2";
            db.update_vaccine_status(mobile, vstatus);
            }
            db.update_request_st(rid,vstatus);
            out.print("ok");
              //send_ins si=new send_ins();
              File f=new File("C:/Users/ASUS/Downloads/code/vaccine.txt");
              FileWriter fw=new FileWriter(f);
              System.out.println("count="+info.count);
            if(info.count==0)
            {
            //si.execute("dispense");
            fw.write("1");
            fw.close();
            info.count++;
            }
            if(info.count>0 & info.count<3)   
            {
          
            info.count++;
            }
            else if(info.count==3)
            {
            //si.execute("dispense");
            fw.write("1");
            fw.close();
            info.count=0;
            }
           
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
