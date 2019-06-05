package ATM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ATM.PendingRequestController
 */
@WebServlet("/account/pending-request")
public class PendingRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingRequestController() {
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
			int requestId = Integer.parseInt(request.getParameter("id"));
			int type = Integer.parseInt(request.getParameter("type"));
	
			if(type == 1) {
				Request req = Database.getRequest(requestId);
				if(req.getAmount() > Database.getDepositAmount(req.getFromDepositId())) {
					//
				} else {
					Database.addFunds(req.getToDepositId(), req.getAmount());
					Database.withdrawFunds(req.getFromDepositId(), req.getAmount());
					
					Database.deleteRequest(requestId);
				}
			} else {
				Database.deleteRequest(requestId);
			}

			response.sendRedirect("/ATM/account");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
