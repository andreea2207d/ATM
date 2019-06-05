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
 * Servlet implementation class ATM.LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id")==null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
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
		String password = Utils.md5(request.getParameter("password"));
		String error_msg = null;
		
		if(username.equals("") || password.equals("")) {
			System.out.println("User or password invalid.");
			error_msg = "User or password invalid.";
		} else {
			try {
				String db_password = Database.getPassword(username);

				if(db_password==null) {
					error_msg = "Wrong username.";
				} else {
					if(db_password.equals(password)) {
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("id", Database.getID(username));
						
						response.sendRedirect("/ATM/account");
						return;
					} else {
						error_msg = "Wrong password.";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("msg", error_msg); 
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
		requestDispatcher.forward(request, response);
	}

}
