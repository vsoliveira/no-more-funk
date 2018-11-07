package com.victor;
import org.openqa.selenium.WebDriver;

import com.victor.action.Actions;
import com.victor.model.Driver;

public class NoMoreFunkApplication {
	public static void main(String[] args) throws InterruptedException {
		
		Driver drivers = new Driver();
		WebDriver driver = drivers.getFirefox();
		driver.get("http://www2.policiamilitar.sp.gov.br/ocorrenciaweb");

		Actions.fillFormCadastro(driver);
		Actions.fillFormEndereco(driver);
		Actions.fillFormOrigem(driver);
		Actions.fillFormInformacoes(driver);

		for (int i = 0; i <= 100; i++) {
			Actions.takeScreenshotCaptcha(driver);
		}

		//Thread.sleep(5000L);
		driver.quit();

	}
	

}
