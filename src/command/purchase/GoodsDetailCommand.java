package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import beans.purchase.GoodsDTO;
import command.main.Command;

public class GoodsDetailCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = null;
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		
		try {
			dto = dao.selectGoods(goods_id);
			request.setAttribute("GDTO", dto);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
