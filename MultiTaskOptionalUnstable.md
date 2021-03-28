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