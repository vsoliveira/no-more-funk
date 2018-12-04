package com.victor;

import com.victor.action.Actions;
import com.victor.model.Driver;
import com.victor.service.CaptchaBreakerServices;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class NoMoreFunkApplication {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		CaptchaBreakerServices.getCaptcha();

//		Driver drivers = new Driver();
//		WebDriver driver = drivers.getFirefox();
//		driver.get("http://www2.policiamilitar.sp.gov.br/ocorrenciaweb");
//
//		Actions.fillFormCadastro(driver);
//		Actions.fillFormEndereco(driver);
//		Actions.fillFormOrigem(driver);
//		Actions.fillFormInformacoes(driver);

//		for (int i = 0; i <= 1000; i++) {
//			Actions.takeScreenshotCaptcha(driver);
//		}

//		Thread.sleep(10000L);
//		driver.quit();

	}
	

}
