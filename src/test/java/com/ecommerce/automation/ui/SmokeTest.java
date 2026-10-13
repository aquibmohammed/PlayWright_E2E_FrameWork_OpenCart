package com.ecommerce.automation.ui;

import com.ecommerce.automation.base.baseTest;
import com.ecommerce.automation.config.ConfigManager;
import com.microsoft.playwright.*;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SmokeTest extends baseTest {
    @Test(priority = 1,groups = {"smoke","regression"}, enabled = true)
    public void openTheApplicaton() {
        page.navigate(ConfigManager.getUIBaseURL());
        test.info("navigating to the website");
        Assert.assertFalse(page.title().isBlank(), "Application page title should not be blank");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in")).click();
        PlaywrightAssertions.assertThat(page.locator(".errorMessage")).containsText(Pattern.compile("required"));
//        Locator socialLinks = page.locator("//div[@class='social-btns']/a[@target='_blank']");
//        List<Locator> socialLocatorsList = socialLinks.all();
//        for(Locator locator : socialLocatorsList){
//            Page newtab  =  page.waitForPopup(() ->{locator.click();});
//            newtab.waitForLoadState(LoadState.DOMCONTENTLOADED);
//            newtab.locator("body").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//
//
//            System.out.println(newtab.title());
//        }
//        //socialLocatorsList.stream().filter()
//        System.out.println(socialLinks.count());
        verifySocialLinksOpenInNewTabs();

    }

    @Test(priority = 2,groups = {"smoke","regression"},
    dependsOnMethods = {"openTheApplicaton"},enabled = true)
    public void userSignIN() {
        //Sign-in
        test.info("navigating to the website");
        page.navigate(ConfigManager.getUIBaseURL());
        test.info("adding email");
        page.getByPlaceholder("Enter Email").fill("admin@email.com");
        test.info("adding password");
        page.getByPlaceholder("Enter Password").fill("admin@123");
        test.info("clicking signIn button");
        page.getByText("Sign in").nth(1).click();
        test.info("doing assertion after loggin ");
        PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
    }

    @Test(priority = 4, groups = {"smoke","regression"}, dependsOnMethods = {"openTheApplicaton","userLogOut"}, enabled = false)
    public void userSignUP() {
        //sign up
       test.skip("Skipp this");

        page.getByText("New user? Signup").click();
        Locator locator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign Up"));
        PlaywrightAssertions.assertThat(locator).isDisabled();
        page.getByPlaceholder("Name").fill("test");
        page.pause();
        page.getByPlaceholder("Email").fill("test123@gmail.com");
        page.getByPlaceholder("Password").fill("test123");
        page.getByLabel("RAG", new Page.GetByLabelOptions().setExact(true)).check();
        PlaywrightAssertions.assertThat(page.getByLabel("RAG", new Page.GetByLabelOptions().setExact(true))).isChecked();
        page.getByLabel("automation", new Page.GetByLabelOptions().setExact(true)).check();
        PlaywrightAssertions.assertThat(page.getByLabel("automation", new Page.GetByLabelOptions().setExact(true))).isChecked();
        page.locator("#gender1").check();
        PlaywrightAssertions.assertThat(page.locator("#gender1")).isChecked();
        //this selects by its attrivbue value
        page.locator("#state").selectOption(new SelectOption().setLabel("Goa"));
        // this select by on screen text value
        //page.locator("#state").selectOption("Goa");
        String[] args = {"Playing", "Reading", "Swimming"};
        page.locator("#hobbies").selectOption(args);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign Up")).click();
    }

    @Test(priority = 3, groups = {"smoke","regression"})
    public  void userLogOut() {
        //     sign out
        page.getByAltText("menu").click();
        page.getByText("Sign out").click();
    }
//        page.navigate(ConfigManager.getUIBaseURL());
//
//        // Multiple tabs and window
//        BrowserContext browserContext1 = browser.newContext();
//        page.navigate(ConfigManager.getUIBaseURL());
//        Page newPage = browserContext1.waitForPage(() -> { page.locator("")});
//        page.bringToFront();
//        newPage.bringToFront();
//        //switch to specific tab
//        List<Frame> allFrame = page.frames();
//        System.out.println("Total Number of Frames"+ allFrame.size());
//        Frame frame = page.frameByUrl(Pattern.compile(".*loing.*"));
//        FrameLocator locator1 = page.frameLocator("#");
//
//
//
//            page.locator(".nav-menu-item-manage").hover();
//            page.getByAltText("manage category").click();
//
//
//
////        //autoSuggestions
////        Browser browser1 = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
////        Page page1 = browser1.newPage();
//
//        //uploadFile
//       // page.locator("#file-upload").setInputFiles(Path.of("/User/mukesh test.png"));
//        //without input tag uploading files
//        FileChooser fileChooser = page.waitForFileChooser(() -> page.getByText("#12312313").click());
//        fileChooser.setFiles(Paths.get("/sample/image.image.png"));
//
//        ///alert,,confirm and popup
//        //default playwright will dismiss it
//        //you need to firstregister
//        // alert
//        page.onDialog(dialog -> {
//            String msg = dialog.message();
//            Assert.assertTrue(msg.contains("I am a JS Alert"));
//            dialog.accept();
//        });
//        //confirm
//
//        page.onDialog(dialog -> {
//            String msg = dialog.message();
//            Assert.assertTrue(msg.contains("I am a JS Alert"));
//            dialog.accept();
//
//        });
//        page.locator("").click();
//
//        // shoadow done xpath not alowd on css selector are used
//        // shodaow root should be open
//        Locator shadowRoot = page.locator("div#username");
//        shadowRoot.locator("#name").fill("abcde");
//
//        //screenshot
//        page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("sccrenshot.png")))
//        //video
//        browser.newContext(new Browser.NewContextOptions().setRecordVideoSize(500,500).setRecordVideoDir(Paths.get("")));
//        // tracing
//        browserContext1.tracing(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
//    }


@Test
public void verifySocialLinksOpenInNewTabs() {
    page.navigate(ConfigManager.getUIBaseURL());
    List<Locator> socialLinks = page.locator(
            "//div[@class='social']//a"
    ).all();

    for( Locator socialLink : socialLinks){
        socialLink.click();
    }
    List<Page> allPages = browserContext.pages();
    for(Page p: allPages){
        p.bringToFront();
        String title = p.title();

        System.out.println(title);
    }

    }
}
