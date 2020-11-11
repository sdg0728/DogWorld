package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import beans.purchase.AdminPurchaseDTO;
import beans.purchase.GoodsDAO;
import beans.purchase.PurchaseDTO;
import command.main.Command;

public class GoodsOrdersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AdminPurchaseDTO[] arr = null;
		GoodsDAO dao = new GoodsDAO();
		
		try {
			arr = dao.selectAllGoods();
			System.out.println(Arrays.toString(arr));
			request.setAttribute("all-purchase-list", arr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
