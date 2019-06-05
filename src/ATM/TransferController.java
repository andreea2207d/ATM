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
 * Servlet implementation class ATM.TransferController
 */
@WebServlet("/account/transfer")
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferController() {
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
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/transfer.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fromDepositId = Integer.parseInt(request.getParameter("deposit_id_from"));
		int toDepositId =  Integer.parseInt(request.getParameter("deposit_id_to"));		
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		if(fromDepositId == toDepositId) {
			request.setAttribute("error", "You chose the same account.");
			doGet(request, response);
		} else {
			if(amount > Database.getDepositAmount(fromDepositId)) {
				request.setAttribute("error", "You don't have enough funds.");
				doGet(request, response);
			} else {
				Database.withdrawFunds(fromDepositId, amount);
				Database.addFunds(toDepositId, amount);
				response.sendRedirect("/ATM/account");
			}
		}
	}
}
