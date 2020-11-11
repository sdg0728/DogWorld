package command.SNS;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SNS.AjaxFollowerListJSON;
import beans.SNS.FriendsDTO;
import command.main.Command;

public class AjaxFollowerListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// parameter 받아오기,  없으면 json response (디폴트)
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
		
		// "xml" 혹은 "json" 으로 response 하기
		switch(reqType) {
		case "json":
			responseJSON2(request, response);  // Jackson 사용
			break;
		default:
			responseJSON2(request, response);  // Jackson 사용
			break;
		} // end switch
	} // end execute()


	/*
	 * Jackson-databind 사용하여 JSON response
	 */
	private void responseJSON2(HttpServletRequest request, HttpServletResponse response) {
		FriendsDTO [] dtoArr = (FriendsDTO []) request.getAttribute("list");
		
		AjaxFollowerListJSON list = new AjaxFollowerListJSON();  // response 할 Java 객체
		
		if(dtoArr == null) {
			list.setStatus("FAIL");
		} else {
			list.setStatus("OK");
			list.setCount(dtoArr.length);
			list.setList(Arrays.asList(dtoArr));
		}
		
		ObjectMapper mapper = new ObjectMapper();   // Java -> Json 매핑할 Mapper 객체
		
		try {
			String jsonString = mapper.writeValueAsString(list);
			
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(jsonString);  // response 내보내기 
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

} // end Command




















