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

public class TWITTER4 {
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


        String URL1 = "https://twitter.com/search?q=hsbc%20mobile%20banking&src=typed_query&f=live";

        driver.get(URL1);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<String> list = new ArrayList<>();
        StringBuilder fileContent = new StringBuilder();
        fileContent = fileContent.append("AUTHOR,"+"DATE, "+"COMMENT,"+"REPLIES,"+"RETWEETS, "+"LIKES").append("\r\n");
        int i = 0;
        while (i < 3) {
            if (list.size() == 60) {
                break;
            }
            String reviewId, reviewIdXPath, statsXpath, tweetText, username, likes , retweets, replies, date;
            List<WebElement> reviews = driver.findElements(By.xpath("//*[@data-testid='tweet']"));

            for (int j = 0; j < reviews.size(); j++) {

                reviewId = reviews.get(j).getAttribute("aria-labelledby");
                reviewIdXPath = "//article[@data-testid=\"tweet\" and @aria-labelledby='" + reviewId + "']";
                tweetText = driver.findElement(By.xpath(reviewIdXPath + "//div[@class=\"css-901oao r-18jsvk2 r-1qd0xha r-a023e6 r-16dba41 r-rjixqe r-bcqeeo r-bnwqim r-qvutc0\" ]")).getText();
                username = driver.findElement(By.xpath(reviewIdXPath + "//span[@class= \"css-901oao css-16my406 css-bfa6kz r-poiln3 r-bcqeeo r-qvutc0\"]")).getText();
//               try{ tweetLikes = driver.findElement(By.xpath(reviewIdXPath+"//div[@class=\"css-1dbjc4n r-xoduu5 r-1udh08x\"]//span[@class=\"css-901oao css-16my406 r-poiln3 r-bcqeeo r-qvutc0\"]")).getText();}
//               catch (Exception f){
//                   tweetLikes = "0";             }
                date = driver.findElement(By.xpath(reviewIdXPath + "//a[@class = \"css-4rbku5 css-18t94o4 css-901oao r-14j79pv r-1loqt21 r-1q142lx r-1qd0xha r-a023e6 r-16dba41 r-rjixqe r-bcqeeo r-3s2u2q r-qvutc0\"]")).getAttribute("aria-label");
                statsXpath = "//div[@class=\"css-1dbjc4n r-18u37iz r-1h0z5md\"]";
                likes = driver.findElement(By.xpath(statsXpath+"//div[@data-testid=\"like\"]")).getAttribute("aria-label").replaceAll("Likes. Like", "").replaceAll("Like. Like","");;
                retweets = driver.findElement(By.xpath(statsXpath+"//div[@data-testid=\"retweet\"]")).getAttribute("aria-label").replaceAll("Retweets. Retweet", "");;
                replies = driver.findElement(By.xpath(statsXpath+"//div[@data-testid=\"reply\"]")).getAttribute("aria-label").replaceAll("Replies. Reply", "").replaceAll("Reply. Reply","");
                System.out.println(username);
                list.add(tweetText);
                System.out.println(list.size());
                fileContent= fileContent.append("\""+username+"\""+","+"\""+date+"\""+","+"\""+tweetText+"\""+","+"\""+replies+"\""+","+"\""+retweets+"\""+","+"\""+likes+"\"").append("\r\n");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

            }
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            i++;
        }

        File file = new File("TWITTER4OUTPUT.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(fileContent.toString());
        }

    }
}


