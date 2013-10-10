import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.gargoylesoftware.htmlunit.BrowserVersion;

url="http://localhost:8080/app/add"

description 'User can add the rest of the references with basic fields'

scenario "user can add booklet reference", {
    given 'booklet is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("booklet"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("key"));
        element.sendKeys("TT");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add conference reference", {
    given 'conference is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("conference"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("booktitle"));
        element.sendKeys("Otavan tähdet");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1987");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add inbook reference", {
    given 'inbook is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("inbook"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("chapter"));
        element.sendKeys("Chapter 10");
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

scenario "user can add incollection reference", {
    given 'incollection is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("incollection"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("booktitle"));
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

scenario "user can add manual reference", {
    given 'manual is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("manual"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("key"));
        element.sendKeys("ERE23");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add mastersthesis reference", {
    given 'mastersthesis is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("mastersthesis"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("school"));
        element.sendKeys("Helsingin Yliopisto");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1987");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add phdthesis reference", {
    given 'phdthesis is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("phdthesis"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Don Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("school"));
        element.sendKeys("Helsingin Yliopisto");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1924");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add proceedings reference", {
    given 'proceedings is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("proceedings"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("title"));
        element.sendKeys("Life");
        element = driver.findElement(By.name("year"));
        element.sendKeys("1924");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add techreport reference", {
    given 'techreport is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("techreport"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Don Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("institution"));
        element.sendKeys("Helsingin Yliopisto");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}

scenario "user can add unpublished reference", {
    given 'unpublished is selected', {
        driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER_9);
        driver.setJavascriptEnabled(true);
        driver.get(url);
        Select select = new Select(driver.findElement(By.name("entryType")));
        select.selectByVisibleText("unpublished"); 
    }

    when 'required fields are given with valid values', {
        element = driver.findElement(By.name("author"));
        element.sendKeys("Bob Benson");
        element = driver.findElement(By.name("title"));
        element.sendKeys("Bob's Life 3");
        element = driver.findElement(By.name("note"));
        element.sendKeys("Otava");
        element.submit();
    }
 
    then 'reference will be added in to system', {
        driver.getPageSource().contains("added successfully!").shouldBe true
    }
}
