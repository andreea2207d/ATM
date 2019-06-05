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
 * Servlet implementation class ATM.WithdrawController
 */
@WebServlet("/account/withdraw")
public class WithdrawController extends HttpServlet {
	private String action = "Withdraw";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawController() {
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
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/withdraw.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int depositId = Integer.parseInt(request.getParameter("deposit_id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		if(amount > Database.getDepositAmount(depositId)) {
			request.setAttribute("error", "You don't have enough funds.");
			doGet(request, response);
		} else {	
			Database.withdrawFunds(depositId, amount);
			response.sendRedirect("/ATM/account");
		}
	}

}
