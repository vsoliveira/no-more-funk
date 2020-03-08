package com.victor;

import com.victor.action.Actions;
import com.victor.model.Driver;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;

public class NoMoreFunkApplication {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		Driver drivers = new Driver();
//		WebDriver driver = drivers.getFirefox();
		WebDriver driver = drivers.getChrome();
		for (int i = 0; i <= 100; i++) {
			driver.get("http://www2.policiamilitar.sp.gov.br/ocorrenciaweb");

			Actions.fillFormCadastro(driver);
			Actions.fillFormEndereco(driver);
			Actions.fillFormOrigem(driver);
			Actions.fillFormInformacoes(driver);
		}

		driver.quit();

	}
	

}
