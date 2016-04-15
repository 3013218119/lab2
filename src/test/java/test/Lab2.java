package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import com.csvreader.CsvReader;

public class Lab2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String id;
  private String pwd;

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.ncfxy.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLab2() throws Exception {
	  CsvReader cr = new CsvReader("C:/Users/Administrator/Downloads/info.csv",',',Charset.forName("GBK"));
	  while(cr.readRecord()){
		  if(cr.get(0).length()>0){
			  id = cr.get(0);
			  pwd = id.substring(4,10);
			  driver.get(baseUrl);
			  driver.findElement(By.id("name")).clear();
			    driver.findElement(By.id("name")).sendKeys(id);
			    driver.findElement(By.id("pwd")).clear();
			    driver.findElement(By.id("pwd")).sendKeys(pwd);
			    new Select(driver.findElement(By.id("gender"))).selectByVisibleText("Ů");
			    driver.findElement(By.id("submit")).click();
//			    String number = tableCell(driver,2,2);
//			    String mail = tableCell(driver,1,2);
			    String number = driver.findElement(By.xpath("//tbody[@id='table-main']/tr[2]/td[2]")).getText();
			    String mail = driver.findElement(By.xpath("//tbody[@id='table-main']/tr[1]/td[2]")).getText();
			    if(cr.get(0).equals(number)){
			    	assertEquals(cr.get(1),mail);
			    }

			    
		  }
		  
	  }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
