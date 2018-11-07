package com.victor.action;

import com.victor.element.Elements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Actions {

    protected Actions() {

    }

    public static void fillFormCadastro(WebDriver driver) {
        driver.findElement(By.xpath(Elements.inputNome))
                .sendKeys("Victor Samuel de Oliveira Silva");
        driver.findElement(By.xpath(Elements.inputCpf))
                .sendKeys(Keys.HOME + "38862802846");
        driver.findElement(By.xpath(Elements.inputRg))
                .sendKeys("422690478");
        driver.findElement(By.xpath(Elements.inputEmail))
                .sendKeys("victorsasilva@yahoo.com.br");
        driver.findElement(By.xpath(Elements.inputConfirmaEmail))
                .sendKeys("victorsasilva@yahoo.com.br");
        driver.findElement(By.xpath(Elements.inputDdd))
                .sendKeys("11");
        driver.findElement(By.xpath(Elements.inputTelefone))
                .sendKeys("966112236");

        driver.findElement(By.xpath(Elements.btnProximoCadastro)).click();

    };

    public static void fillFormEndereco(WebDriver driver) {
        driver.findElement(By.xpath(Elements.inputCep))
                .sendKeys(Keys.HOME + "07097140");

        driver.findElement(By.xpath(Elements.imgLupa)).click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath(Elements.inputEnderecoBairroHidden)).getAttribute("value").length() != 0;
            }
        });

        driver.findElement(By.xpath(Elements.inputEnderecoNumero))
                .sendKeys("526");
        driver.findElement(By.xpath(Elements.inputEnderecoComplemento))
                .sendKeys("B");

        driver.findElement(By.xpath(Elements.btnProximoEndereco)).click();

    };

    public static void fillFormOrigem(WebDriver driver) {
        driver.findElement(By.xpath(Elements.inputRadioLocalBarulho))
                .click();
        driver.findElement(By.xpath(Elements.inputRadioMotivo))
                .click();

        driver.findElement(By.xpath(Elements.btnProximoOrigem)).click();
    }

    public static void fillFormInformacoes(WebDriver driver) {
        driver.findElement(By.xpath(Elements.inputComplemento))
                .sendKeys(Elements.complemento);
        driver.findElement(By.xpath(Elements.inputRadioNao))
                .click();
        driver.findElement(By.xpath(Elements.checkDeclaro))
                .click();
    }

    public static void takeScreenshotCaptcha(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath(Elements.captcha));

        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            Point point = element.getLocation();

            int elementWidth = element.getSize().getWidth();
            int elementHeight = element.getSize().getHeight();

            BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);


            ImageIO.write(elementScreenshot, "png", screenshot);
            File screenshotLocation = new File(String.format("C:\\images\\%s.png", Math.random()));
            FileUtils.copyFile(screenshot, screenshotLocation);
            driver.findElement(By.xpath(Elements.captchaRefresh))
                    .click();
        } catch (Exception e) {

        }


    }


}
