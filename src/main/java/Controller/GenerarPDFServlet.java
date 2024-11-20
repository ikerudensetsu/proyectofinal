package Controller;

import Connection.Database;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenerarPDFServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GenerarPDFServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerarPDFServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //String filename = System.getProperty("user.dir") + "\\Reportes\\pedidos.pdf";
            String filename = getServletContext().getRealPath(File.separator) + "\\..\\..\\Reportes\\pedidos.pdf";
            
            Connection cn = Database.getConnection();
            String sql = "SELECT * FROM pedidos";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst())
            {
                return;
            }
            
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 16.0f,Font.BOLD);
            Paragraph para = new Paragraph("Registros de Pedidos", f1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(5);
            table.addCell(new Phrase("ID"));
            table.addCell(new Phrase("ID Usuario"));
            table.addCell(new Phrase("Total"));
            table.addCell(new Phrase("Fecha"));
            table.addCell(new Phrase("Estado"));
            table.setHeaderRows(1);
            
            while (rs.next()) {
                table.addCell(rs.getString("id"));
                table.addCell(rs.getString("id_usuario"));
                table.addCell(rs.getString("total"));
                table.addCell(rs.getString("fecha"));
                table.addCell(rs.getString("estado"));
            }

            document.add(new Paragraph(" "));

            document.add(table);
            
            document.close();
            File file = new File(filename);
            Desktop.getDesktop().open(file);
        } catch (DocumentException | HeadlessException | FileNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        request.getRequestDispatcher("pedidos.jsp").forward(request, response);
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
