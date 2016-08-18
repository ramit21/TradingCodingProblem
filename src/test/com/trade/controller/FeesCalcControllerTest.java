package test.com.trade.controller;

import junit.framework.Assert;
import main.com.trade.controller.FeesCalcController;

import org.junit.Before;
import org.junit.Test;

public class FeesCalcControllerTest {

	private FeesCalcController controller;

	@Before
	public void setup() {
		controller = new FeesCalcController("src/resources/inputdata.txt", "src/resources/output.txt", new String[]{"ClientName","Quantity"});
	}

	@Test
	public void testReportGeneration() {
		boolean result = controller.produceTransactionFeeFile();
		Assert.assertEquals(result, true);
	}
}
