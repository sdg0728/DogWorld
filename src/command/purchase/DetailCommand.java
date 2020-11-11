package command.purchase;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import beans.purchase.GoodsDTO;
import beans.purchase.ReviewDTO;
import command.main.Command;

public class DetailCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO g_dto = null;
		
		ReviewDTO[] r_dto = null;
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		
		try {
			g_dto = dao.selectGoods(goods_id);
			request.setAttribute("GDTO", g_dto);
			
			dao = new GoodsDAO();
			r_dto = dao.selectReview(goods_id);
			System.out.println("review: "+Arrays.toString(r_dto));
			request.setAttribute("RDTO", r_dto);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
