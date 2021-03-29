import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Queue.*;
import Tree.*;

public class App 
{
    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите какой браузер использовать:");
        System.out.println("1)Firefox\n2)Google Chrome(требуется версия 90)\n3)Opera");
        int choice = scanner.nextInt();
        System.out.println("Какое количество отрисовать: ");
        int number = scanner.nextInt();
        System.out.println("Максимальное количество детей: ");
        int maxChilds = scanner.nextInt();
        System.out.println("Максимальное количество Node в дереве: ");
        int maxNodes = scanner.nextInt();
        scanner.close();
        switch (choice) {
            case 1:
            {
                System.setProperty("webdriver.gecko.driver", ".\\src\\Drivers\\geckodriver.exe");
                if ((number % 4) == 0) 
                {
                    WebDriver[] geckodrivers = {
                        new FirefoxDriver(),
                        new FirefoxDriver(),
                        new FirefoxDriver(),
                        new FirefoxDriver()
                    };
                    generatefor4(number,geckodrivers, maxChilds, maxNodes);
                } 
                else if((number % 3) == 0)
                {
                    WebDriver[] geckodrivers = {
                        new FirefoxDriver(),
                        new FirefoxDriver(),
                        new FirefoxDriver()
                    };
                    generatefor3(number,geckodrivers, maxChilds, maxNodes);
                }
                else if((number % 2) == 0)
                {
                    WebDriver[] geckodrivers = {
                        new FirefoxDriver(),
                        new FirefoxDriver()
                    };
                    generatefor2(number,geckodrivers, maxChilds, maxNodes);
                }
                else 
                {
                    generatefor1(number,new FirefoxDriver() ,maxChilds, maxNodes);
                }
                break;
            }
            case 2:
            {
                System.setProperty("webdriver.chrome.driver", ".\\src\\Drivers\\chromedriver.exe");
                if ((number % 4) == 0) 
                {
                    WebDriver[] chromedrivers = {
                        new ChromeDriver(),
                        new ChromeDriver(),
                        new ChromeDriver(),
                        new ChromeDriver()
                    };
                    generatefor4(number,chromedrivers, maxChilds, maxNodes);
                } 
                else if((number % 3) == 0)
                {
                    WebDriver[] chromedrivers = {
                        new ChromeDriver(),
                        new ChromeDriver(),
                        new ChromeDriver()
                    };
                    generatefor3(number,chromedrivers, maxChilds, maxNodes);
                }
                else if((number % 2) == 0)
                {
                    WebDriver[] chromedrivers = {
                        new ChromeDriver(),
                        new ChromeDriver()
                    };
                    generatefor2(number,chromedrivers, maxChilds, maxNodes);
                }
                else 
                {
                    generatefor1(number,new ChromeDriver() ,maxChilds, maxNodes);
                }
                break;
            }       
            case 3:
            {
                System.setProperty("webdriver.opera.driver", ".\\src\\Drivers\\operadriver.exe");
                if ((number % 4) == 0) 
                {
                    WebDriver[] operadrivers = {
                        new OperaDriver(),
                        new OperaDriver(),
                        new OperaDriver(),
                        new OperaDriver()
                    };
                    generatefor4(number,operadrivers, maxChilds, maxNodes);
                } 
                else if((number % 3) == 0)
                {
                    WebDriver[] operadrivers = {
                        new OperaDriver(),
                        new OperaDriver(),
                        new OperaDriver()
                    };
                    generatefor3(number,operadrivers, maxChilds, maxNodes);
                }
                else if((number % 2) == 0)
                {
                    WebDriver[] operadrivers = {
                        new OperaDriver(),
                        new OperaDriver()
                    };
                    generatefor2(number,operadrivers, maxChilds, maxNodes);
                }
                else 
                {
                    generatefor1(number,new OperaDriver() ,maxChilds, maxNodes);
                }
                break;
            }  
        }        
    }

    public static void generatefor4(int number,WebDriver[] drivers, int maxChilds, int maxNodes)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                printFiles(1 ,number/4, drivers[0],maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.submit(() -> {
            try {
                printFiles((number/4)+1 ,(number/4)*2, drivers[1] ,maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executor3 = Executors.newSingleThreadExecutor();
        executor3.submit(() -> {
            try {
                printFiles(((number/4)*2)+1 ,(number/4)*3, drivers[2],maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executor4 = Executors.newSingleThreadExecutor();
        executor4.submit(() -> {
            try {
                printFiles(((number/4)*3) + 1 ,number, drivers[3],maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
        executor2.shutdown();
        executor3.shutdown();
        executor4.shutdown();
    }
    public static void generatefor3(int number ,WebDriver[] drivers, int maxChilds, int maxNodes)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                printFiles(1 ,number/3, drivers[0],maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
        executor2.submit(() -> {
            try {
                printFiles((number/3)+1 ,(number/3)*2, drivers[1] ,maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        ExecutorService executor3 = Executors.newSingleThreadExecutor();
        executor3.submit(() -> {
            try {
                printFiles(((number/3)*2)+1 ,number, drivers[2],maxChilds,maxNodes);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
        executor2.shutdown();
        executor3.shutdown();
    }
    public static void generatefor2(int number ,WebDriver[] drivers, int maxChilds, int maxNodes)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> {
                    try {
                        printFiles(1 ,number/2, drivers[0],maxChilds,maxNodes);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });
        ExecutorService executor2 = Executors.newSingleThreadExecutor();
                executor2.submit(() -> {
                    try {
                        printFiles((number/2)+1 ,number, drivers[1],maxChilds,maxNodes);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });
        executor.shutdown();
        executor2.shutdown();
    }
    public static void generatefor1(int number ,WebDriver driver, int maxChilds, int maxNodes)
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.submit(() -> {
                    try {
                        printFiles(1 ,number, driver,maxChilds,maxNodes);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void printFiles(int number,int max, WebDriver driver, int maxChilds, int maxNodes) throws InterruptedException, IOException
    {
        
        String start = new File(".\\index.html").getAbsolutePath();
        System.out.println(start);
        driver.get(start);
        int count = number;
        while(count<=max)
        {
            WebElement button = driver.findElement(By.xpath("/html/body/div/button"));
            WebElement searchInput = driver.findElement(By.id("txt"));
            WebElement canvas = driver.findElement(By.id("canv")); 
            TimeUnit.SECONDS.sleep(1);
            Tree<Integer> tree = new Tree<>(generateTree(maxChilds,maxNodes));
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return (tree.isEmpty() == false);
                }
            });
            searchInput.click();
            String tmptree = tree.printTree();
            (new WebDriverWait(driver, 25)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return tmptree.length() != 0;
                }
            });
            searchInput.sendKeys(Keys.BACK_SPACE+tmptree);
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.id("txt")).getAttribute("value").length() != 0;
                }
            });
            button.click();
            TimeUnit.MILLISECONDS.sleep(2500);
            String src = canvas.getAttribute("src");
            if(src.isEmpty())
            {
                driver.navigate().refresh();
                continue;
            }
            String base64Image = src.split(",")[1];
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            File outputfile = new File(".\\OutFiles\\"+ "№" + count + " " +tree.calculateAlpha()+".png");
            ImageIO.write(bufferedImage, "png", outputfile); 
            driver.navigate().refresh();
            count++;
        }
        driver.close();
    }
    public static Node<Integer> generateTree(int childLimit, int maxNodes)
    {
        Random rand = new Random();
        Node<Integer> root = new Node<Integer>(1);
        SimpleQueue<Node<Integer>> stack = new SimpleQueue<>();
        stack.add(root);
        int tmp;
        int count = 1;
        while (!stack.isEmpty() && count < maxNodes)
        {
            Node<Integer> node = stack.remove();
            tmp = rand.nextInt(childLimit+1);
            for(int i = count; i < count+tmp;i++)
            {
                Node<Integer> tmpNode = new Node<Integer>(i);
                node.addChild(tmpNode);
                stack.add(tmpNode);
            }
            count+=tmp;
        }
        return root;
    }
    
}
