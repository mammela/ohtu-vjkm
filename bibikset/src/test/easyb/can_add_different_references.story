import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can add book, article and misc references with basic fields'

scenario "user can add book reference", {
    given 'book is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("book"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Otava");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1987");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add article reference", {
    given 'article is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("article"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 4");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Otava");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1987");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}


scenario "user can add misc reference", {
    given 'misc is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("misc"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("key"));
        element.sendKeys("MM342");
        element = driver.findElement(By.name("note"));
        element.sendKeys("Demo");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user cannot add book reference", {
    given 'book is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("book"); 
    }

    when 'required fields are given with invalid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("publisher"));
        element.sendKeys("Otava");
        element = driver.findElement(By.name("year"));
        element.sendKeys("vuosiluku");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}

scenario "user cannot add article reference", {
    given 'article is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("article"); 
    }

    when 'required fields are given with invalid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 4");
        element = driver.findElement(By.name("journal"));
        element.sendKeys("Otava");
        element = driver.findElement(By.name("year"));
        element.sendKeys("vuosiluku");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}

scenario "user cannot add book reference", {
    given 'book is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("book"); 
    }

    when 'user does not fill all required input fields', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}

scenario "user cannot add article reference", {
    given 'article is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("article"); 
    }

    when 'user does not fill all required input fields', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element.submit();
    }
 
    then 'reference will not be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe false
    }
}
