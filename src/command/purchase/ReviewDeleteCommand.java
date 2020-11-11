package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class ReviewDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		int review_id = Integer.parseInt(request.getParameter("review_id"));
		
		try {
			cnt = dao.deleteReview(review_id, request);
			
			request.setAttribute("delete", cnt);
			request.setAttribute("goods_id", goods_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
