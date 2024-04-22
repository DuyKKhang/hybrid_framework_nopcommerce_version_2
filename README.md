Structure framework 
+ actions
  - Manager modules, functions use commons
  - commons: Manage functions commonly use in test case
  - AbstractPage: wrapper Selenium AIP, use in package PageObjects
  - AbstractTest: use in pacKage Test Case
  - Global Constants: URL Server
  - PageObjects: manage actions each page
    +  All page in project (each page manage one actions that page itself)
+ test cases
  - Manager modules, functions, test case
+ resources
  - Manage external file for the whole framework (testNG, .xml, log4j, Excel)
+ interfaces
  - Manage UI/Locator in project 
+ Folder difference (Third Party Tool)
  - Libraries
  - uploadfiles
  - browserLogf
  - browserDriver
