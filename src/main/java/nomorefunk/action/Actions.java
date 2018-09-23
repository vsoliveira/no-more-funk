package nomorefunk.action;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import nomorefunk.element.Elements;

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
	
	
}
