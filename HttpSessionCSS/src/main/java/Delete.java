import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Delete extends HttpServlet {
    Notebook notebook = new Notebook();

    public void init(ServletConfig config) {
        notebook.file();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String el = request.getParameter("el");
//        String text2 = request.getParameter("text2");
        System.out.println(el);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        notebook.delete(el);
        response.sendRedirect("/HttpSessionCSS/AdminPage");
//        HttpSession session = request.getSession();
//        String author = (String) session.getAttribute("name");
//        Date date = new Date();
//
//        // Вывод текущей даты и времени с использованием toString()
////        System.out.println(date.toString());
////        System.out.println(text1 + " " + text2 +" "+ author +" " + date.toString());
//
////        postBook.add(text1, text2, author, date.toString());
//        response.sendRedirect("/HttpSessionCSS/ProfileServlet");

        out.println("</html></body>");
        out.close();
    }

}
