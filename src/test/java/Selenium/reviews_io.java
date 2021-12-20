package Selenium;// Generated by Selenium IDE
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import javax.lang.model.element.Element;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class reviews_io {


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

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String URL1 = "https://www.reviews.io/company-reviews/store/natwest";
        String URL = "https://www.reviews.io/company-reviews/store/under-u";
        String URL2 = "https://www.reviews.io/company-reviews/store/webuy-com";

       driver.get(URL2);
       driver.manage().window().maximize();
        int disable = driver.findElements(By.xpath("//span[text()='»']/..")).size();
        StringBuilder fileContent = new StringBuilder();
        fileContent = fileContent.append("AUTHOR,"+"COMMENT,"+"RATING,"+"TIME").append("\r\n");
        while (disable == 0) {
            List<WebElement> reviews = driver.findElements(By.xpath("//div[@class=\"Review \"]"));
            String reviewId, author, timeRange,reviewComment, reviewIdXPath;
            int rating;
            for (int i=0; i<reviews.size(); i++){
                reviewId=reviews.get(i).getAttribute("data-review-id");
                reviewIdXPath= "//div[@class=\"Review \" and @data-review-id="+reviewId+"]";
                author= driver.findElement(By.xpath(reviewIdXPath+"//a[@class=\"Review__author\"]")).getText();
                timeRange=driver.findElement(By.xpath(reviewIdXPath+"/div[@class=\"Review__content\"]//div[@class=\"Review__dateSource\"]")).getText();
                reviewComment=driver.findElement(By.xpath(reviewIdXPath+"/div[@class=\"Review__content\"]//span[@class=\"Review__body u-wordBreak--wordBreak\"]")).getText().replaceAll("\"","");
                rating= driver.findElements(By.xpath(reviewIdXPath+"//i[@class=\"stars__icon  icon-full-star-01\"]")).size();
                fileContent= fileContent.append("\""+author+"\""+","+"\""+reviewComment+"\""+","+"\""+rating+"\""+","+"\""+timeRange+"\"").append("\r\n");
                System.out.println(author);
            }
            if(driver.findElements(By.xpath("//span[text()='»']/..")).size()==0)
                    driver.findElement(By.linkText("»")).click();
            disable = driver.findElements(By.xpath("//span[text()='»']/..")).size();
        }

        File file = new File("reviews_ioCombined.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
        }

//        FileWriter writer = new FileWriter("reviews_io_complete.csv");
//        for (String str : fileContent) {
//            writer.write(str + System.lineSeparator());
//        }
//        writer.close();

      /*  List<String> list = new ArrayList<>();
        List<String> ratingList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();


        while (disable == 0) {
            disable = driver.findElements(By.xpath("//span[text()='»']/..")).size();
            //   List<WebElement>  reviews = driver.findElements(By.xpath("//div[@class='Review ']"));
            // List<WebElement> numberOfStars = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div[2]/div[1]/div/i/.."));
            List<WebElement> reviews = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div[3]/div/span"));
            List<WebElement> datePosted = driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div[3]/div/div[3]/div[2]"));

            List<WebElement> numberOfStars = driver.findElements(By.xpath("//div[@class='Review__overallStars__stars']//i[@class='stars__icon icon-full-star-01']"));

            for (WebElement webElement : reviews) {
                String review = webElement.getText().replaceAll(",", "");
                System.out.println(review);
                list.add(review);
                int starnumber = numberOfStars.size();
                String stars=String.valueOf(starnumber);
                ratingList.add(stars);
                System.out.println(stars);

            }


            for (WebElement webElement : datePosted) {
                String date = webElement.getText().replaceAll("Posted", "");
                System.out.println(date);
                dateList.add(date);
            }

            if (disable > 0) {
                break;
            }
            driver.findElement(By.linkText("»")).click();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FileWriter writer = new FileWriter("reviews_io.txt");
        for (String str : list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();

        FileWriter starsWriter = new FileWriter("reviews_io_stars.txt");
        for (String str : ratingList) {
            starsWriter.write(str + System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
        }
        starsWriter.close();

        FileWriter dateWriter = new FileWriter("reviews_io_date.txt");
        for (String str : dateList) {
            dateWriter.write(str + System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
        }
        dateWriter.close(); */

    }
}