package ATM;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ATM.RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id")==null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
			requestDispatcher.forward(request, response);
		} else {
			response.sendRedirect("/ATM/account");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String error_msg = null;

		if(username.equals("") || password.equals("")) {
			error_msg = "Username or password invalid.";
		} else {
			if(Database.getPassword(username) != null) {
				error_msg = "Username taken.";
			} else {
				Database.addUser(username, password);
				error_msg = "Successfully registered";
			}
		}

		request.setAttribute("msg", error_msg);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
	}
}
