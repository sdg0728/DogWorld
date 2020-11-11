package command.myPage;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DietDAO;
import beans.myPage.DietDTO;
import command.main.Command;

public class DietWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		DietDAO dao = new DietDAO();
		boolean chk = false;
		
		Date today = new Date();
		SimpleDateFormat format;
		format = new SimpleDateFormat("yyyy-MM-dd");
		
		DietDTO [] arr = (DietDTO [])request.getAttribute("data");
		String regdate = "";
		for (int i = 0; i < arr.length; i++) {
			if(arr[i].getRegdate().split(" ")[0].equals(format.format(today))) {
				regdate = arr[i].getRegdate();
				chk = true;
			}else {
				chk = false;
			}
		}
		
		int petId = Integer.parseInt(request.getParameter("petId"));
		int weight = Integer.parseInt(request.getParameter("weight"));
		
		System.out.println("regdate: " + regdate);
		
		try {
			
			if(chk) {				
				cnt = dao.update(weight, petId, regdate);
			}else {
				cnt = dao.insert(weight, petId);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("wirte", cnt);
		
	}

}
