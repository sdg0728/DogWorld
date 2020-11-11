package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class CartDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		
		try {
			cnt = dao.deleteCart(purchase_id);
			request.setAttribute("cartDelete", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
