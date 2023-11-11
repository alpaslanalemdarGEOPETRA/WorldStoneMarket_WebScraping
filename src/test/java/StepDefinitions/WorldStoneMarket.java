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

        List<WebElement> captions = getDriver().findElements
                (By.cssSelector("[class='pname'] a"));


        //   Map<String, Map<String, String>> companyInformation=new HashMap<>();

        for (WebElement e : captions) {

            wsm.myClick(e);   // We go to each company profile
         /////////  wsm.wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("[class='company'] [class='name']")));

         /////////  String nameOfCompany = getDriver().findElement((By.cssSelector("[class='company'] [class='name']"))).getText();
         /////////  String descriptionOfCompany = getDriver().findElement((By.cssSelector("[class='cd-box']"))).getText();


            WebElement productsHeaderLink = getDriver().findElement(By.cssSelector("[title='Products']"));

            //   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Products']")));

            wsm.myClick(productsHeaderLink); // We go to all product lists


            List<WebElement> productEachLink = getDriver().findElements
                    (By.cssSelector("a div[class='img-wrap']"));


            for (WebElement prdlnk : productEachLink) {

                wsm.myClick(prdlnk); // We go to each product page of company


                getDriver().navigate().back();
            }


            //   companyInformation.put(e.getText(), )


        }
        // ExcelUtility.writeToExcel("src/test/java/WorldStoneMarketWebScraping/WSM_YeniExcel.xlsx", Scenario "Collect company information" );


    }

    @Then("Build a companyprofile and add products")
    public void buildACompanyprofileAndAddProducts() {
    }
}
