import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add inproceedings-referece with basic fields'

scenario "user can add inproceedings reference", {
    given 'add selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get("http://t-kristiat.users.cs.helsinki.fi/bibikset/app/add");
        element = driver.findElement(By.id("type"));
        ((JavascriptExecutor)driver).executeScript("changeValue('inproceedings')", element);        
    }

    when 'valid input is given', {
        element = driver.findElement(By.name("key"));
        element.sendKeys("Bob89");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1989");
        element = driver.findElement(By.name("Add"));
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("Bob's Life").shouldBe true
    }
}
