# Installation
Directly clone project

Make sure all dependencies are downloaded

If you are using different Java SDK, you will need to setup SKD

Under src > test > java > com > betterplace > tests, there is Challange class which contains the test and running against 2 different urls.

You can directly run it from IDE or I added maven surefire plugin to pom.xml which helps to run tests from command line by typing **mvn verify**


## Failing Test?
Second test is failing because of overlapping payment method texts!


# Framework
I have created this project with maven so I keep all dependencies in pom.xml file

I created configuration.properties file, which contains browser type and urls in key-value format.

# Utilities
-I created Driver utility, which is designed as singleton and it supports "chrome","chrome-headless","firefox","firefox-headless","internet explorer" for windows and "edge", "safari" for mac.

-I created ConfigurationReader utility to be able to get the values from configuration.properties file.

-I created ReusableMethods under utilities which contains static methods, so i am able to call them with class name without creating object.

# Pages
I created abstract BasePage, which contains all common webelements and methods and other pages extends it.

I used PageFactory.initElements method to be able to initialize webelements by using @FindBy annotation without using findElement() method.

I created successPage for that specific page which extends base page.


# Tests
I created objects from the pages to be able to use its webelements and methods. 

Since I execute these tests on my local computer, I used waits more than expected to avoid from synchronization issues because of internet and my computer`s speed.

I created TestBase class which contains before and after methods and Challenge test class extends this TestBase to be able to execute these methods before each test.

# Data Driven
I run same test against 2 different urls by using @DataProvider, but is is also possible to run tests for different environments by adding a .xml file

# Report
I used Extent Report dependency, it generates HTML report with test steps and also takes screenshot if test fails!

You can find test report under test-output file, right click on report.html and Open in Browser > Chrome

Under test-oputput > Screenshots folder, you can find screenshots when tests fails.




