package command.purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.purchase.AjaxCardRegJSON;
import command.main.Command;

public class AjaxCardRegisterCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String reqType = (String) request.getParameter("reqType");

		if (reqType == null) reqType = "json";

		switch (reqType) {
		case "json":
			responseJSON(request, response);
			break;

		default:
			responseJSON(request, response);
			break;
		}
	}

	private void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		int cnt = (int)request.getAttribute("cardReg");
		List<Integer> cntArr = new ArrayList<Integer>();
		
		AjaxCardRegJSON list = new AjaxCardRegJSON();
		
		cntArr.add(cnt);
		if(cnt == 0) {
			list.setStatus("FAIL");
		} else {
			list.setStatus("OK");
			list.setCount(1);
			list.setList(cntArr);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(list);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
