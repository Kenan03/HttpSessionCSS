

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet {

    PostBook postBook = new PostBook();

    public void init(ServletConfig config) {
        postBook.file();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head>\n" +
                " <meta charset=\"ISO-8859-1\">\n" +
                " <title>Servlet Login Example</title>\n" +
                " <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<header class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom\">\n" +
                " <a href=\"/\" class=\"d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none\">\n" +
                "  <svg class=\"bi me-2\" width=\"40\" height=\"32\" role=\"img\" aria-label=\"Bootstrap\"><use xlink:href=\"#bootstrap\"></use></svg>\n" +
                " </a>\n" +
                "\n" +
                "\n" +
                " <ul class=\"nav col-12 col-md-auto mb-2 justify-content-center mb-md-0\">\n" +
                "  <h1 align=\"center\">Home</h1>\n" +
                " </ul>\n" +
                "\n" +
                " <div class=\"col-md-3 text-end\">\n" +
                "  <button type=\"button\" class=\"btn btn-outline-primary me-2\"><a href=\"login.html\">Login</a></button>\n" +
                "  <button type=\"button\" class=\"btn btn-outline-primary me-2\"><a href=\"signup.html\">Sign-up</a></button>\n" +
                " </div>\n" +
                "</header>");

        out.println(postBook.get());
//        HttpSession session = request.getSession(false);
//        if(session != null){
//            postBook.file();
//            request.getRequestDispatcher("link.html").include(request, response);
//            String name = (String)session.getAttribute("name");
//            out.print("Hello, " + name + " Welcome to Profile");
//            out.print(postBook.get());
//        }
//        else{
//            out.print("Please login first");
//            request.getRequestDispatcher("login.html").include(request, response);
//        }

        out.println("</html></body>");
        out.close();
    }
}