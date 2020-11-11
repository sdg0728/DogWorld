package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import beans.purchase.OrdersDTO;
import command.main.Command;

public class OrdersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		OrdersDTO[] arr = null;
		
		String user_id = request.getParameter("user_id");
		
		try {
			arr = dao.selectOrders(user_id);
			
			request.setAttribute("orders", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
