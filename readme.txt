<Swagger Petstore Test Automation Framework & Tests>
https://petstore.swagger.io/

There are multiple ways to run the tests:
    - Run it directly from the test classes, ind the IDE:
            * src/test/java/pet/CreateUpdateDeletePetTests.class
            * src/test/java/store/CreateGetDeleteOrderTests.class
    - Run the test suite from testng.xml
    - Run with Maven command. They run with "mvn clean install" by default,
            but, for specifically running of the test suite use: "mvn test -DxmlFilePath=src/testng.xml"


Used technologies:
    - language: Java (version: 22.01)
    - build tool: Maven
    - test framework: TestNG (version: 7.10.2)
    - AssertJ (version: 3.26.0)
    - Jackson Databind (version: 2.17.2)
    - Log4j (version: 2.23.1)
    - Rest Assured (version: 5.5.0)
