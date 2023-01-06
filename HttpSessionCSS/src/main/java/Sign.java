import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sign extends HttpServlet {

    Notebook notebook = new Notebook();

    public void init(ServletConfig config) {
        notebook.file();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");


        request.getRequestDispatcher("links.html").include(request, response);
        if(notebook.findName(name)){
            notebook.add(name, password);
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
        }
        else {
            out.print("Sorry, such username exist");
            request.getRequestDispatcher("login.html").include(request, response);
        }

//        if((notebook.find(name, password))){
//            out.print("Welcome, " + name);
//            HttpSession session = request.getSession();
//            session.setAttribute("name", name);
//        }
//        else{
//            out.print("Sorry, username or password error!");
//            request.getRequestDispatcher("login.html").include(request, response);
//        }

        out.println("</html></body>");
        out.close();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//
//        try {
//            writer.println("<form action=LoginServlet method=post>");
//            writer.println("Name:<input type=text name=name><br>");
//            writer.println("Password:<input type=\"password\" name=\"password\"><br>");
//            writer.println("<input type=\"submit\" value=\"login\">\n" +
//                    "</form>");
//            writer.println("<h2>Name: " + name + "; Age: " + age + "</h2>");
//        } finally {
//            writer.close();
//        }
//    }


}