import static org.junit.Assert.assertEquals;
import org.json.*;
import org.junit.Test;

public class GetClinicInfoTest {

	@Test
	public void buildClinicInfoTest() throws JSONException {
		GetClinicInfo gci = new GetClinicInfo();
		JSONObject info = gci.buildClinicInfo("loc1", "umkc", "555-5555", "a@a.com", "7-8");
		
		assertEquals(info.get("clinicName"), "loc1");
		assertEquals(info.get("clinicAddress"), "umkc");
		assertEquals(info.get("clinicPhone"), "555-5555");
		assertEquals(info.get("clinicEmail"), "a@a.com");
		assertEquals(info.get("clinicHours"), "7-8");	
	}

}
