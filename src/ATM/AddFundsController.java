package ATM;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ATM.AddFundsController
 */
@WebServlet("/account/add-funds")
public class AddFundsController extends HttpServlet {
	private String action = "Add funds";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFundsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("id")==null) {
			response.sendRedirect("/ATM/login");
		} else {
			int user_id = (int)session.getAttribute("id");
			
			ArrayList<Deposit> deposits = Database.getDeposits(user_id);
			
			
			request.setAttribute("deposits", deposits);
			request.setAttribute("action", action);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/add_funds.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int depositId = Integer.parseInt(request.getParameter("deposit_id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		Database.addFunds(depositId, amount);
		response.sendRedirect("/ATM/account");
	}

}
