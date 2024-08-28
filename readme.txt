<Swagger Petstore API Test Automation Framework & Tests>
https://petstore.swagger.io/

There are multiple ways to run the tests:
    - Run it directly from the test classes, in the IDE:
            * src/test/java/pet/...
            * src/test/java/store/...
    - Run the test suite from testng xml files, in the IDE:
            * src/testng.xml
            * src/testSuites/petSuite.xml
            * src/testSuites/orderSuite.xml
            * src/testSuites/userSuite.xml
    - Run with Maven command:
            * all tests run with "mvn clean install" by default
            * running all the tests directly: "mvn test -DxmlFilePath=src/testng.xml"
            * running the Pet test suite only: "mvn test -DxmlFilePath=src/testSuites/petSuite.xml"
            * running the Order test suite only: "mvn test -DxmlFilePath=src/testSuites/orderSuite.xml"
            * running the Order test suite only: "mvn test -DxmlFilePath=src/testSuites/userSuite.xml"

"mvn clean install" is needed before running any tests.
To run without tests use: "mvn clean install -DskipTests".
You will need to install the Lombok extension to your IDE, or it will have issues with the code.
            You will be able to build and run the code it without it.

Used technologies:
    - Language: Java (version: 22.01)
    - Build Tool: Maven (version: 3.9.6)
    - Test Framework: TestNG (version: 7.10.2)
    - AssertJ (version: 3.26.0)
    - Jackson Databind (version: 2.17.2)
    - Log4j (version: 2.23.1)
    - Rest Assured (version: 5.5.0)
    - Lombok (version: 1.18.34)
