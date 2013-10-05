import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can add inproceedings-referece with basic fields'

scenario "user can add inproceedings reference", {
    given 'add selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get("http://t-kristiat.users.cs.helsinki/bibikset/app/add");
        Select select = new Select(driver.findElement(By.name("type")));
        select.selectByVisibleText("Inproceeding"); 
    }

    when 'valid input is given', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1989");
        element = driver.findElement(By.name("key"));
        element.sendKeys("BB89");
        element = driver.findElement(By.name("Add"));
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("Reference with key 'BB89' successfully added!").shouldBe true
    }
}
