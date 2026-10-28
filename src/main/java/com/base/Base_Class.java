package com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public abstract class Base_Class {

    public static WebDriver driver;

    public static ExtentReports extentsReports;
    public static File file;

    protected static WebDriver launchBrowser(String browserName) {
        try{
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE LAUNCHING BROWSER");
        }
        return driver;
    }

    protected static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE LAUNCHING URL");
        }
    }

    protected static void passInput(WebElement element, String value) {
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PASSING INPUT");
        }
    }

    public static void elementClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR: OCCURRED WHILE CLICKING ELEMENT");
        }
    }

    protected static void browserTerminate() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE TERMINATING BROWSER");
        }
    }

    protected static void windowHandling(int num) {
        try {
            List<String> allWindows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allWindows.get(num));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING WINDOW");
        }
    }

    protected static String getCurrentWindowHandle() {
        try {
            return driver.getWindowHandle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING CURRENT WINDOW HANDLE");
            return null;
        }
    }

    protected static Set<String> getAllWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ALL WINDOW HANDLES");
            return null;
        }
    }

    protected static void switchToWindow(String windowHandle) {
        try {
            driver.switchTo().window(windowHandle);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO WINDOW");
        }
    }

    protected static void selectOptions(WebElement element, String type, String value) {
        try {
            Select select = new Select(element);
            if(type.equalsIgnoreCase("value")) {
                select.selectByValue(value);
            } else if(type.equalsIgnoreCase("visibletext")) {
                select.selectByVisibleText(value);
            } else if(type.equalsIgnoreCase("index")) {
                select.selectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SELECTING OPTION");
        }
    }

    protected static void deselectOptions(WebElement element, String type, String value) {

        try {
            Select select = new Select(element);
            if(type.equalsIgnoreCase("value")) {
                select.deselectByValue(value);
            } else if(type.equalsIgnoreCase("visibletext")) {
                select.deselectByVisibleText(value);
            } else if(type.equalsIgnoreCase("index")) {
                select.deselectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE DESELECTING OPTION");
        }
    }

    protected static void closeBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CLOSING BROWSER");
        }
    }

    protected static void implicitWait(int timeInSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SETTING IMPLICIT WAIT");
        }
    }

    protected static void waitForElement(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT TO BE VISIBLE");
        }
    }

    protected static void waitForElementToBeClickable(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("CLICK FAILED: " + e.getMessage());
        }
    }

    protected static void waitForElementInvisibility(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT INVISIBILITY");
        }
    }

    protected static void waitForText(WebElement element, String text, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR TEXT");
        }
    }

    protected static void waitForAlert(int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ALERT");
        }
    }

    protected static void waitForElementPresence(By locator, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT PRESENCE");
        }
    }

    protected static void fluentWait(WebElement element, int timeInSeconds, int pollingTimeInSeconds) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeInSeconds))
                    .pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT USING FLUENT WAIT");
        }
    }

    protected static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE NAVIGATING TO URL");
        }
    }

    protected static void navigate(String action) {
        try {
            if (action.equalsIgnoreCase("back")) {
                driver.navigate().back();
            } else if (action.equalsIgnoreCase("forward")) {
                driver.navigate().forward();
            } else if (action.equalsIgnoreCase("refresh")) {
                driver.navigate().refresh();
            } else {
                Assert.fail("ERROR : INVALID NAVIGATION ACTION");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE NAVIGATING");
        }
    }

    protected static String alert(String action) {
        try {
            Alert alert = driver.switchTo().alert();
            if (action.equalsIgnoreCase("gettext")) {
                return alert.getText();
            } else if (action.equalsIgnoreCase("accept")) {
                alert.accept();
            } else if (action.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
            } else {
                Assert.fail("ERROR : INVALID ALERT ACTION");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED WHILE PERFORMING ALERT ACTION");
        }
        return null;
    }

    protected static void alertSendKeys(String value) {
        try {
            driver.switchTo().alert().sendKeys(value);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SENDING KEYS TO ALERT");
        }
    }

    protected static void action(String type, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            if (type.equalsIgnoreCase("movetoelement")) {
                actions.moveToElement(element).perform();
            } else if (type.equalsIgnoreCase("click")) {
                actions.click(element).perform();

            } else if (type.equalsIgnoreCase("rightclick")) {
                actions.contextClick(element).perform();

            } else if (type.equalsIgnoreCase("doubleclick")) {
                actions.doubleClick(element).perform();

            } else if (type.equalsIgnoreCase("clickandhold")) {
                actions.clickAndHold(element).perform();

            } else if (type.equalsIgnoreCase("release")) {
                actions.release(element).perform();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PERFORMING MOUSE ACTION USING ACTIONS");
        }
    }

    protected static void action(String type, WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            if (type.equalsIgnoreCase("draganddrop")) {
                actions.dragAndDrop(source, target).perform();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE DRAGGING AND DROPPING ELEMENT USING ACTIONS");
        }
    }

    protected static void frameSwitch(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING INDEX");
        }
    }

    protected static void frameSwitch(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING NAME OR ID");
        }
    }

    protected static void frameSwitch(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING WEBELEMENT");
        }
    }

    protected static void frameSwitchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO DEFAULT CONTENT");
        }
    }

    protected static void frameSwitchToParentFrame() {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO PARENT FRAME");
        }
    }

    protected static void robotKeyPress(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PRESSING KEY USING ROBOT");
        }
    }

    protected static void robotKeyRelease(int key) {
        try {
            Robot robot = new Robot();
            robot.keyRelease(key);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE RELEASING KEY USING ROBOT");
        }
    }

    protected static void checkBox(WebElement element, String type) {
        try {
            if(type.equalsIgnoreCase("select")) {
                if(!element.isSelected()) {
                    element.click();
                }
            } else if(type.equalsIgnoreCase("deselect")) {
                if(element.isSelected()) {
                    element.click();
                }
            } else if(type.equalsIgnoreCase("click")) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE HANDLING CHECKBOX");
        }
    }

    protected static boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING ELEMENT IS ENABLED");
            return false;
        }
    }

    protected static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING ELEMENT IS DISPLAYED");
            return false;
        }
    }

    protected static boolean isSelected(WebElement element) {
        try {
            return element.isSelected();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING ELEMENT IS SELECTED");
            return false;
        }
    }

    protected static List<WebElement> getOptions(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getOptions();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING DROPDOWN OPTIONS");
            return null;
        }
    }

    protected static String getTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING PAGE TITLE");
            return null;
        }
    }

    protected static String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING CURRENT URL");
            return null;
        }
    }

    protected static String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ELEMENT TEXT");
            return null;
        }
    }

    protected static String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ELEMENT ATTRIBUTE");
            return null;
        }
    }

    protected static void fileUpload(String filepath) {
        try {
            Robot robot = new Robot();
            StringSelection selection = new StringSelection(filepath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE UPLOADING FILE");
        }
    }

    protected static void screenshot() {
        try {
            Date currentDate = new Date();
            String dateFile = currentDate.toString().replace(":", "_").replace(" ", "_");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:\\Users\\NARESH\\IdeaProjects\\Maven_Project\\screenshots\\" + dateFile + ".png"));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE TAKING SCREENSHOT");
        }
    }

    protected static void scroll(int pixels) {
        try {
            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, pixels).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED WHILE SCROLLING");
        }
    }

    protected static void scrollToElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.scrollToElement(element).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO ELEMENT");
        }
    }

    protected static WebElement getFirstSelectedOption(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getFirstSelectedOption();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING FIRST SELECTED OPTION");
            return null;
        }
    }

    protected static List<WebElement> getAllSelectedOptions(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getAllSelectedOptions();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ALL SELECTED OPTIONS");
            return null;
        }
    }

    protected static boolean isMultiple(WebElement element) {
        try {
            Select select = new Select(element);
            return select.isMultiple();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING DROPDOWN IS MULTIPLE");
            return false;
        }
    }

    protected static void radioButton(WebElement element) {
        try {
            if (!element.isSelected()) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE HANDLING RADIO BUTTON");
        }
    }

    protected static void jsClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CLICKING ELEMENT USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO ELEMENT USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO TOP USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO BOTTOM USING JAVASCRIPT");
        }
    }

    protected static void jsHighlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].style.border='3px solid red';", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE HIGHLIGHTING ELEMENT USING JAVASCRIPT");
        }
    }

    protected static String jsGetTitle() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (String) js.executeScript("return document.title;");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING TITLE USING JAVASCRIPT");
            return null;
        }
    }

    protected static String jsGetUrl() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return (String) js.executeScript("return document.URL;");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING URL USING JAVASCRIPT");
            return null;
        }
    }

    protected static void jsScroll(int pixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy({top: arguments[0], behavior: 'smooth'});", pixels);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED WHILE SCROLLING");
        }
    }

    protected static void waitForUrl(String expectedURL, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR URL");
        }
    }

    protected static void validationUrl(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING VALIDATION");
        }
    }

    protected static void validateDate(WebElement element) {
        try {
            String actualDate = element.getText().trim();
            if (actualDate.equalsIgnoreCase("Today")) {
                Assert.assertTrue("Profile update date is not Today",
                        actualDate.equalsIgnoreCase("Today"));
                return;
            }
            System.out.println(actualDate);
            String[] formats = {
                    "ddMMM, yyyy", "dd MMM, yyyy", "dd-MM-yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy-MM-dd", "MMM dd, yyyy", "MMMM dd, yyyy"
            };
            LocalDate displayedDate = null;
            for (String format : formats) {
                try {
                    displayedDate = LocalDate.parse(actualDate, DateTimeFormatter.ofPattern(format, Locale.ENGLISH));
                    break;
                } catch (DateTimeParseException ignored) {
                }
            }
            if (displayedDate == null) {
                Assert.fail("ERROR : UNSUPPORTED DATE FORMAT - " + actualDate);
            }
            Assert.assertEquals(LocalDate.now(), displayedDate);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR DURING DATE VALIDATION");
        }
    }

    protected static WebElement waitForElementToBeClickable(By locator, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT TO BE CLICKABLE");
            return null;
        }
    }

    protected static void waitForElementCountToIncrease(By locator, int previousCount, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(d -> d.findElements(locator).size() > previousCount);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT COUNT TO INCREASE");
        }
    }

    protected static void validation(WebElement element, String expected){
     try {
         String text = element.getText();
         System.out.println(text);
         Assert.assertEquals(element.getText(),expected);
     }   catch (Exception e){
         Assert.fail("ERROR : OCCUR DURING VALIDATION");
     }
    }

    public static void extentsReportStart(String location) {
        extentsReports = new ExtentReports();
        file = new File(location);
        file.getParentFile().mkdirs();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
        extentsReports.attachReporter(sparkReporter);
        extentsReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentsReports.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public static void extentsReportTeardown(String location) throws IOException {
        extentsReports.flush();
    }

    public String takeScreenshot() throws IOException {
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("Screenshots\\.png" + "_" + timestamp + ".png");
        FileHandler.copy(src, dest);
        return dest.getAbsolutePath();
    }

}
