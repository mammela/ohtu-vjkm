import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can add inproceedings-referece with basic fields'

scenario "user can add inproceedings reference", {
    given 'inproceedings is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
    }

    when 'required fields are given with valid values', {
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
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user cannot add inproceedings reference", {
    given 'inproceedings is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
    }

    when 'required fields are given with invalid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life3");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("vuosiluku");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}

scenario "user cannot add inproceedings reference", {
    given 'inproceedings is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inproceedings"); 
    }

    when 'user does not fill all required input fields', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 2");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("123");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}

