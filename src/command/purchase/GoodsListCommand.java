package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import beans.purchase.GoodsDTO;
import command.main.Command;

public class GoodsListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO[] arr = null;
		
		try {
			arr = dao.selectGoods();
			
			request.setAttribute("GoodsList", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
