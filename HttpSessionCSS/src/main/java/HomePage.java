

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomePage extends HttpServlet {

    PostBook postBook = new PostBook();

    public void init(ServletConfig config) {
        postBook.file();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><style></style></head><body>");


        HttpSession session = request.getSession(false);
        if(session != null){
            postBook.file();
            request.getRequestDispatcher("link.html").include(request, response);
            String name = (String)session.getAttribute("name");
            out.print("Hello, " + name + " Welcome to Profile");
            out.print(postBook.get());
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.println("</html></body>");
        out.close();
    }
}