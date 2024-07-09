There are multiple ways to run the tests:
    - Run it directly from the test classes:
            * src/test/java/Pet/CreateUpdateDeletePetTests.class
    - Run the test suite from testng.xml
    - Run with Maven command. They run with "mvn clean install" by default,
            but, for specifically running of the test suite use: "mvn test -DxmlFilePath=src/testng.xml"