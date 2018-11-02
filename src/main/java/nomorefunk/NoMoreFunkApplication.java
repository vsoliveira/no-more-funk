package nomorefunk;
import org.openqa.selenium.WebDriver;

import nomorefunk.action.Actions;
import nomorefunk.model.Driver;

public class NoMoreFunkApplication {
	public static void main(String[] args) throws InterruptedException {
		
		Driver drivers = new Driver();
		WebDriver driver = drivers.getFirefox();
		driver.get("http://www2.policiamilitar.sp.gov.br/ocorrenciaweb");

		Actions.fillFormCadastro(driver);
		Actions.fillFormEndereco(driver);
		Actions.fillFormOrigem(driver);
		Actions.fillFormInformacoes(driver);
		
		Thread.sleep(15000L);
		driver.quit();

	}
	

}
