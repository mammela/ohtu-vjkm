import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can get automatically generated bibtex key'

scenario "user can get automatically generated bibtex key", {
    given 'add reference is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
    }

    when 'required fields are given with valid values without bibtex key', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Anna Benson Bee");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Anna's Life");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1922");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("Reference with a key=ABB22").shouldBe true
    }
}
