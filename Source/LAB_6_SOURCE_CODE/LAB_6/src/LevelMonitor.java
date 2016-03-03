

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.*;

/**
 * Servlet implementation class LevelMonitor
 */
@WebServlet("/LevelMonitor")
public class LevelMonitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LevelMonitor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String BP = request.getParameter("BP");
		String BS = request.getParameter("BS");		
		JSONObject info = new JSONObject();
				
		if(isBloodPressureSafe(BP) == false || isBloodSugarSafe(BS) == false){
			try {
				info.put("status", "danger");
			} catch (JSONException e) {e.printStackTrace();}
		}
		else{
			try {
				info.put("status", "safe");
			} catch (JSONException e) {e.printStackTrace();}
		}		
		try {
			info.put("BP", BP);
			info.put("BS", BS);
		} catch (JSONException e) {e.printStackTrace();}
		
		response.setContentType("application/json");     
		PrintWriter out1 = response.getWriter();  
		out1.print(info);
		out1.flush();

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		doGet(request, response);
	}
	
	boolean isBloodPressureSafe(String BP){		
		String[] splitBP = BP.split("/");		
		int systolic = Integer.parseInt(splitBP[0]);
		int diastolic = Integer.parseInt(splitBP[1]);		
		return !(systolic < 90 || diastolic < 60 || systolic > 140 || diastolic > 90 );
	}
	
	boolean isBloodSugarSafe(String BS){
		int bloodSugar = Integer.parseInt(BS);
		return !(bloodSugar < 70 || bloodSugar > 300);
	}

}
