

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminPage extends HttpServlet {

    Notebook notebook = new Notebook();

    public void init(ServletConfig config) {
        notebook.file();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><style></style></head><body>");

        notebook.reset();
        notebook.file();
        HttpSession session = request.getSession(false);
        if(session != null){
            request.getRequestDispatcher("link.html").include(request, response);
            String name = (String)session.getAttribute("name");
            out.print("Hello, " + name );
            out.print(notebook.getNamesStrings());
        }
        else{
            out.print("Please login first");
            request.getRequestDispatcher("login.html").include(request, response);
        }

        out.println("</html></body>");
        out.close();
    }
}