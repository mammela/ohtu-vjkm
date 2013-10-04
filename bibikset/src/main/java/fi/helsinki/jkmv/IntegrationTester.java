package fi.helsinki.jkmv;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.ui.Model;

public class IntegrationTester {
    public static void main(String[] args) {
        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);

        WebElement element;

        driver.get("http://t-kristiat.users.cs.helsinki/bibikset/app/add");
        
        element = driver.findElement(By.id("typeSelect"));
        
        //valitaan tyypiksi article ja ladataan kentät formille
        ((JavascriptExecutor)driver).executeScript("changeValue('article')", element);
        System.out.println( driver.getPageSource() );
        

        // asetetaan muihin kenttiin jotain
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("123");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1989");

        // lähetetään lomake
        element = driver.findElement(By.name("Add"));
        element.submit();
        System.out.println( driver.getPageSource() );
        
    }
}
