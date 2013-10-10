import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can see if entered bibtex key is not unique'

scenario "user can see if entered bibtex key is not unique", {
    given 'add reference is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
    }

    and 'reference is added', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Elvis Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Just Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1923");
        element = driver.findElement(By.name("key"));
        element.sendKeys("EB23");
        element.submit();
    }
    
    when 'same bibtex key is given by user for a new reference', {
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
	element = driver.findElement(By.name("author"));
        element.sendKeys("Elvis Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Just Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1923");
        element = driver.findElement(By.name("key"));
        element.sendKeys("EB23");
        element.submit();
    }
 
    then 'reference will not added in to system', {
        driver.getPageSource().contains("added succesfully").shouldBe false
    }
}
