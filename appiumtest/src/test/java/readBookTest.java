import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Created by suixiang on 2017/9/1.
 */

public class readBookTest{
    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception{
        File classpathRoot = new File(System.getProperty("user.dir"));
        //app的目录
        File appDir = new File(classpathRoot, "/src/test/java/apps/");
        //app的名字，对应你apps目录下的文件
        File app = new File(appDir, "pris_generic_new.apk");
        //创建Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置要调试的模拟器的名字
        //capabilities.setCapability("deviceName","192.168.31.211:5555");
        capabilities.setCapability("deviceName","192.168.56.101:5555");
        //设置模拟器的系统版本
        capabilities.setCapability("platformVersion", "5.1.0");
        //设置app的路径
        capabilities.setCapability("app", app.getAbsolutePath());
        //设置app的包名
        capabilities.setCapability("appPackage", "com.netease.pris");
        //设置app的启动activity
        //capabilities.setCapability("appActivity", ".activity.MainGridActivity");
        capabilities.setCapability("appActivity", ".activity.PRISActivityFlasScreen");
        capabilities.setCapability("noReset",true);
        //启动driver
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        //测试完毕，关闭driver，不关闭将会导致会话还存在，下次启动就会报错
        driver.quit();
        //删除云阅读安装包
        ProcessBuilder testDevice = new ProcessBuilder("/Users/suixiang/Library/Android/sdk/platform-tools/adb","uninstall","com.netease.pris");
        Process testDeviceP = testDevice.start();
        BufferedReader testDeviceInput = new BufferedReader(new InputStreamReader(testDeviceP.getInputStream()));
        String s1 = null;
        while ((s1 = testDeviceInput.readLine()) != null) {
            System.out.println(s1);
        }
        System.out.println("云阅读已删除安装包");
    }

    /**
     * 要执行的的测试方法
     */
    @Test
    public void ReadBookTest()throws IOException {
        FileWriter fw = new FileWriter("/Users/marvin/AndroidStudioProjects/uiTest/appiumtest/src/test/java/outtxt/scene");
        String outScene;
        try{
            Thread.sleep(10000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        outScene = System.currentTimeMillis()+","+"开始执行自动化用例了哦"+"\r\n";
        fw.write(outScene);
        /*try{
            WebElement dialogUpgrade = driver.findElementById("com.netease.pris:id/dialog_message");
            if(dialogUpgrade.getText().matches("网易云阅读已经为您准备好最新版本，是否立即安装？")){
                WebElement cancel = driver.findElementById("com.netease.pris:id/button_negative");
                cancel.click();
                outScene = System.currentTimeMillis()+","+"点击了取消立即安装"+"\r\n";
                fw.write(outScene);
                fw.flush();
            }
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }*/
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();
        driver.findElementByXPath(".//*[@text='书架']").click();
        outScene = System.currentTimeMillis()+","+"点击底部书架tab"+"\r\n";
        fw.write(outScene);
        fw.flush();
        //WebElement firstBook = driver.findElementByXPath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.support.v4.widget.DrawerLayout[1]/android.widget.RelativeLayout[1]/android.support.v4.view.ViewPager[1]/android.widget.RelativeLayout[1]/android.view.View[2]/android.widget.ListView[1]/android.widget.LinearLayout[1]");
        //firstBook.click();

        driver.findElementByXPath(".//*[@text='未读']").click();
        outScene = System.currentTimeMillis()+","+"点击书架上的第一本书"+"\r\n";
        fw.write(outScene);
        fw.flush();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        for(int i = 0;i<5;i++){
            driver.tap(1,width/2,height/2,500);
            outScene = System.currentTimeMillis()+","+"点击屏幕中间1次"+"\r\n";
            fw.write(outScene);
            fw.flush();
        }

        for (int j = 0; j < 60; j++) {
            driver.swipe(width * 3 / 4, height / 2, width / 4, height / 2, 1000);
            outScene = System.currentTimeMillis()+","+"翻页第"+j+"次"+"\r\n";
            fw.write(outScene);
            fw.flush();
        }
        outScene = System.currentTimeMillis()+","+"UI自动化执行结束啦";
        fw.write(outScene);
        fw.flush();
    }
}
