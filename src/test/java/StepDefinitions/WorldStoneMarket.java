package StepDefinitions;

import Pages.DialogContent;
import Utilities.ExcelUtility;
import Utilities.GWD;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.FileOutputStream;
import java.time.Instant;
import java.util.*;


import Utilities.GWD;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class WorldStoneMarket extends GWD {


  // WorldStoneMarket wsm = new WorldStoneMarket();
   Pages.WorldStoneMarket wsm=new Pages.WorldStoneMarket();
   String nameOfCompany;


    Instant wait = null;


//    @Given("Navigate to stonecontact")
//    public void navigate_to_stonecontact() {
//
//
//    }


    @When("Collect Company names,links, and build an excell")
    public void collectCompanyNamesLinksAndBuildAnExcell() {


        GWD.getDriver().get("https://www.stonecontact.com/suppliers/stone-machinery/turkey-country");

        List<WebElement> captions = getDriver().findElements
                (By.cssSelector("[class='pname']"));




        Map<String, Map<String, String>> companyInformation=new HashMap<>();


        for (WebElement e : captions) {
            nameOfCompany=e.getText();


            wsm.myClick(e);   // We go to each company profile
            WebElement productsHeaderLink = getDriver().findElement(By.cssSelector("[title='Products']"));

         //   wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Products']")));

            wsm.myClick(productsHeaderLink); // We go to all product lists





            List<WebElement> productEachLink = getDriver().findElements
            (By.cssSelector("a div[class='img-wrap']"));


            for (WebElement prdlnk:    productEachLink  ) {

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
