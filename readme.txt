<Swagger Petstore Test Automation Framework & Tests>
https://petstore.swagger.io/

There are multiple ways to run the tests:
    - Run it directly from the test classes, in the IDE:
            * src/test/java/pet/...
            * src/test/java/store/...
    - Run the test suite from testng xml files, in the IDE:
            * src/testng.xml
            * src/testsuites/petSuite.xml
    - Run with Maven command:
            * all tests run with "mvn clean install" by default
            * running all the tests directly: "mvn test -DxmlFilePath=src/testng.xml"
            * running the Pet test suite only: "mvn test -DxmlFilePath=src/testsuites/petSuite.xml"

"mvn clean install" is needed before running any tests.

Used technologies:
    - language: Java (version: 22.01)
    - build tool: Maven (version: 3.9.6)
    - test framework: TestNG (version: 7.10.2)
    - AssertJ (version: 3.26.0)
    - Jackson Databind (version: 2.17.2)
    - Log4j (version: 2.23.1)
    - Rest Assured (version: 5.5.0)
    - Lombok (version: 1.18.34)
