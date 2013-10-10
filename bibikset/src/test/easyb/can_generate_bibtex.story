import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can generate a list of references in Bibtex-format'

scenario "user can generate a list in Bibtex-format", {
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

    when 'bibtex selected', {
        element = driver.findElement(By.linkText("Bibtex"));
        element.click();
    }
 
    then 'references in Bibtex-format will be shown', {
        driver.getPageSource().contains("@inproceedings{BB87,").shouldBe true
    }
}