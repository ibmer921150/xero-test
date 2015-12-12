package com.xero.web.base;

/**
 * 
 * 
 * @author Yu Liu
 * 
 */

import org.openqa.selenium.By;

public class CommonCases {

	public void navigateToRepeatingTab() {

	}

	public void newRepeatingInvoice(TestBase tb, String periodUnit, Boolean timeUnite, String dueDateDay, String paidToName) {

		// click "New Repeating Invoice" button
        tb.click(By.linkText("New Repeating Invoice")); 
		
		// set repeat period
		tb.input(By.id("PeriodUnit"), periodUnit);
		if (timeUnite)
			tb.input(By.id("TimeUnit_value"), "Week(s)");

		// select invoice due date
		tb.input(By.id("DueDateDay"), dueDateDay);
  
		// input name of invoice to
		tb.input(By.xpath("//input[starts-with(@id, 'PaidToName_') and contains(@class, 'x-form-text')]"), paidToName);
		
		// input reference
		if (timeUnite)
			tb.input(By.xpath("//input[contains(@id, 'Reference') and contains(@class, 'field')]"), "[Week]");
		else
			tb.input(By.xpath("//input[contains(@id, 'Reference') and contains(@class, 'field')]"), "[Month]");

		// set invoice status
		tb.click(By.id("saveAsDraft"));

		// add item
		tb.doubleClick(By.cssSelector("div.x-grid3-cell-inner.x-grid3-col-colPriceList.x-unselectable"));
		tb.input(By.id("ext-comp-1001"), "001");

		tb.doubleClick(By.cssSelector("div.x-grid3-cell-inner.x-grid3-col-colDescription.x-unselectable"));
		tb.input(By.cssSelector("textarea.x-form-textarea.x-form-field"), "testing");
		
		tb.doubleClick(By.cssSelector("div.x-grid3-cell-inner.x-grid3-col-colQuantity.x-unselectable"));
		if (timeUnite)
			tb.input(By.cssSelector("input.x-form-text.x-form-field.x-form-num-field"), "40");
		else
			tb.input(By.cssSelector("input.x-form-text.x-form-field.x-form-num-field"), "20");
		
		tb.doubleClick(By.cssSelector("div.x-grid3-cell-inner.x-grid3-col-colUnitPrice.x-unselectable"));
		tb.input(By.xpath("//input[contains(@class, 'x-form-text x-form-field x-form-num-field') and contains(@style, 'width: 71px')]"), "25");
		
		tb.doubleClick(By.cssSelector("div.x-grid3-cell-inner.x-grid3-col-colAccount.x-unselectable"));
		
		if (timeUnite)
			tb.input(By.cssSelector("div.x-form-field-wrap.x-form-field-trigger-wrap.x-trigger-wrap-focus > input.x-form-text.x-form-field.x-form-focus"), "433 - Insurance");
		else
			tb.input(By.cssSelector("div.x-form-field-wrap.x-form-field-trigger-wrap.x-trigger-wrap-focus > input.x-form-text.x-form-field.x-form-focus"), "200 - Sales");
		tb.click(By.cssSelector("div.x-combo-list-item.x-combo-selected > div"));
		tb.click(By.xpath("(//button[@type='button'])[3]"));
		tb.wait(10);
	}

	public void navigateToRepeatingInvoice(TestBase tb) {
		tb.click(By.id("Accounts"));
		tb.click(By.linkText("Sales"));
		tb.click(By.linkText("Repeating"));
	}

	private static CommonCases inst = new CommonCases();

	private CommonCases() {
		super();
	}

	public static CommonCases getInstance() {
		return inst;
	}
}
