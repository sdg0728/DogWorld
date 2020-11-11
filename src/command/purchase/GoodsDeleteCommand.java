package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class GoodsDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		
		try {
			cnt = dao.deleteGoods(goods_id, request);
			request.setAttribute("deleteGoods", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
