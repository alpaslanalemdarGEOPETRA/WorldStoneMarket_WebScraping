package StepDefinitions;

import Pages.DialogContent;
import Utilities.ExcelUtility;
import Utilities.GWD;
import io.cucumber.java.*;

import java.io.FileOutputStream;
import java.time.Instant;
import java.util.*;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.*;
import org.testng.annotations.*;

import java.util.List;


public class WorldStoneMarket extends GWD {
    Pages.WorldStoneMarket wsm = new Pages.WorldStoneMarket();
    private WebDriver driver;
    private XSSFWorkbook workbook;
    private Sheet sheet;
    private int rowCount = 0;

    public WorldStoneMarket() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Firm Data");
        // You may want to create header row here
    }


    @When("Collect Company names,links, and build an excell")
    public void collectCompanyNamesLinksAndBuildAnExcell() {


        GWD.getDriver().get("https://www.stonecontact.com/suppliers/stone-machinery/turkey-country");

        List<WebElement> companies = getDriver().findElements
                (By.cssSelector("[class='pname'] a"));


        //   Map<String, Map<String, String>> companyInformation=new HashMap<>();

        for (WebElement e : companies) {

            wsm.myJsClick(e);   // We go to each company profile
            /////////  wsm.wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("[class='company'] [class='name']")));

            /////////  String nameOfCompany = getDriver().findElement((By.cssSelector("[class='company'] [class='name']"))).getText();
            /////////  String descriptionOfCompany = getDriver().findElement((By.cssSelector("[class='cd-box']"))).getText();


            //  WebElement productsHeaderLink = getDriver().findElement(By.cssSelector("[title='Products']"));

            //   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Products']")));

            List<WebElement> categoriesOfFirms = null;
            for (int i = 0; i < 3; i++) {
                try {
                    categoriesOfFirms = driver.findElements(By.cssSelector("body > div.main > div.menu > ul > li:nth-child(2) > a"));
                    break;
                } catch (StaleElementReferenceException ex) {
                    System.out.println("lcoek2vcr");
                }
            }




            //  List<WebElement> categoriesOfFirms = getDriver().findElements
            //        (By.cssSelector("body > div.main > div.menu > ul > li:nth-child(2) > a"));
            //        //  (By.xpath("/html/body/div[4]/div[1]/ul/li[2]/a"));
            //         // (By.xpath("//ul[@class='tree']//li[@class='pro']//a[contains(@href,'/p/')]"));

            //  categoriesOfFirms.forEach(System.out::println);

            assert categoriesOfFirms != null;
            for (WebElement ctgLinks : categoriesOfFirms) {

                wsm.myJsClick(ctgLinks); // We go to all product lists

                List<WebElement> productEachLink = getDriver().findElements
                        (By.cssSelector("a div[class='img-wrap']"));


                for (WebElement prdlnk : productEachLink) {

                    wsm.myJsClick(prdlnk); // We go to each product page of company


                    String nameOfProduct = prdlnk.getText();

                    WebElement cat1Element = getDriver().findElement(By.xpath("(//div[@class='custom-nav']//child::a)[3]"));
                    WebElement cat2Element = getDriver().findElement(By.xpath("(//div[@class='custom-nav']//child::a)[4]"));
                    WebElement cat3Element = getDriver().findElement(By.xpath("(//div[@class='custom-nav']//child::a)[5]"));

                    String categories = getCategoryInfo(cat1Element, cat2Element, cat3Element);

                    String descriptionOfProduct = getDriver().findElement(By.xpath("//div[@class='detail-content']")).getText();

                    List<WebElement> picturesOfProducts = getDriver().findElements
                            (By.xpath("//div[@class='bannerwarp']//li[starts-with(@style, 'width:600px')]"));


                    getDriver().navigate().back();
                }


            }
            getDriver().navigate().back();
        }
        //   companyInformation.put(e.getText(), )


        // ExcelUtility.writeToExcel("src/test/java/WorldStoneMarketWebScraping/WSM_YeniExcel.xlsx", Scenario "Collect company information" );


    }

    public String getCategoryInfo(WebElement cat1Element, WebElement cat2Element, WebElement cat3Element) {
        String cat1 = (cat1Element != null) ? cat1Element.getText() : "";
        String cat2 = (cat2Element != null) ? " > " + cat2Element.getText() : "";
        String cat3 = (cat3Element != null) ? " > " + cat3Element.getText() : "";

        // Concatenate the categories, omitting any that are not present
        return cat1 + cat2 + cat3;
    }

    @Then("Build a companyprofile and add products")
    public void buildACompanyprofileAndAddProducts() {
    }
}
