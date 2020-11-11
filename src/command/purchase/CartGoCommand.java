package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class CartGoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		String user_id = request.getParameter("user_id");
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int count = Integer.parseInt(request.getParameter("cart_count"));
		
		try {
			cnt = dao.insertCart(user_id, goods_id, count);
			
			request.setAttribute("cartGo", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
