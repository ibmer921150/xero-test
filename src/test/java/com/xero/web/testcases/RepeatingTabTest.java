package com.xero.web.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import com.xero.web.base.CommonCases;
import com.xero.web.base.TestBase;
import com.xero.web.base.Validations;

/**
 * 
 * 
 * @author Yu Liu
 * 
 */

public class RepeatingTabTest extends TestBase {

	private String baseUrl;
	private String userName;
	private String userPassword;
	private String org;
	private int timeout = 20;

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		baseUrl = configProperties.getProperty("baseurl");
		userName = configProperties.getProperty("username");
		userPassword = configProperties.getProperty("pd");
		org = configProperties.getProperty("orgname");
		driver.get(baseUrl);
		driver.manage().window().maximize();
		login(userName, userPassword);
	}

	@Test
	public void testRepeatingTabNavigation() {
		try {
			Log.info("Login Xero Account");
			this.verifyContains(By.id("title"), org, "Failed to login");

			Log.info("Navigate to the Sales screen");
			this.click(By.id("Accounts"));
			this.click(By.linkText("Sales"));

			Log.info("Navigate to the Repeat tab");
			this.click(By.linkText("Repeating"));
			this.verifyContains(By.linkText("Repeating"), "Repeating", "Failed to nevigate to Repeat tab");
			this.verifyEquals(getColor(By.linkText("Repeating")), "rgba(0, 0, 0, 1)", "Repeating tab is not highlighted");
			this.verifyEquals(getBackgroudColor(By.id("Accounts")), "rgba(255, 255, 255, 1)", "Accounts is highlighted");

			Log.info("Navigate from 'All' tab to 'Repeating' tab");
			this.click(By.linkText("All"));
			this.verifyNotEquals(getColor(By.linkText("Repeating")), "rgba(51, 51, 51, 1)", "Repeating tab can not be navigated to All tab");
			this.click(By.linkText("Repeating"));
			this.verifyEquals(getColor(By.linkText("Repeating")), "rgba(0, 0, 0, 1)", "All tab can not be navigated to Repeating tab");
		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Test
	public void testRepeatingSaveAaDraft() {
		try {
			Log.info("Test repeating invoice save as draft functionality");
			CommonCases.getInstance().navigateToRepeatingInvoice(this);

			Log.info("Prepare test data - create 2 new repeating templates");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", false, "2", "Beijing");
			CommonCases.getInstance().newRepeatingInvoice(this, "2", true, "4", "HongKong");

			Log.info("Set any repeating invoice template to save as draft status");
			Log.info("Save as draft");
			this.click(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > input"));
			this.click(By.xpath("//span[contains(text(), 'Save as Draft')]"));
			this.doubleClick(By.xpath("//span[contains(text(), 'OK')]"));
			this.wait(timeout);
			this.verifyContains(By.id("notify01"), "1 repeating transaction saved as draft", "Fail to Save as draft");
			this.verifyContains(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(9)"), "Saved as Draft", "Failed to Save as draft 1");
		} catch (Exception ex) {
			verificationErrors.append(ex.toString());
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Test
	public void testRepeatingApprove() {
		try {
			Log.info("Test repeating invoice approve functionality");
			CommonCases.getInstance().navigateToRepeatingInvoice(this);

			Log.info("Prepare test data - create 2 new repeating templates");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", false, "2", "Beijing");
			CommonCases.getInstance().newRepeatingInvoice(this, "2", true, "4", "HongKong");

			Log.info("Set any repeating invoice template to save as draft status");
			Log.info("Approve");
			this.click(By.cssSelector("table.standard > tbody > tr:nth-of-type(2) > td:nth-of-type(1) > input"));
			this.click(By.xpath("//span[contains(text(), 'Approve')]"));
			this.doubleClick(By.xpath("//span[contains(text(), 'OK')]"));
			this.wait(timeout);
			this.verifyContains(By.id("notify01"), "1 repeating transaction approved", "Failed to Approve");
			this.verifyContains(By.cssSelector("table.standard > tbody > tr:nth-of-type(2) > td:nth-of-type(9)"), "pproved", "Failed to Approve 1");
		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Ignore
	public void testRepeatingApproveForSending() {

	}

	@Test
	public void testRepeatingDelete() {
		try {
			Log.info("Test repeating invoice delete functionality");
			CommonCases.getInstance().navigateToRepeatingInvoice(this);

			Log.info("Prepare test data - create 2 new repeating templates");
			CommonCases.getInstance().newRepeatingInvoice(this, "2", true, "4", "HongKong");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", true, "2", "Shanghai");

			Log.info("Delete");
			this.click(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > input"));
			this.doubleClick(By.xpath("//span[contains(text(), 'Delete')]"));
			this.doubleClick(By.xpath("//span[contains(text(), 'OK')]"));
			this.wait(timeout);
			this.verifyTrue(isTextPresent("1 repeating transaction deleted"), "Failed to delete repeating invoice");
		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Test
	public void testRepeatingSearch() {
		try {
			Log.info("Test repeating invoice delete functionality");
			CommonCases.getInstance().navigateToRepeatingInvoice(this);

			Log.info("Prepare test data - create 4 new repeating templates");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", false, "2", "Beijing");
			CommonCases.getInstance().newRepeatingInvoice(this, "2", true, "4", "HongKong");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", true, "3", "Harbin");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", true, "2", "Shanghai");

			Log.info("Search");
			this.doubleClick(By.xpath("//span[contains(text(), 'Search')]"));

			Log.info("Search invalid name");
			this.input(By.id("sb_txtReference"), "invalid name");
			this.click(By.id("sbSubmit_"));
			this.verifyTrue(isTextPresent("There are no items to display"), "Failed to search invalid name");

			Log.info("Search valid name");
			this.click(By.cssSelector("#sbContainer_ > div.field.cancel a"));
			this.input(By.id("sb_txtReference"), "HongKong");
			this.click(By.id("sbSubmit_"));
			this.verifyContains(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(2) > a"), "HongKong", "Failed to search valid name");

			Log.info("Search filter by date");
			this.click(By.cssSelector("#sbContainer_ > div.field.cancel a"));
			this.input(By.id("sb_drpWithin_value"), "Next invoice date");
			this.input(By.id("sb_dteStartDate"), day(7));
			this.input(By.id("sb_dteEndDate"), day(7));
			this.click(By.id("sbSubmit_"));
			Thread.sleep(3000);
			this.verifyTrue(isTextPresent("Harbin"), "Failed to search filter by date");

			Log.info("Search blank");
			this.input(By.id("sb_txtReference"), "");
			this.input(By.id("sb_drpWithin_value"), "Any date");
			this.input(By.id("sb_dteStartDate"), "");
			this.input(By.id("sb_dteEndDate"), "");
			this.click(By.id("sbSubmit_"));
			this.verifyTrue(isTextPresent("No items selected"), "Failed to search blank");

			Log.info("Clear search filters");
			this.input(By.id("sb_txtReference"), "clear testing");
			this.click(By.cssSelector("#sbContainer_ > div.field.cancel a"));
			this.verifyFalse(isTextPresent("clear testing"), "Failed to clear search");
		} catch (Exception ex) {
			verificationErrors.append(ex.toString() + "\n");
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Ignore
	public void testRepeatingCreate() {

	}

	@Ignore
	public void testRepeatingEdit() {

	}

	@Ignore
	public void testRepeatingSorting() {

	}

	@Ignore
	public void testRepeatingValidation() {
		try {
			Log.info("Email validations on Approve for sending");
			Log.info("Test repeating invoice approve functionality");
			CommonCases.getInstance().navigateToRepeatingInvoice(this);

			Log.info("Prepare test data - create 1 new repeating templates");
			CommonCases.getInstance().newRepeatingInvoice(this, "1", false, "2", "Beijing");

			Log.info("Email positive validations");
			String[] emailPositive = Validations.getInstance().map.get("emailpositive");
			for (int i = 0; i < emailPositive.length; i++) {
				this.click(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > input"));
				this.click(By.xpath("//span[contains(text(), 'Approve for Sending')]"));
				this.input(By.id("ContactEmail"), emailPositive[i]);
				this.click(By.id("popupSend"));
				verifyTrue(isTextPresent("1 invoice has been approved for sending"), "'" + emailPositive[i] + "'" + " failed positive validation");

			}

			Log.info("Email negative validations");
			this.click(By.cssSelector("table.standard > tbody > tr:nth-of-type(1) > td:nth-of-type(1) > input"));
			this.click(By.xpath("//span[contains(text(), 'Approve for Sending')]"));
			String[] emailNegative = Validations.getInstance().map.get("emailnegative");
			for (int i = 0; i < emailNegative.length; i++) {
				this.input(By.id("ContactEmail"), emailPositive[i]);
				this.click(By.id("popupSend"));
				verifyPresent(By.cssSelector("div.notify bg-red bigger"), "'" + emailNegative[i] + "'" + " failed negative validation");

			}
		} catch (Exception ex) {
			verificationErrors.append(ex.toString());
			Log.error(this.getClass().getName() + " failed", ex);
		}
	}

	@Ignore
	public void testRepeatingRoles() {

	}

	@Override
	@After
	public void tearDown() throws Exception {
		Log.info("Clear up data");
		if (!this.isTextPresent("There are no items to display")) {
			this.click(By.cssSelector("table.standard > thead > tr > td:nth-of-type(1) > input"));
			this.click(By.cssSelector("div.action-bar > div.small.thin.skip.red.button > a"));
			this.click(By.cssSelector("div.button-container > div.medium.green.button > a"));
			this.wait(timeout);
		}
		super.tearDown();
	}
}
