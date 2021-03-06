package Selenium;// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CVS {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void example() throws IOException {
        Actions actions = new Actions(driver);
        driver.get("https://www.amazon.com/Software-Test-Automation-Mark-Fewster/product-reviews/0201331403/ref=cm_cr_getr_d_paging_btm_prev_1?ie=UTF8&reviewerType=all_reviews&pageNumber=1");
        driver.manage().window().setSize(new Dimension(1050, 660));
        String test = driver.findElement(By.xpath("//*[text()='Next page']/..")).getAttribute("class");
        System.out.println(test);
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();

        while(test.equals("a-last")) {
            List<WebElement> reviews = driver.findElements(By.xpath("//div[@class='a-row a-spacing-small review-data']"));
            for (WebElement webElement : reviews) {
                String review = webElement.getText();
                System.out.println(review);
                list.add(review);
            }

            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            List<WebElement> reviews3 = driver.findElements(By.xpath("//*[@data-hook=\"review-star-rating\"]/span"));
            for (WebElement webElement : reviews3) {
                String review = webElement.getAttribute("innerHTML");
                System.out.println(review);
                list3.add(review);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            List<WebElement> reviews4 = driver.findElements(By.xpath("//span[@class='a-size-base a-color-secondary review-date']"));
            for (WebElement webElement : reviews4) {
                String review = webElement.getAttribute("innerHTML");
                System.out.println(review);
                list4.add(review);
            }
            test = driver.findElement(By.xpath("//*[text()='Next page']/..")).getAttribute("class");
            if (test.equals("a-last")) {
                driver.findElement(By.xpath("//li/*[text()='Next page']")).click();
            }
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        List<String> list6 = new ArrayList<>();
        for(String longelement : list){
            if(longelement.length() > 100 ){
                String inputString = longelement.substring(0, 100);
                list6.add(inputString);
            }
        }

        for (int i=0;i<(list3.size()) ;i++){
            String hing = list6.get(i);
            list2.add(hing);
            String thing = list3.get(i);
            String thing2 = list4.get(i);
            list2.add(thing);
            list2.add(thing2);
        }

        for (String webElement : list2) {
            String webElement2 = webElement.replaceAll(",", "");
            list5.add(webElement2);
        }
        for(int i=0;i<list5.size();i++){
            System.out.println(list5.get(i));
        }
        File file = new File("test.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("User Review,Star Rating,Date");
        bw.newLine();
        for(int i=0;i<(list5.size()/3);i++)
        {
            bw.write(list5.get(3*i)+","+list5.get(3*i +1)+","+list5.get(3*i +2));
            bw.newLine();
        }
        bw.close();
        fw.close();
    }
}