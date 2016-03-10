

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;
/**
 * Servlet implementation class GetClinic
 */
@WebServlet("/GetClinicInfo")
public class GetClinicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String clinicName = "Kansas City Clinic";
	String clinicAddress = "5100 Rockhill Rd, Kansas City, MO 64110";
	String clinicPhone = "(816) 555-1234";
	String clinicEmail = "KClinic@clinic.com";
	String clinicHours = "Monday - Friday, 8 AM - 5PM";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetClinicInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JSONObject info = buildClinicInfo(clinicName,clinicAddress,clinicPhone,clinicEmail,clinicHours);
		
		response.setContentType("application/json");     
		PrintWriter out = response.getWriter();  
		out.print(info);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	JSONObject buildClinicInfo(String clinicName,String clinicAddress,String clinicPhone,String clinicEmail,String clinicHours){
		JSONObject info = new JSONObject();		
		try {
			info.put("clinicName", clinicName);
			info.put("clinicAddress", clinicAddress);
			info.put("clinicPhone", clinicPhone);
			info.put("clinicEmail", clinicEmail);
			info.put("clinicHours", clinicHours);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
		
	}

}
