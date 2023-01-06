import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class Post extends HttpServlet {

    PostBook postBook = new PostBook();

    public void init(ServletConfig config) {
        postBook.file();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String text1 = request.getParameter("text1");
        String text2 = request.getParameter("text2");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");


        HttpSession session = request.getSession();
        String author = (String) session.getAttribute("name");
        Date date = new Date();

        // Вывод текущей даты и времени с использованием toString()
//        System.out.println(date.toString());
//        System.out.println(text1 + " " + text2 +" "+ author +" " + date.toString());

        postBook.add(text1,"<h2>" + text2 + "</h2>", "<h3>" + author, date.toString() + "</h3>");
        response.sendRedirect("/HttpSessionCSS/ProfileServlet");

        out.println("</html></body>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String name = request.getParameter("name");
        String age = request.getParameter("age");

        try {
            writer.println("<form action=LoginServlet method=get>");
            writer.println("Name:<input type=text name=name><br>");
            writer.println("Password:<input type=\"password\" name=\"password\"><br>");
            writer.println("<input type=\"submit\" value=\"login\">\n" +
                    "</form>");
            writer.println("<h2>Name: " + name + "; Age: " + age + "</h2>");
        } finally {
            writer.close();
        }
    }



}