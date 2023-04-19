package org.example;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StepImplementation extends BaseTest {
    public static Logger logger= LogManager.getLogger(StepImplementation.class);
    private  List<String> stringList=new ArrayList<>();

    @Step("<id> li element gorunur olana kadar bekle")
    public void gorunurbekle(String id){
        WebDriverWait wait = new WebDriverWait(appiumDriver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
    @Step("<id> li elementin oldugu kontrol edilir")
    public void checkElement(String element){
        Assert.assertTrue( "Elementi gördü",appiumDriver.findElement(By.id(element)).isDisplayed());
        logger.info("Elementi gördü");
    }
    @Step("<id> id'li elemente tikla")
    public void click(String element) {
        appiumDriver.findElement(By.id(element)).click();
        logger.info(element+"tiklandi");
    }
    @Step("<xpath> xpath'li element gorunur olana kadar bekle")
    public void gorunurbekleXpath(String xpath){
        WebDriverWait wait = new WebDriverWait(appiumDriver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    @Step("<element> xpath'li elemente tikla")
    public void clickXpath(String element) {
        appiumDriver.findElement(By.xpath(element)).click();
        logger.info(element+"tiklandi");
    }
    @Step("<id> id'li elemente <text> text degerini gonder")
    public void sendKeys(String id,String text){
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info(text+"degeri gonderildi");
    }
    @Step("<xpath> li elementi al,bugun veya bugunden sonraki 7 gunden birine tikla")
    public void clickRandomByxpath(String xpath) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(xpath));
        Random rand = new Random();
        int randomNumber = rand.nextInt(8)+1;
        elements.get(randomNumber).click();

    }
    @Step("<key> id'li element <keyword> text değerini içerdiğini kontrol et")
    public void textControl(String key,String keyword) {
        assertTrue(appiumDriver.findElement(By.id(key)).getText().contains(keyword),"elementi içermiyor");
        logger.info("Elementi içeriyor");
    }
    @Step("<key> elementin text degeri bir dizine at")
    public void getTextList(String key){
        String text = appiumDriver.findElement(By.xpath(key)).getText();
        stringList.add(text);
        logger.info(text+" texti dizine atıldı");
    }
    @Step("<key> id'li elementin text degeri bir dizine at")
    public void getTextListId(String key){
        String text = appiumDriver.findElement(By.id(key)).getText();
        stringList.add(text);
        logger.info(text+" texti dizine atıldı");
    }
    @Step("string listenin iki elementinin esit oldugunu kontrol et")
    public void compareList(){
        assertEquals(stringList.get(0),stringList.get(1));
        logger.info(stringList.get(0)+stringList.get(1)+"birbirine esittir");
    }
    @Step("<second> saniye kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000*second);
    }
    @Step("<element> id'li element varsa tikla")
    public void clickAcceptbutton(String element) throws InterruptedException {
        if (appiumDriver.findElement(By.id(element)).isDisplayed()){
            appiumDriver.findElement(By.id(element)).click();
            waitForsecond(3);
        }else {
            System.out.println("Pop-up gelmedi");
        }
    }
}
