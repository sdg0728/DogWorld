package command.myPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import command.main.Command;

public class BreedInfoAjaxCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
		
		responesJSON(request, response);

	}

	private void responesJSON(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList arr = (ArrayList)request.getAttribute("data");
		
		JSONObject jsonOutput = new JSONObject();  // 최종 결과는 object
		
		int count = (arr== null) ? 0 : arr.size();
		jsonOutput.put("count", count);
			
		JSONArray dataArr = new JSONArray(); // array  
		for(int i = 0; i < count; i++) {
			JSONObject dataObj = new JSONObject();			
			// array 에 추가
			
			HashMap map = (HashMap)arr.get(i);
			dataObj.put("title", map.get("title"));
			dataObj.put("link", map.get("link"));
			dataObj.put("description", map.get("description"));
			dataObj.put("thumbnail", map.get("thumbnail"));
			
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
		}
		
	}

}
