import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can remove references'

scenario "user can trash a reference", {
    given 'references have been added', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1987");
        element.submit();
    }

    when 'list selected', {
        element = driver.findElement(By.linkText("List"));
        element.click();
    }
    
    and 'trash selected', {
        element = driver.findElement(By.linkText("trash"));
        element.click();
    }
 
    then 'a reference will be in the trashcan', {
        driver.getPageSource().contains("recover").shouldBe true
    }
}

scenario "user can recover a reference from trashcan", {
    given 'list selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        element = driver.findElement(By.linkText("List"));
        element.click();
    }
    
    and 'recover selected', {        
        element = driver.findElement(By.linkText("recover"));
        element.click();
    }
    
    then 'a reference will be out of the trashcanr', {
        driver.getPageSource().contains("No items in the trash").shouldBe true
    }
}