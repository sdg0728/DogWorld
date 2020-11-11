package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import beans.purchase.GoodsDTO;
import beans.purchase.PurchaseDTO;
import command.main.Command;

public class CartCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO[] goodsArr = null;
		PurchaseDTO[] arr = null;
		String user_id = request.getParameter("user_id");
		
		try {
			arr = dao.selectCart(user_id);
			int[] goods_id = new int[arr.length];
			for (int i = 0; i < arr.length; i++) {
				goods_id[i] = arr[i].getGoods_id();
			}
			
			dao = new GoodsDAO();
			goodsArr = dao.selectGoodsByCart(goods_id);
			
			request.setAttribute("cart", arr);
			request.setAttribute("goods", goodsArr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
