Swagger Petstore Test Automation Framework & Tests
https://petstore.swagger.io/

There are multiple ways to run the tests:
    - Run it directly from the test classes, ind the IDE:
            * src/test/java/pet/CreateUpdateDeletePetTests.class
            * src/test/java/store/CreateGetDeleteOrderTests.class
    - Run the test suite from testng.xml
    - Run with Maven command. They run with "mvn clean install" by default,
            but, for specifically running of the test suite use: "mvn test -DxmlFilePath=src/testng.xml"