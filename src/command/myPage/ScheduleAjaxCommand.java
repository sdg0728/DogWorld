package command.myPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.myPage.ScheduleDTO;
import command.main.Command;

public class ScheduleAjaxCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
		
		responesJSON(request, response);

	}

	private void responesJSON(HttpServletRequest request, HttpServletResponse response) {
		
		ScheduleDTO [] arr = (ScheduleDTO [])request.getAttribute("data");
		JSONObject jsonOutput = new JSONObject();  // 최종 결과는 object
		
		int count = (arr== null) ? 0 : arr.length;
		jsonOutput.put("count", count);
		
		
		
		JSONArray dataArr = new JSONArray(); // array  
		for(int i = 0; i < count; i++) {
			
			JSONObject dataObj = new JSONObject();
			dataObj.put("no", arr[i].getNo());
			dataObj.put("content", arr[i].getContent());
			dataObj.put("start_date", arr[i].getStart_date());
			dataObj.put("end_date", arr[i].getEnd_date());
			dataObj.put("s_check", arr[i].getS_check());
			dataObj.put("id", arr[i].getId());
			
			// array 에 추가
			dataArr.put(dataObj);
			
		} // end for
				
		jsonOutput.put("data", dataArr);
		jsonOutput.put("status", "OK");
		
		
		try {
			String jsonString = jsonOutput.toString();  //  JSON object 를 문자열로 변환
			response.setContentType("application/json; charset=utf-8");  // MIME 설정
			response.getWriter().write(jsonString); //  response 내보내기
		} catch (IOException e) {
			e.printStackTrace();
		}  // end try
		
	}

}
