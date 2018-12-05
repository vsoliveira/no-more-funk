package com.victor.action;

import com.victor.element.Elements;
import com.victor.model.ApplicationProperties;
import com.victor.model.Captcha;
import com.victor.service.CaptchaBreakerServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    }

    ;

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
                .sendKeys("548");
        driver.findElement(By.xpath(Elements.inputEnderecoComplemento))
                .sendKeys("");

        driver.findElement(By.xpath(Elements.btnProximoEndereco)).click();

    }

    ;

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
        driver.findElement(By.xpath(Elements.captchaInput))
                .click();


        checkCaptcha(driver);

    }

    public static void checkCaptcha(WebDriver driver) {
        if (driver.findElement(By.xpath(Elements.boxError)).isDisplayed()) {
            driver.findElement(By.xpath(Elements.boxErrorMessage));
            if (!driver.findElement(By.xpath(Elements.checkDeclaro)).isSelected()) {
                driver.findElement(By.xpath(Elements.checkDeclaro))
                        .click();
                driver.findElement(By.xpath(Elements.captchaInput))
                        .click();
            }

            tryFillCaptcha(driver);
        } else {
            tryFillCaptcha(driver);
        }


    }

    public static void tryFillCaptcha(WebDriver driver) {
        Captcha captcha = CaptchaBreakerServices.solveCaptcha(takeScreenshotCaptcha(driver));

        if (captcha.isSolved()) {
            saveCaptchaImage(captcha);
            driver.findElement(By.xpath(Elements.captchaInput)).sendKeys(captcha.getText());
        } else {
            saveCaptchaImage(captcha);
        }
        try {
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.xpath(Elements.captchaInput)).getAttribute("value").length() == 5;
                }
            });

            driver.findElement(By.xpath(Elements.btnEnviar))
                    .click();

            if (!driver.findElement(By.xpath(Elements.popUpSucess)).isDisplayed()) {
                checkCaptcha(driver);
            }
        } catch (TimeoutException e) {
            System.out.println("Captcha input is blank");
            checkCaptcha(driver);
        }
    }

    private static void saveCaptchaImage(Captcha captcha) {
        if (!"".equals(captcha.getText())) {
            File oldfile =new File(captcha.getPathImage());

            File newfile =new File(captcha.getPathImage().replaceAll("([^\\\\]+)(?=\\.\\w+$)", captcha.getText()));

            if(oldfile.renameTo(newfile)){
                System.out.println("Rename succesful");
            }else{
                System.out.println("Rename failed");
            }
        }
    }

    public static String takeScreenshotCaptcha(WebDriver driver) {

        ApplicationProperties properties = new ApplicationProperties("application.properties");

        WebElement element = driver.findElement(By.xpath(Elements.captcha));
        executeJavaScript("window.scrollBy(0, -250)", driver);

        if (driver.findElement(By.xpath(Elements.boxError)).isDisplayed()) {
            executeJavaScript("window.scrollBy(0, 250)", driver);
        }

        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);


            Point pointOld = element.getLocation();

//            Map<String, BigDecimal> coordinates = getResultInJavaScript("return $('#CaptchaImage').position();", driver);
            Map<String, BigDecimal> coordinates = getResultInJavaScript("function offset(e){var t=e.position(),o=window.pageXOffset||document.documentElement.scrollLeft,n=window.pageYOffset||document.documentElement.scrollTop;return{top:t.top-n,left:t.left-o}}var element=$(\"#CaptchaImage\"); return offset(element);", driver);

            BigDecimal left = coordinates.get("left");
            BigDecimal top = coordinates.get("top");

            Point point = new Point(left.toBigInteger().intValue(), top.toBigInteger().intValue());

            int elementWidth = element.getSize().getWidth();
            int elementHeight = element.getSize().getHeight();

            BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);

            ImageIO.write(elementScreenshot, properties.getProperty("format.captchas.images"), screenshot);

            File screenshotLocation = new File(String.format("%s\\%s.%s", properties.getProperty("path.captchas.images"), Math.random(), properties.getProperty("format.captchas.images")));
            FileUtils.copyFile(screenshot, screenshotLocation);

            //driver.findElement(By.xpath(Elements.captchaRefresh))
            //        .click();
            return screenshotLocation.getPath();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static void executeJavaScript(String command, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(command, "");
    }

    private static Map<String, BigDecimal> getResultInJavaScript(String command, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        Map<String, Object> jsLocation = (Map<String, Object>) jse.executeScript(command, "");
        return extractResultFromJavaScript(jsLocation);
    }

    private static Map<String, BigDecimal> extractResultFromJavaScript(Map<String, Object> map) {

        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            Object coordinate = entry.getValue();
            if (coordinate instanceof Long) {
                Long valor = (Long) entry.getValue();
                result.put(entry.getKey(), new BigDecimal(valor));
            } else if (coordinate instanceof Integer) {
                Integer valor = (Integer) entry.getValue();
                result.put(entry.getKey(), new BigDecimal(valor));
            } else if (coordinate instanceof Double) {
                Double valor = (Double) entry.getValue();
                result.put(entry.getKey(), new BigDecimal(valor));
            } else {
                result.put(entry.getKey(), new BigDecimal(entry.toString()));
            }

        }
        return result;
    }

}
