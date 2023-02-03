

package com.udacity.jwdnd.course1.cloudstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

  @LocalServerPort
  private int port;

  private WebDriver driver;

  @BeforeAll
  static void beforeAll() {
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void beforeEach() {
    this.driver = new ChromeDriver();
  }

  @AfterEach
  public void afterEach() {
    if (this.driver != null) {
      driver.quit();
    }
  }

  @Test
  public void getLoginPage() {
    driver.get("http://localhost:" + this.port + "/login");
    Assertions.assertEquals("Login", driver.getTitle());
  }

  /**
   * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity checks.
   **/
  private void doMockSignUp(String firstName, String lastName, String userName, String password) {
    // Create a dummy account for logging in later.

    // Visit the sign-up page.
    WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
    driver.get("http://localhost:" + this.port + "/signup");
    webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

    // Fill out credentials
    webDriverWait.until(visibilityOfElementLocated(By.id("inputFirstName")));
    WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
    inputFirstName.click();
    inputFirstName.sendKeys(firstName);

    webDriverWait.until(visibilityOfElementLocated(By.id("inputLastName")));
    WebElement inputLastName = driver.findElement(By.id("inputLastName"));
    inputLastName.click();
    inputLastName.sendKeys(lastName);

    webDriverWait.until(visibilityOfElementLocated(By.id("inputUsername")));
    WebElement inputUsername = driver.findElement(By.id("inputUsername"));
    inputUsername.click();
    inputUsername.sendKeys(userName);

    webDriverWait.until(visibilityOfElementLocated(By.id("inputPassword")));
    WebElement inputPassword = driver.findElement(By.id("inputPassword"));
    inputPassword.click();
    inputPassword.sendKeys(password);

    // Attempt to sign up.
    webDriverWait.until(visibilityOfElementLocated(By.id("buttonSignUp")));
    WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
    buttonSignUp.click();

		/* Check that the sign-up was successful.
		// You may have to modify the element "success-msg" and the sign-up
		// success message below depending on the rest of your code.
		*/
    assertTrue(driver.findElement(By.id("signup-success-message")).getText()
        .contains("You successfully signed up!"));
  }


  /**
   * PLEASE DO NOT DELETE THIS method. Helper method for Udacity-supplied sanity checks.
   **/
  private void doLogIn(String userName, String password) {
    // Log in to our dummy account.
    driver.get("http://localhost:" + this.port + "/login");
    WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

    webDriverWait.until(visibilityOfElementLocated(By.id("inputUsername")));
    WebElement loginUserName = driver.findElement(By.id("inputUsername"));
    loginUserName.click();
    loginUserName.sendKeys(userName);

    webDriverWait.until(visibilityOfElementLocated(By.id("inputPassword")));
    WebElement loginPassword = driver.findElement(By.id("inputPassword"));
    loginPassword.click();
    loginPassword.sendKeys(password);

    webDriverWait.until(visibilityOfElementLocated(By.id("login-button")));
    WebElement loginButton = driver.findElement(By.id("login-button"));
    loginButton.click();

    webDriverWait.until(ExpectedConditions.titleContains("Home"));

  }

  /**
   * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the rest of your code.
   * This test is provided by Udacity to perform some basic sanity testing of your code to ensure
   * that it meets certain rubric criteria.
   * <p>
   * If this test is failing, please ensure that you are handling redirecting users back to the
   * login page after a successful sign-up. Read more about the requirement in the rubric:
   * https://review.udacity.com/#!/rubrics/2724/view
   */
  @Test
  public void testRedirection() {
    // Create a test account
    doMockSignUp("Redirection", "Test", "RT", "123");

    // Check if we have been redirected to the login page.
    Assertions.assertEquals("Login", driver.getTitle());
  }

  /**
   * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the rest of your code.
   * This test is provided by Udacity to perform some basic sanity testing of your code to ensure
   * that it meets certain rubric criteria.
   * <p>
   * If this test is failing, please ensure that you are handling bad URLs gracefully, for example
   * with a custom error page.
   * <p>
   * Read more about custom error pages at:
   * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
   */
  @Test
  public void testBadUrl() {
    // Create a test account
    doMockSignUp("URL", "Test", "UT", "123");
    doLogIn("UT", "123");
    // Try to access a random made-up URL.
    driver.get("redirect:/http://localhost:" + this.port + "/some-random-page");
    Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
  }

  /**
   * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the rest of your code.
   * This test is provided by Udacity to perform some basic sanity testing of your code to ensure
   * that it meets certain rubric criteria.
   * <p>
   * If this test is failing, please ensure that you are handling uploading large files (>1MB),
   * gracefully in your code.
   * <p>
   * Read more about file size limits here: https://spring.io/guides/gs/uploading-files/ under the
   * "Tuning File Upload Limits" section.
   */
//  @Test
//  public void testLargeUpload() {
//    // Create a test account
//    doMockSignUp("Large File", "Test", "LFT", "123");
//    doLogIn("LFT", "123");
//
//    // Try to upload an arbitrary large file
//    WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
//    String fileName = "upload5m.zip";
//
//    webDriverWait.until(visibilityOfElementLocated(By.id("fileUpload")));
//    WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
//    fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());
//
//    WebElement uploadButton = driver.findElement(By.id("uploadButton"));
//    uploadButton.click();
//    try {
//      webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
//    } catch (org.openqa.selenium.TimeoutException e) {
//      System.out.println("Large File upload failed");
//    }
//    Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
//
//  }

  /**
   * This section contains the tests related the Notes section of the app, namely Creating, Editing
   * and Deleting a note. Also present is the utility method to re-direct back to the notes tab
   * after the successful testing
   */

  @Test
  public void create_note() throws InterruptedException {
    doLogIn("user", "1234");

    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

    navigateToNotesTab(webDriverWait);

    // open the create-note dialog box
    driver.findElement(By.id("add-note-button")).click();
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("noteModal"))).isDisplayed());

    // input new note details
    webDriverWait.until(visibilityOfElementLocated(By.id("note-title"))).sendKeys("Selenium Test");
    webDriverWait.until(visibilityOfElementLocated(By.id("note-description")))
        .sendKeys("Test description");

    // save the note
    webDriverWait.until(visibilityOfElementLocated(By.id("submit-note"))).click();

    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("success"))).isDisplayed());

    // navigate back to home-page -> notes-tab
    driver.get("http://localhost:" + this.port + "/home");
    navigateToNotesTab(webDriverWait);

    // verify the existence of newly created note
    assertEquals("Selenium Test",
        webDriverWait.until(visibilityOfElementLocated(By.id("note-title-row"))).getText());
    assertEquals("Test description",
        webDriverWait.until(visibilityOfElementLocated(By.id("note-description-row"))).getText());

    Thread.sleep(1000);
  }

  @Test
  public void edit_note() throws InterruptedException {
    create_note();

    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

    int numOfNote = driver.findElement(By.id("userTable")).findElement(By.tagName("tbody"))
        .findElements(By.tagName("tr")).size();

    webDriverWait.until(visibilityOfElementLocated(By.id("edit-note"))).click();
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("noteModal"))).isDisplayed());

    // edit the note
    var noteTitle = webDriverWait.until(visibilityOfElementLocated(By.id("note-title")));
    var noteDesc = webDriverWait.until(visibilityOfElementLocated(By.id("note-description")));

    noteTitle.clear();
    noteTitle.sendKeys("edited-test-note-title");

    noteDesc.clear();
    noteDesc.sendKeys("edited-test-note-description");

    // save the edited note
    webDriverWait.until(visibilityOfElementLocated(By.id("submit-note"))).click();

    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("success"))).isDisplayed());

    // navigate back to home-page -> notes-tab
    driver.get("http://localhost:" + this.port + "/home");
    navigateToNotesTab(webDriverWait);

    // verify the existence of newly created note
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("note-title-row"))).getText()
        .startsWith("edited"));
    assertTrue(
        webDriverWait.until(visibilityOfElementLocated(By.id("note-description-row"))).getText()
            .startsWith("edited"));

    // verify that number of notes remain the same, as we are only editing the existing note
    assertEquals(numOfNote,
        driver.findElement(By.id("userTable")).findElement(By.tagName("tbody"))
            .findElements(By.tagName("tr")).size());

    Thread.sleep(1000);
  }

  @Test
  public void delete_note() throws InterruptedException {
    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

    create_note();

    int numOfNote = driver.findElement(By.id("userTable")).findElement(By.tagName("tbody"))
        .findElements(By.tagName("tr")).size();

    // click on the delete note button
    webDriverWait.until(visibilityOfElementLocated(By.id("delete-note"))).click();

    // verify
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("success"))).isDisplayed());

    // navigate back to home-page -> notes-tab
    driver.get("http://localhost:" + this.port + "/home");
    navigateToNotesTab(webDriverWait);

    // verify there are no notes -> deletion successful
    assertEquals(numOfNote - 1,
        driver.findElement(By.id("userTable")).findElement(By.tagName("tbody"))
            .findElements(By.tagName("tr")).size());

    Thread.sleep(1000);
  }

  private void navigateToNotesTab(WebDriverWait webDriverWait) {
    driver.findElement(By.id("nav-notes-tab")).click();
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("nav-notes"))).isDisplayed());
  }

  /**
   * This section contains the tests related the Credential section of the app, namely Creating,
   * Editing and Deleting a credential.
   */

  @Test
  public void create_credential() throws InterruptedException {
    doLogIn("user", "1234");
    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
    navigateToCredentialTab(webDriverWait);

    // open the create-credential dialog box
    driver.findElement(By.id("add-credential-button")).click();
    assertTrue(
        webDriverWait.until(visibilityOfElementLocated(By.id("credentialModal"))).isDisplayed());

    // input new credential details
    webDriverWait.until(visibilityOfElementLocated(By.id("credential-url")))
        .sendKeys("http://google.com");
    webDriverWait.until(visibilityOfElementLocated(By.id("credential-username")))
        .sendKeys("username_123");
    webDriverWait.until(visibilityOfElementLocated(By.id("credential-password")))
        .sendKeys("password@123");

    // save the credential
    webDriverWait.until(visibilityOfElementLocated(By.id("save-credential-button"))).click();

    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("success"))).isDisplayed());

    // navigate back to home-page -> credential-tab
    driver.get("http://localhost:" + this.port + "/home");
    navigateToCredentialTab(webDriverWait);

    // verify the existence of newly created note
    assertEquals("http://google.com",
        webDriverWait.until(visibilityOfElementLocated(By.id("url-credential"))).getText());
    assertEquals("username_123",
        webDriverWait.until(visibilityOfElementLocated(By.id("username-credential"))).getText());
    //assertEquals("password@123", webDriverWait.until(visibilityOfElementLocated(By.id("password-credential"))).getText());

    Thread.sleep(1000);
  }

  @Test
  public void edit_credential() throws InterruptedException {
    create_credential();
    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

    int numOfNote = driver.findElement(By.id("credentialTable")).findElement(By.tagName("tbody"))
        .findElements(By.tagName("tr")).size();

    webDriverWait.until(visibilityOfElementLocated(By.id("edit-credential-button"))).click();
    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("credentialModal"))).isDisplayed());

    // edit the credential
    var credentialURL = webDriverWait.until(visibilityOfElementLocated(By.id("credential-url")));
    var credentialUsername = webDriverWait.until(visibilityOfElementLocated(By.id("credential-username")));
    var credentialPassword = webDriverWait.until(visibilityOfElementLocated(By.id("credential-password")));

    credentialURL.clear();
    credentialUsername.clear();
    credentialPassword.clear();

    credentialURL.sendKeys("http://edited-url.com");
    credentialUsername.sendKeys("edited-username");
    credentialPassword.sendKeys("edited-password");

    // save the edited credential
    webDriverWait.until(visibilityOfElementLocated(By.id("save-credential-button"))).click();

    assertTrue(webDriverWait.until(visibilityOfElementLocated(By.id("success"))).isDisplayed());

    // navigate back to home-page -> notes-tab
    driver.get("http://localhost:" + this.port + "/home");
    navigateToCredentialTab(webDriverWait);

    // verify that number of credentials remain the same, as we are only editing the existing note
    assertEquals(numOfNote, driver.findElement(By.id("credentialTable")).findElement(By.tagName("tbody"))
        .findElements(By.tagName("tr")).size());

    Thread.sleep(1000);
  }

  @Test
  public void delete_credential() throws InterruptedException {
  }

  private void navigateToCredentialTab(WebDriverWait webDriverWait) {
    driver.findElement(By.id("nav-credentials-tab")).click();
    assertTrue(
        webDriverWait.until(visibilityOfElementLocated(By.id("nav-credentials"))).isDisplayed());
  }
}