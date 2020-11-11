package command.purchase;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class PurchaseCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		Integer[] pidArr = null;
		int cnt = 0;
		
		String user_id = request.getParameter("user_id");
		String name = request.getParameter("name");
		String deliver = request.getParameter("roadFullAddr");
		String phone = request.getParameter("phone");
		
		try {
			pidArr = dao.selectPurchaseId(user_id);
			System.out.println(Arrays.toString(pidArr));
			for (int i = 0; i < pidArr.length; i++) {
				dao = new GoodsDAO();
				int[] cc = dao.selectCount(pidArr[i]);
				System.out.println(Arrays.toString(cc));
				dao = new GoodsDAO();
				cnt += dao.updatePurchase(name, deliver, phone, pidArr[i], cc[1], cc[0]);
			}
			
			request.setAttribute("update_purchase", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
