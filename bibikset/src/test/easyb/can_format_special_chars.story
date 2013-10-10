import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can get scandinavian characters formatted in Bibtex-format'

scenario "user can get Bibtex-list correctly with scandinavian characters", {
    given 'references with scandinavian characters have been added', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
        element = driver.findElement(By.name("author"));
        element.sendKeys("Pekka Päätalo");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Pekan Keittokirja");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("Uudet käytännöt");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1999");
        element = driver.findElement(By.name("key"));
        element.sendKeys("PPPPP99");
        element.submit();
    }

    when 'bibtex selected', {
        element = driver.findElement(By.linkText("Bibtex"));
        element.click();
    }
 
    then 'references in correct Bibtex-format will be shown', {
        driver.getPageSource().contains("Pekka P{\\\"a}{\\\"a}talo").shouldBe true
    }
}