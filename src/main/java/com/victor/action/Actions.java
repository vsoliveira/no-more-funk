package com.victor.action;

import com.victor.element.Elements;
import com.victor.model.ApplicationProperties;
import com.victor.model.Captcha;
import com.victor.service.CaptchaBreakerServices;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    final static Logger logger = LogManager.getLogger(Actions.class);

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
                .sendKeys("brunoguios-victor@yahoo.com");
        driver.findElement(By.xpath(Elements.inputConfirmaEmail))
                .sendKeys("brunoguios-victor@yahoo.com");
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
        driver.findElement(By.xpath(Elements.inputRadioLocalBarulhoViaPublica))
                .click();
        driver.findElement(By.xpath(Elements.inputRadioMotivoAglomeracaoDePessoas))
                .click();
        driver.findElement(By.xpath(Elements.inputRadioQtdPessoasAte50))
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
//        String s = takeScreenshotCaptcha(driver);
//        Captcha captcha = CaptchaBreakerServices.solveCaptcha(takeScreenshotCaptcha(driver));
        Captcha captcha = new Captcha("", "", false);
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
            logger.info("Captcha input is blank");
            driver.findElement(By.xpath(Elements.captchaRefresh))
                    .click();
            checkCaptcha(driver);
        }
    }

    private static void saveCaptchaImage(Captcha captcha) {
        if (!"".equals(captcha.getText())) {
            File oldfile = new File(captcha.getPathImage());

            File newfile = new File(captcha.getPathImage().replaceAll("([^\\\\]+)(?=\\.\\w+$)", captcha.getText()));

            if (oldfile.renameTo(newfile)) {
                logger.info("Rename succesful");
            } else {
                logger.info("Rename failed");
            }
        }
    }

    public static String takeScreenshotCaptcha(WebDriver driver) {

        ApplicationProperties properties = new ApplicationProperties("application.properties");

        WebElement element = driver.findElement(By.xpath(Elements.captcha));
        executeJavaScript("window.scrollTo(0, -250)", driver);

        if (driver.findElement(By.xpath(Elements.boxError)).isDisplayed()) {
            executeJavaScript("window.scrollTo(0, 250)", driver);
        }

        try {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImg = ImageIO.read(screenshot);

            Map<String, BigDecimal> coordinates = getResultInJavaScript("function offset(t){let e=t.getBoundingClientRect();return{top:e.y,left:e.x}}; let img = document.getElementById('CaptchaImage'); return offset(img);", driver);


            BigDecimal left = coordinates.get("left").add(BigDecimal.valueOf(109));
            BigDecimal top = BigDecimal.ZERO;
            if (driver.findElement(By.xpath(Elements.boxError)).isDisplayed()) {
                top = coordinates.get("top").add(BigDecimal.valueOf(116));
            } else {
                top = coordinates.get("top").add(BigDecimal.valueOf(130));
            }

            Point point = new Point(left.toBigInteger().intValue(), top.toBigInteger().intValue());

            int elementWidth = element.getSize().getWidth() + 50;
            int elementHeight = element.getSize().getHeight() + 20;

            logger.error("x: {}; y: {}", point.getX(), point.getY());
            BufferedImage elementScreenshot = fullImg.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);

            String imgFormat = properties.getProperty("format.captchas.images");
            String pathToSaveImages = properties.getProperty("path.captchas.images");

            File screenshotLocation = new File(String.format("%s\\%s.%s", pathToSaveImages, Math.random(), imgFormat));
            ImageIO.write(elementScreenshot, imgFormat, screenshotLocation);

            return screenshotLocation.getPath();
        } catch (Exception e) {
            logger.error(e);
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
