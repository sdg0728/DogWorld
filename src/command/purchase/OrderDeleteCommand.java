package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class OrderDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		System.out.println(purchase_id + " " + goods_id + " " + count);
		
		try {
			cnt = dao.deletePurchase(purchase_id, goods_id, count);
			request.setAttribute("orderDel", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
