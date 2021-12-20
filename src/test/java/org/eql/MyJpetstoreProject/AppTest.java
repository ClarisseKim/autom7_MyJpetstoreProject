package org.eql.MyJpetstoreProject;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {
	WebDriver driver;
	Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
	WebDriverWait wait;

	@FindBy(xpath = "//a[@href='actions/Catalog.action']")
	WebElement lienEnterTheStore;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);
	}

	@Test
	public void jpetstoreTest() {
		LOGGER.info("****** ETAPE 1 : Connexion à l'url de départ de JpetStore *******");
		driver.get("http://localhost:8090/jpetstore");

		LOGGER.info("****** ETAPE 2 : Accéder à la page de login de JpetStore ******");
		driver.findElement(By.xpath("//a[@href='actions/Catalog.action']")).click();

		LOGGER.info("****** ETAPE 3 : Cliquer sur le lien 'SignOn ******");
		driver.findElement(By.xpath("//a[contains(@href,'signonForm')]")).click();

		LOGGER.info("****** ETAPE 4 : Supprimer le contenu du champ login et indiquer mon login ******");
		WebElement champLogin = driver.findElement(By.xpath("//input[@name='username']"));
		champLogin.clear();
		champLogin.sendKeys("ACID");

		LOGGER.info("****** ETAPE 5 : Supprimer le contenu du champ password et indiquer mon password ******");
		WebElement champPassword = driver.findElement(By.xpath("//input[@name='password']"));
		champPassword.clear();
		champPassword.sendKeys("ACID");

		LOGGER.info("****** ETAPE 6 : Cliquer sur le bouton login ******");
		driver.findElement(By.xpath("//input[@name='signon']")).click();

		LOGGER.info("****** ETAPE 7 : Vérifier que l'on est connecté avec le bon utilisateur ******");
		String nomAccueilRecupere = driver.findElement(By.xpath("//div[@id='WelcomeContent']")).getText();
		String nomAccueilAttendu = "Welcome ABC!";
		assertEquals("Le nom récupéré '" + nomAccueilRecupere + "' ne correspond pas au nom accueil attendu '"
				+ nomAccueilAttendu + "'", nomAccueilAttendu, nomAccueilRecupere);

		// snake
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/a[4]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[5]/a")).click();

		// shark
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/a[1]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[3]/td[1]/a")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[5]/a")).click();

		// parrot
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/a[5]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr[2]/td[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[5]/a")).click();

		// remove shark
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[3]/td[8]/a")).click();

		// update parrot quantity 2
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[3]/td[5]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/form/table/tbody/tr[3]/td[5]/input"))
				.sendKeys("2");
		// update cart
		driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[4]/td[1]/input")).click();

		// checkout
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/a")).click();

	}

	@After
	public void teardown() {
		// driver.quit();
	}
}
