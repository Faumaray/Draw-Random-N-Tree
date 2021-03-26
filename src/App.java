import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.Scanner;

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

import Queue.SimpleQueue;
import Tree.Node;
import Tree.Tree;

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
                WebDriver driver = new FirefoxDriver();
                printFiles(number, driver,maxChilds,maxNodes);
                break;
            }
            case 2:
            {
                System.setProperty("webdriver.chrome.driver", ".\\src\\Drivers\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                printFiles(number, driver,maxChilds,maxNodes);
                break;
            }       
            case 3:
            {
                System.setProperty("webdriver.opera.driver", ".\\src\\Drivers\\operadriver.exe");
                WebDriver driver = new OperaDriver();
                printFiles(number, driver,maxChilds,maxNodes);
                break;
            }  
        }        
    }
    
    public static void printFiles(int max, WebDriver driver, int maxChilds, int maxNodes) throws InterruptedException, IOException
    {
        String start = new File(".\\index.html").getAbsolutePath();
        System.out.println(start);
        driver.get(start);
        int count = 1;
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
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
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
