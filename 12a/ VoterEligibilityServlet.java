import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VoterEligibilityServlet")
public class VoterEligibilityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve and validate input parameters
            String name = request.getParameter("name");
            String ageStr = request.getParameter("age");

            if (name == null || name.isEmpty()) {
                out.println("<h3>Error: Name cannot be empty.</h3>");
                return;
            }

            if (ageStr == null || ageStr.isEmpty()) {
                out.println("<h3>Error: Age cannot be empty.</h3>");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                out.println("<h3>Error: Please enter a valid numeric age.</h3>");
                return;
            }

            // Check voter eligibility based on age
            if (age >= 18) {
                out.println("<h3>Hello " + name + ", you are eligible to vote!</h3>");
            } else {
                out.println("<h3>Hello " + name + ", you are not eligible to vote yet.</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}