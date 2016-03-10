//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LevelMonitorTest {

	@Test
	public void isBloodPressureSafeTrueTest() {
		String BP = "120/80";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(true, lm.isBloodPressureSafe(BP));	
	}


	@Test
	public void isBloodPressureSafeFalseHighTest() {
		String BP = "145/95";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(false, lm.isBloodPressureSafe(BP));	
	}
	
	@Test
	public void isBloodPressureSafeFalseLowTest() {
		String BP = "85/55";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(false, lm.isBloodPressureSafe(BP));	
	}
	
	@Test
	public void isBloddSugarSafeTrueTest(){
		String BS = "150";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(true, lm.isBloodSugarSafe(BS));
	}
	
	@Test
	public void isBloddSugarSafeFalseHighTest(){
		String BS = "301";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(false, lm.isBloodSugarSafe(BS));
	}
	
	@Test
	public void isBloddSugarSafeFalseLowTest(){
		String BS = "69";
		LevelMonitor lm = new LevelMonitor();
		assertEquals(false, lm.isBloodSugarSafe(BS));
	}
}
