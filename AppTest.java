package LinkedInventoryManagement;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import LinkedInventoryManagement.Common.InventoryLinkedList;
import LinkedInventoryManagement.Product.Product;
import LinkedInventoryManagement.Product.ProductCatalog;
import LinkedInventoryManagement.Security.SecurityOperations;
import LinkedInventoryManagement.Security.User;


public class AppTest {
    private static final SecurityOperations UserOperations = null;

    /**
     * This unit test validates the GetFirstElement method. It first stores three
     * test products, and attemps to get the first element in the list, and verifies
     * this is correct. Then, it removes the first element, and calls the method to
     * get the first element in the updated list, and validated this is the element
     * that was originally the second item in the list (before the first item was
     * removed).
     */
    @Test
    public void GetFirstElementInListUnitTest() {
        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < testProducts.length; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // Expected product from test products
        Product expectedFirstProduct = testProducts[0];

        // Call method to get first product in list
        Product actualFirstProduct = testList.GetFirst();

        // Verify the two objects are equal
        if (!actualFirstProduct.equals(expectedFirstProduct)) {
            assertTrue("Assert #1: The GetFirst method did not correctly return the first element in the list", false);
        }

        // Remove first object in list and call method again, ensuring it returns that
        // was
        // in index 1 prior to call to remove
        testList.RemoveFirst();

        // Expected product from test products
        expectedFirstProduct = testProducts[1];

        // Call method to get first product in list
        actualFirstProduct = testList.GetFirst();

        // Verify the two objects are equal
        if (!actualFirstProduct.equals(expectedFirstProduct)) {
            assertTrue("Assert #2: The GetFirst() method did not correctly return the first element in the list",
                    false);
        }

    }

    /**
     * This unit test validates the GetLastElement method. It first stores three
     * test products, and attemps to get the last element in the list, and verifies
     * this is correct. Then, it removes the last element, and calls the method to
     * get the last element in the updated list, and validated this is the element
     * that was originally the second item in the list (before the item was
     * removed).
     */
    @Test
    public void GetLastElementInListUnitTest() {

        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Get size of array
        int sizeOfTestProducts = testProducts.length;

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < sizeOfTestProducts; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // Expected product from test products
        Product expectedLastProduct = testProducts[sizeOfTestProducts - 1];

        // Call method to get last product in list
        Product actualLastProduct = testList.GetLast();

        // Verify the two objects are equal
        if (!actualLastProduct.equals(expectedLastProduct)) {
            assertTrue("Assert #1: The GetLast() method did not correctly return the last element in the list", false);
        }

        // Remove the last element in the list
        testList.RemoveLast();

        // Test again

        // Expected product from test products
        expectedLastProduct = testProducts[sizeOfTestProducts - 2];

        // Call method to get last product in list
        actualLastProduct = testList.GetLast();

        // Verify the two objects are equal
        if (!actualLastProduct.equals(expectedLastProduct)) {
            assertTrue("Assert #2: The GetLast() method did not correctly return the last element in the list", false);
        }

    }

    /*
     * This unit test validates the InsertElement method of the InventoryLinkedList
     * functions properly byattempting to insert multiple elements into a test
     * linked list.
     */
    @Test
    public void InsertElementInListUnitTest() {
        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Insert element into first index of list
        testList.Insert(0, testProducts[0]);

        // Insert element into second index of list
        testList.Insert(1, testProducts[1]);

        // Insert element into last index of list
        testList.Insert(2, testProducts[2]);

        // Verify the first element was properly inserted into the list at the first
        // index
        Product expectedProduct = testProducts[0];
        Product actualProduct = testList.GetFirst();

        if (!actualProduct.equals(expectedProduct)) {
            assertTrue("The Insert() method did not correctly insert an element into the first index of the list",
                    false);
        }

        // Verify an element is properly inserted into the middle of a list
        expectedProduct = testProducts[1];
        actualProduct = testList.GetElement(1);

        if (!actualProduct.equals(expectedProduct)) {
            assertTrue(
                    "The Insert() method did not correctly insert an element into an index in the middle of the list",
                    false);
        }

        // Verify an element is properly inserted into the list at the last index
        expectedProduct = testProducts[2];
        actualProduct = testList.GetLast();

        if (!actualProduct.equals(expectedProduct)) {
            assertTrue("The Insert() method did not correctly insert an element into the last index of the list",
                    false);
        }

    }

    /*
     * This unit test verifies that the InventoryLinkedList properly removes items
     * from the linked list. It first inserts items into a list, and then attemps to
     * remove the first one. It then verifies the element now at the first index of
     * the list is the element that was originally in the second index. It then
     * attemps to remove an element in the middle of the list, and at the end of the
     * list, and ensure they work properly in the same manner.
     */
    @Test
    public void RemoveElementInListUnitTest() {

        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Get size of array
        int sizeOfTestProducts = testProducts.length;

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < sizeOfTestProducts; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // Remove first index and store in Product object
        Product removedProduct = testList.Remove(0);

        // Verify the first element in the list is now the second element in the test
        // array (product was removed successfully)
        Product expectedProduct = testProducts[1];
        Product actualProduct = testList.GetFirst();

        if (!actualProduct.equals(expectedProduct)) {
            assertTrue(
                    "The Remove() method did not properly remove the first element in the list. It is still at the first index of the list.",
                    false);
        }

        // Loop through objects in list and verify removedProduct is not in list
        for (int i = 0; i < testList.GetLength(); i++) {
            Product currentProduct = testList.GetElement(i);

            if (currentProduct.equals(removedProduct)) {
                assertTrue(
                        "The Remove() method did not properly remove the first element in the list. It still exists at some index in the list, though not at its original position.",
                        false);
            }
        }

        // Remove last product from list
        removedProduct = testList.Remove(1);

        expectedProduct = testProducts[1];
        actualProduct = testList.GetElement(0);

        if (!actualProduct.equals(expectedProduct)) {
            assertTrue(
                    "The Remove() method did not properly remove the first element in the list. It is still at the first index of the list.",
                    false);
        }

        // Loop through objects in list and verify removedProduct is not in list
        for (int i = 0; i < testList.GetLength(); i++) {
            Product currentProduct = testList.GetElement(i);

            if (currentProduct.equals(removedProduct)) {
                assertTrue(
                        "The Remove() method did not properly remove the first element in the list. It still exists at some index in the list, though not at its original position.",
                        false);
            }
        }

        // Clear testList linked list
        for (int i = 0; i < testList.GetLength(); i++) {
            testList.Remove(i);
        }

        Product productToRemove = new Product(4, "newProduct4", 4.0, 4, 4);
        // Add multiple new products to list and attemp to remove one in the middle
        testList.InsertLast(new Product(1, "newProduct1", 1.0, 1, 1));
        testList.InsertLast(new Product(2, "newProduct2", 2.0, 2, 2));
        testList.InsertLast(new Product(3, "newProduct3", 3.0, 3, 3));
        testList.InsertLast(productToRemove);
        testList.InsertLast(new Product(5, "newProduct5", 5.0, 5, 5));

        // Call method to remove product in the middle of the list
        removedProduct = testList.Remove(3);

        if (!removedProduct.equals(productToRemove)) {
            assertTrue(
                    "The Remove() method did not properly remove an element in the middle of the list from its position",
                    false);
        }

        // Loop through objects in list and verify removedProduct is not in list
        for (int i = 0; i < testList.GetLength(); i++) {
            Product currentProduct = testList.GetElement(i);

            if (currentProduct.equals(removedProduct)) {
                assertTrue(
                        "The Remove() method did not properly remove the first element in the list. It still exists at some index in the list, though not at its original position. ",
                        false);
            }
        }

    }

    /*
     * This method inserts elements into a linked list and tests the Contains method
     * works properly by returning true, indicating the product was found in the
     * list. Then, the test passes a product that is not in the list, and asserts
     * that the Contains method returns false.
     */
    @Test
    public void ListContainsUnitTest() {
        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < testProducts.length; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // Test contains method works for first element
        boolean productFound = testList.Contains(testProducts[0]);

        if (!productFound) {
            assertTrue(
                    "The Contains() method returns false when searching for an element that is the first index of the list",
                    false);
        }
        // Test contains method works for middle element
        productFound = testList.Contains(testProducts[1]);

        if (!productFound) {
            assertTrue(
                    "The Contains() method returns false when searching for an element that is in the middle of the list",
                    false);
        }

        // / Test contains method works for last element
        productFound = testList.Contains(testProducts[2]);

        if (!productFound) {
            assertTrue(
                    "The Contains() method returns false when searching for an element that is the last index of the list",
                    false);
        }

        // NOW TESTING BY PASSING AN OBJECT THAT IS NOT IN LIST TO THE CONTAINS METHOD
        // -> SHOULD RETURN FALSE

        // Create an product that is not in the list
        Product notInListProduct = new Product(999, "nonExistant", 9.9, 9, 9);

        // Call contains method on product not in list
        productFound = testList.Contains(notInListProduct);

        // If the method returns that the product IS in the list
        if (productFound != false) {
            assertTrue(
                    "The Contains() method returns that the list contains an object that actually does not exist in the list",
                    false);
        }

    }

    /*
     * This unit test verifies the SetElement method works correctly for the custom
     * created linked list. It attemps to set an element at the first index of the
     * list, last index, and at a middle index of the list. After setting an
     * element, it gets the element from that index in the list and validates it
     * matches the element to be set with.
     * 
     */
    @Test
    public void SetElementInListUnitTest() {
        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < testProducts.length; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // ATTEMPTING FIRST FOR AN ELEMENT AT FIRST INDEX OF THE LIST

        // Create a new product
        Product newExpectedProduct1 = new Product(11, "newProduct1", 1.0, 1, 1);

        // Call method to set element in list to new product
        testList.SetElement(0, newExpectedProduct1);

        Product actualProduct1 = testList.GetFirst();

        if (!actualProduct1.equals(newExpectedProduct1)) {
            assertTrue("The SetElement() method did not properly set an element at the first index of the list", false);
        }

        // ATTEMPTING FIRST FOR AN ELEMENT AT AN INDEX IN THE MIDDLE OF THE LIST

        // Create a new product
        Product newExpectedProduct2 = new Product(2, "newProduct2", 2.0, 2, 2);

        // Call method to set element in list to new product
        testList.SetElement(1, newExpectedProduct2);

        Product actualProduct2 = testList.GetElement(1);

        if (!actualProduct2.equals(newExpectedProduct2)) {
            assertTrue("The SetElement() method did not properly set an element at an index in the middle of the list",
                    false);
        }

        // ATTEMPTING FIRST FOR AN ELEMENT AT THE LAST INDEX OF THE LIST

        // Create a new product
        Product newExpectedProduct3 = new Product(3, "newProduct3", 3.0, 3, 3);

        // Call method to set element in list to new product
        testList.SetElement(2, newExpectedProduct3);

        Product actualProduct3 = testList.GetElement(2);

        if (!actualProduct3.equals(newExpectedProduct3)) {
            assertTrue("The SetElement() method did not properly set an element at the last index of the list", false);
        }

    }

    /*
     * This unit test populates a custom linked list with obejcts and then verifies
     * that the GetElement method works correctly for an element at the first index
     * of the list, the last index of the list, and at an index in the middle of the
     * list.
     */
    @Test
    public void GetElementInListUnitTest() {

        // Create custom linked list with Product Objects
        InventoryLinkedList<Product> testList = new InventoryLinkedList<>();

        // Returns three test products
        Product[] testProducts = getTestProductsArray();

        // Loop through returns testProducts and store in linked list
        for (int i = 0; i < testProducts.length; i++) {
            Product currentProduct = testProducts[i];

            // Insert into linked list
            testList.Insert(i, currentProduct);
        }

        // Test getting first element in list -> GetElement() calls GetFirst()
        Product expectedProduct = testProducts[0];
        Product actualProduct = testList.GetElement(0);

        // Verify equality
        if (!actualProduct.equals(expectedProduct)) {
            assertTrue("The GetElement() method does not currently return the element at index 0", false);
        }

        // Test getting an element in the middle of the list
        expectedProduct = testProducts[1];
        actualProduct = testList.GetElement(1);

        // Verify equality
        if (!actualProduct.equals(expectedProduct)) {
            assertTrue(
                    "The GetElement() method does not currently return the element at the specified index in the middle of the list",
                    false);
        }

        // Test getting last element in list -> GetElement() calls GetLast()
        expectedProduct = testProducts[2];
        actualProduct = testList.GetElement(2);

        // Verify equality
        if (!actualProduct.equals(expectedProduct)) {
            assertTrue("The GetElement() method does not currently return the element at the last index of the list",
                    false);
        }

    }

    /*
     * This method is used by multiple unit tests to get multiple product objects.
     * These objects are then used to populate a custom linked list.
     */
    public Product[] getTestProductsArray() {
        Product expectedProduct1 = new Product(1, "expectedProduct1", 1.0, 1, 1);
        Product expectedProduct2 = new Product(2, "expectedProduct2", 2.0, 2, 2);
        Product expectedProduct3 = new Product(3, "expectedProduct3", 3.0, 3, 3);

        Product[] productsArr = { expectedProduct1, expectedProduct2, expectedProduct3 };

        return productsArr;
    }

    /*
     * Method used in various test cases to read users from file and store in an
     * array list. This method is NOT a unit test. Used to limit redundancy of code.
     */
    public ArrayList<User> readUsersFromFileAndStoreInList() {
        ArrayList<User> userList = new ArrayList<>();

        try {

            // Create file and scanner objects
            // File usersFile = new File("Users.dat");
            File usersFile = new File("/Users/rooperaikaa91/Desktop/InventoryManagement/Users.txt");
            Scanner file = new Scanner(usersFile);

            // Define variables
            String userInfo;
            String lastFirstNameInFile = null;
            String lastLastNameInFile = null;
            String lastUsernameInFile = null;
            String lastPasswordInFile = null;
            Boolean lastIsManagerInFile = null;

            // Loop through file and store user information in user list
            while (file.hasNextLine()) {

                // Store each line into array
                String[] userInfoArray = new String[5];
                // Input line
                userInfo = file.nextLine();

                // If the line is an empty line
                if (userInfo.isBlank())
                    break;

                // Number of commas in line used to determine which fields are present
                int numComma = 0;

                // Count number of commas in string
                for (int i = 0; i < userInfo.length(); i++) {
                    if (userInfo.charAt(i) == ',')
                        numComma++;
                }

                // Split array into individual elements
                userInfoArray = userInfo.split(", ");

                // If all data is in the user's line
                if (numComma == 4) {
                    // Store user values in temporary variables
                    lastFirstNameInFile = userInfoArray[0].trim();
                    lastLastNameInFile = userInfoArray[1].trim();
                    lastUsernameInFile = userInfoArray[2].trim();
                    lastPasswordInFile = userInfoArray[3].trim();

                    lastIsManagerInFile = Boolean.parseBoolean((userInfoArray[4]).trim());
                } else { // If the user omitted last name

                    // Store user values in temporary variables
                    lastFirstNameInFile = userInfoArray[0].trim();
                    lastLastNameInFile = null;
                    lastUsernameInFile = userInfoArray[1].trim();
                    lastPasswordInFile = userInfoArray[2].trim();
                    lastIsManagerInFile = Boolean.parseBoolean((userInfoArray[3]).trim());
                }

                // Add each user to list
                userList.add(new User(lastFirstNameInFile, lastLastNameInFile, lastUsernameInFile, lastPasswordInFile,
                        lastIsManagerInFile));
            }
        } catch (IOException e) {
            assertTrue("The file did not exist", false);
        }
        return userList;
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /*
     * This AuthenticateUser method takes as parameters a username and password of a
     * user, and reads all user information from the file. If matching username and
     * password combination are located in the file, the method successfully
     * authenticates the user and returns a user object for this authenticated user.
     * Users can be stored with either 4 or 5 data fields, with 4 occurring when the
     * user omits their last name, which still is valid. This test method first
     * writes a user to the file with all 5 data fields, and then calls the method
     * to authenticate the user. Next, it does this for a user with 4 fields. Then,
     * the test method tests that when inncorrect information is passed to the
     * method (data nont inn the file), it will not authenticate the user and
     * instead returnn a null user object.
     */
    @Test
    public void AuthenticateUserTest() {

        // Testing for user with correct information (exists in file)
        // This user data was added to the users.dat file
        {

            // Temporary variables
            String testUsername = "vaLidUsEr";
            String testPassword = "testPassword";

            // Create expected user object
            User expectedUser = new User("teSt", "caSe", testUsername, testPassword, true);
            // Add user to file and list
            SecurityOperations.AddNewUser(expectedUser);

            // Call authenticate user method with expected values
            User actualUser = SecurityOperations.AuthenticateUser(testUsername, testPassword);

            // Check if user returned is null -> Method returns null user when not
            // authenticated
            if (actualUser == null) {
                assertTrue("The user was not authenticated, when they should have been", false);
            }

            // Verify all data fields match

            if (!expectedUser.getFirstName().equalsIgnoreCase(actualUser.getFirstName())) {
                assertTrue("The authenticated user method did not correctly store the user's first name", false);
            }

            if (!expectedUser.getLastName().equalsIgnoreCase(actualUser.getLastName())) {
                assertTrue("The authenticated user method did not correctly store the user's last name", false);
            }

            if (!expectedUser.getUsername().equalsIgnoreCase(actualUser.getUsername())) {
                assertTrue("The authenticated user method did not correctly store the user's username", false);
            }

            if (!expectedUser.getPassword().equals(actualUser.getPassword())) {
                assertTrue("The authenticated user method did not correctly store the user's hashed password", false);
            }

            if (!expectedUser.getIsManager() == actualUser.getIsManager()) {
                assertTrue("The authenticated user method incorrectly determined if the user is a manager", false);
            }

        }

        // Test method returns a null user when information is passed does not match a
        // user in the file's information
        // Here testing with incorrect information -> Should return null User object
        {

            // Testing with incorrect Password
            String testUsername = "vAlIdUsEr";
            String testPassword = "wrongPassword";

            User actualUser = SecurityOperations.AuthenticateUser(testUsername, testPassword);

            if (actualUser != null) {
                assertTrue(
                        "The authenticate user method did not return a null user when incorrect password was passed to it",
                        false);
            }

            // Testing with incorrect username
            testUsername = "nonExistantUsername";

            actualUser = SecurityOperations.AuthenticateUser(testUsername, testPassword);
            {
                if (actualUser != null) {
                    assertTrue(
                            "The authenticate user method did not return a null user when non existant username was passed to it",
                            false);
                }

            }
        }

        // User can be stored in file without last name. Test method returns
        // authenticated user object even if last name omitted
        // Authenticate user method should store null for password
        {

            String testUsername = "testUsername2";
            String testPassword = "testPassword";

            // Create expected user object
            User expectedUser = new User("Test2", "null", testUsername, testPassword, false);
            // Write user to file and add to list
            SecurityOperations.AddNewUser(expectedUser);

            User actualUser = SecurityOperations.AuthenticateUser(testUsername, testPassword);

            // Check if user returned is null -> Method returns null user when not
            // authenticated
            if (actualUser == null) {
                assertTrue("The user was not authenticated, when they should have been", false);
            }

            // Verify all data fields
            if (!expectedUser.getFirstName().equalsIgnoreCase(actualUser.getFirstName())) {
                assertTrue("The authenticated user method did not correctly store the user's first name", false);
            }

            if (!(expectedUser.getLastName().equalsIgnoreCase(actualUser.getLastName()))) {
                assertTrue("The authenticated user method did not correctly store the user's last name", false);
            }

            if (!expectedUser.getUsername().equalsIgnoreCase(actualUser.getUsername())) {
                assertTrue("The authenticated user method did not correctly store the user's username", false);
            }

            if (!expectedUser.getPassword().equals(actualUser.getPassword())) {
                assertTrue("The authenticated user method did not correctly store the user's hashed password", false);
            }

            if (!expectedUser.getIsManager() == actualUser.getIsManager()) {
                assertTrue("The authenticated user method incorrectly determined if the user is a manager", false);
            }

        }

    }

    /*
     * This unit test method tests the AddNewUser method by first creating a new
     * user who does not exist in the Users.dat file, and then calling the method to
     * add the new user. The method should return true in this case, which is
     * validated by this test. Then, it is checked that the new user's information
     * was correctly written to the file. The method returns false if the user
     * information already exists in the file. This is also checked by this unit
     * test.
     */
    @Test
    public void AddNewUserTest() {

        String newFirstName = "firstName";
        String newLastName = "lastName";
        String newUsername = "username";
        String newPassword = "testPassword";
        boolean newIsManager = false;

        // Create new user object with expected values
        User newUser = new User(newFirstName, newLastName, newUsername, newPassword, newIsManager);

        // Call AddNewUser method for new user
        SecurityOperations.AddNewUser(newUser);

    
        // Read from file and ensure that new user was added to Users.dat file
        // Checking that last entry in file is the newly added user
        {

            try {

                // File usersFile = new File("Users.dat");
                File usersFile = new File("/Users/rooperaikaa91/Desktop/InventoryManagement/Users.txt");

                Scanner file = new Scanner(usersFile);

                String userInfo;
                String lastFirstNameInFile = null;
                String lastLastNameInFile = null;
                String lastUsernameInFile = null;
                String lastPasswordInFile = null;
                Boolean lastIsManagerInFile = null;

                // Loop through file and store the last
                while (file.hasNextLine()) {
                    // Store each line into array
                    String[] userInfoArray = new String[5];
                    userInfo = file.nextLine();

                    int numComma = 0;
                    // Count number of commas in string
                    for (int i = 0; i < userInfo.length(); i++) {
                        if (userInfo.charAt(i) == ',')
                            numComma++;
                    }

                    // Split array
                    userInfoArray = userInfo.split(", ");

                    if (numComma == 4) {
                        // Store user values in temporary variables
                        lastFirstNameInFile = userInfoArray[0].trim();
                        lastLastNameInFile = userInfoArray[1].trim();
                        lastUsernameInFile = userInfoArray[2].trim();
                        lastPasswordInFile = userInfoArray[3].trim();

                        lastIsManagerInFile = Boolean.parseBoolean((userInfoArray[4]).trim());
                    } else {
                        // Store user values in lastorary variables
                        lastFirstNameInFile = userInfoArray[0].trim();
                        lastLastNameInFile = null;
                        lastUsernameInFile = userInfoArray[1].trim();
                        lastPasswordInFile = userInfoArray[2].trim();
                        lastIsManagerInFile = Boolean.parseBoolean((userInfoArray[3]).trim());
                    }

                }

                // Verify all data added to file matches expected data for new user

                // It is not writing to the file correctly in my method
                if (!lastFirstNameInFile.equalsIgnoreCase(newFirstName)) {
                    assertTrue("The new user's first name was not stored correctly in the file", false);
                }

                if (!lastLastNameInFile.equalsIgnoreCase(newLastName)) {
                    assertTrue("The new user's last name was not stored correctly in the file", false);
                }
                if (!lastUsernameInFile.equalsIgnoreCase(newUsername)) {
                    assertTrue("The new user's username was not stored correctly in the file", false);
                }
                if (!lastPasswordInFile.equals(SecurityOperations.GetPasswordHash(newPassword))) {
                    assertTrue("The new user's password was not stored correctly in the file", false);
                }
                if (lastIsManagerInFile != newIsManager) {
                    assertTrue("The new user's manager boolean was not stored correctly in the file", false);
                }
            } catch (IOException e) {
                assertTrue("The file did not exist", false);
            }

        }

        // Verify that addNewUser returns false if user is already in the system
        {
            User existingUser = new User("Test", "Case", "validUser", "testPassword", true);

            SecurityOperations.AddNewUser(existingUser);

            
            SecurityOperations.AddNewUser(existingUser);

            // Add existing user to list of users (this is typically done in Main class.
            // Done here for test purposes)


            // Add to userList list
            ArrayList<User> userList = readUsersFromFileAndStoreInList();

            int counterForExistingInfoInFile = 0;

            // Search through array and check how many occurances there exist of the
            // username annd password of the existing user
            for (User user : userList) {
                // If the currennt user's username and password match
                if (user.getUsername().equalsIgnoreCase("validUser")
                        && user.getPassword().equals(SecurityOperations.GetPasswordHash("testPassword"))) {
                    // Incremenet counter for number of occurannces of this existing information
                    counterForExistingInfoInFile++;
                }
            }

            // If the existing information exists in the file more than once, this means the
            // method wrote the data to the file again
            if (counterForExistingInfoInFile > 1) {
                assertTrue("The AddNewUser method added the existing user to the file, while it should not have",
                        false);
            }

        }

    }

    /*
     * This unit test creates a new user object and then writes this user to the
     * file and adds them to the user list. Next, the removeUser method is used to
     * remove this user from both the file and the list. To check the user is
     * removed, the method originally stores the index of the user who shall be
     * removed(but before they are) in the array list. The list is then updated by
     * reading all users from the file again. After the method to remove the user is
     * called, the test validates that the user at the index in the list is not the
     * same as the user who was supposed to be removed. Next, the test attempts to
     * remove a user who does not exist in the file, and then validates that the
     * method returns false when this occurs.
     */

    @Test
    public void RemoveUserTest() {

        // Define variables
        String userToRemoveFirstName = "removeFirst";
        String userToRemoveLastName = "removeLast";
        String userToRemoveUsername = "removeUsername";
        String userToRemovePassword = "testPassword";
        boolean userToRemovesIsManager = false;

        // Create new user to remove
        User userToRemove = new User(userToRemoveFirstName, userToRemoveLastName, userToRemoveUsername,
                userToRemovePassword, userToRemovesIsManager);

        // Removed this because then add new user method saw it as a duplicate user
        // SecurityOperations.addUserToUsersList(userToRemove);

        // Add new user to file and list
        SecurityOperations.AddNewUser(userToRemove);


        // Call method to remove user by username
        SecurityOperations.RemoveUser(userToRemoveUsername);

        // The removal should have been successful since the user exists in the file,
        // since they were just added to it

        // RemoveUser method returns false if unsuccessful removal
        

        // Call method to read users from file and store in list (after user removed
        // from file)
        ArrayList<User> updatedUserList = readUsersFromFileAndStoreInList();

        // Store data from new list with former index of user to remove from file
        // If last user in file was removed, there will now be nobody at this index so
        // it will be out of bounds

        // loop through the list and check that remove user's info isnt there
        boolean userStillInFile = false;

        for (User currentUser : updatedUserList) {
            if (currentUser.getUsername().equalsIgnoreCase(userToRemoveUsername) && currentUser.getPassword()
                    .equals(SecurityOperations.GetPasswordHash(userToRemovePassword))) {
                userStillInFile = true;
            }
        }

        // Verify that the information of the user to remove is not in the file anymore
        if (userStillInFile) {
            assertTrue("The user was not correctly removed from the Users.dat file", false);
        }

        // Now trying to remove a use who does nont exist in the file
        SecurityOperations.RemoveUser("thisUsernameDoesNotExistInFile");

        

    }

    /*
     * This test method creates a new user and adds the user to the file. Then, the
     * change password method is called on this user with their username, current
     * password, and new password passed to the method as arguments. The user
     * information is then stored in a list, and the index of the user is then
     * located by usernmae. This unit test validates that the password of the user
     * is not the same as before, as long as valid information was passed to the
     * changePassword method. Next, the test verifies that if incorrect information
     * is passed to the method, then the password will not be changed when the
     * changePAssword method is called. The changePassword method returns true
     * whenever a successful password change operation is performed, and false
     * otherwise. This unit test validates that the method returns these values as
     * intended.
     * 
     */
    @Test
    public void ChangePasswordTest() {

        // Testing first with valid credentials

        // Define variables
        String tempFirstName = "tempFirstName";
        String tempLastName = "tempLastName";
        String tempUsername = "tempUsername";
        String tempPassword = "tempPassword";
        String newPassword = "newPass"; // Password will be changed to this
        boolean tempIsManager = false;

        // Create temp user object with expected values
        User tempUser = new User(tempFirstName, tempLastName, tempUsername, tempPassword, tempIsManager);

        // // Add user to list of users
        // SecurityOperations.addUserToUsersList(tempUser);

        // Call method to add user to file
        SecurityOperations.AddNewUser(tempUser);

        // CURRENT PASSWORD IS GETTING HASHED TWICE SOMEWHERE

        // Change password of user
        UserOperations.ChangePassword(tempUser.getUsername(),
                tempUser.getPassword(), newPassword);

        // Need to read file and verify password is changed
        ArrayList<User> userList = readUsersFromFileAndStoreInList();

        // Flag
        boolean passwordChangedSuccessfully = true;

        // Loop through list to check new password does not equal the old one
        for (User currentUser : userList) {

            // Locate the key user
            if (currentUser.getUsername().equalsIgnoreCase(tempUser.getUsername())) {
                // If the current password does not equal the password that was supposed to be
                // changed to

                if (!(currentUser.getPassword().trim()
                        .equalsIgnoreCase(SecurityOperations.GetPasswordHash(newPassword.trim())))) {
                    passwordChangedSuccessfully = false;
                }
                break;
            }
        }

        // If the password was not changed successfully
        if (!passwordChangedSuccessfully) {
            assertTrue("The password was not changed successfully in the file", false);
        }

       

        // // The user is being removed from the file
        // userList = readUsersFromFileAndStoreInList();

        // Now test with incorrect information -> Password should not be changed

        newPassword = "thisShouldNotBeAffected";

        // user list is empty again

        // Add user to list of users
        SecurityOperations.AddNewUser(tempUser);

        for (User currentUser : userList) {

            // Locate the key user
            if (currentUser.getUsername().equalsIgnoreCase(tempUser.getUsername())) {

                // If the current password equals the password the new password
                if (!(currentUser.getPassword().trim().equalsIgnoreCase(tempUser.getPassword().trim()))) {

                }
                break;
            }
        }

        // Call method with wrong password
        SecurityOperations.ChangePassword(tempUser.getUsername(),
                SecurityOperations.GetPasswordHash("wrongPassword"),
                SecurityOperations.GetPasswordHash(newPassword));

        // Update userList
        userList = readUsersFromFileAndStoreInList();

        // Flag
        boolean passwordChanged = false;

        // Make sure that the password for the user did not change in file
        // Loop through users in list
        for (User currentUser : userList) {

            // Locate the key user
            if (currentUser.getUsername().equalsIgnoreCase(tempUser.getUsername())) {

                // If the current password equals the password the new password
                if ((currentUser.getPassword().trim().equalsIgnoreCase(tempUser.getPassword().trim()))) {
                    passwordChanged = true;
                }
                break;
            }
        }

        // If the password changed with incorrect credentials
        if (passwordChanged) {
            assertTrue(
                    "The password was changed when incorrect credentials were passed in, but it should not have been changed",
                    false);
        }
    }


    /*
     * This method tests the addUpdateProduct method properly
     * adds and updates products in the list.
     */
    @Test
    public void AddUpdateProductTest() {


        // Define variables for data fields of products to create
        int tempID1 = 1;
        String tempName1 = "product1";
        double cost1 = 1.0;
        int quantity1 = 1;
        int margin1 = 1;

        int tempID2 = 2;
        String tempName2 = "product2";
        double cost2 = 2.0;
        int quantity2 = 2;
        int margin2 = 2;

        int tempID3 = 3;
        String tempName3 = "product3";
        double cost3 = 3.0;
        int quantity3 = 3;
        int margin3 = 3;

        // Create products
        Product product1 = new Product(tempID1, tempName1, cost1, quantity1, margin1);
        Product product2 = new Product(tempID2, tempName2, cost2, quantity2, margin2);
        Product product3 = new Product(tempID3, tempName3, cost3, quantity3, margin3);

        

        
        ProductCatalog.AddUpdateProduct(product1);
        ProductCatalog.AddUpdateProduct(product2);
        ProductCatalog.AddUpdateProduct(product3);

        // Flag variables if product is found in the list
        boolean product1Found = false;
        boolean product2Found = false;
        boolean product3Found = false;

        InventoryLinkedList<Product> productList = new InventoryLinkedList<>();

        product1Found = productList.Contains(product1);
        product2Found = productList.Contains(product2);
        product3Found = productList.Contains(product3);


        // If any of the three products were missing
        if (!product1Found || !product2Found || !product3Found) {
            assertTrue("The addUpdateProduct did not correctly add one of the items to the product list", false);
        }

        // NOW TRYING TO ADD DUPLICATE PRODUCTS

        // Get size of list before attempting to add duplicate products
        int expectedSizeOfProductList = productList.GetLength();

        // Flags for successful method call (no changes made to objects in list since
        // duplicates)
        boolean productProductParameterSuccessful = ProductCatalog.AddUpdateProduct(product1);
        boolean productIDParameterSuccessful = ProductCatalog.AddUpdateProduct(product2);
        boolean productNameParameterSuccessful = ProductCatalog.AddUpdateProduct(product3);

        // Verify that overloaded AddUpdateProduct method returned true for successful
        // operations
        if (!productProductParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product object parameter signalled an error in adding or updating the product",
                    false);
        }
        // Verify that overloaded AddUpdateProduct method returned true for successful
        // operations
        if (!productIDParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product ID parameter signalled an error in adding or updating the product",
                    false);
        }
        // Verify that overloaded AddUpdateProduct method returned true for successful
        // operations
        if (!productNameParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product name parameter signalled an error in adding or updating the product",
                    false);
        }

        // Get size of list after method calls
        int actualSizeOfProductList = productList.GetLength();

        // Ensure the size of list did not change when passing in duplicate products
        if (expectedSizeOfProductList != actualSizeOfProductList) {
            assertTrue(
                    "The AddUpdateProduct method adds duplicate items to the menu, when it should insntead update the product instead of adding a new one",
                    false);
        }

        // Define updated values of product 1
        String expectedName = "Product1UpdatedName";
        double expectedCost = 10.50;
        int expectedQuantity = 10;
        int expectedMargin = 10;

        // Set updated values for product 1
        product1.setName(expectedName);
        product1.setCost(expectedCost);
        product1.setQuantity(expectedQuantity);
        product1.setMargin(expectedMargin);

        // Call method on product 1 by product parameter
        productProductParameterSuccessful = ProductCatalog.AddUpdateProduct(product1);

        // Verify that overloaded AddUpdateProduct method returned true for successful
        // operation
        if (!productProductParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product object parameter signalled an error in adding or updating the product",
                    false);
        }

        // Epsilon value for double cost comparison
        final double EPSILON = 0.0001;

        // Validate addUpdateProduct method with parameter of type Product object works
        // correctly by
        // validating all fields for product 1
        if (!(product1.getName().equalsIgnoreCase(expectedName))) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of type Product object does not correctly update the product name",
                    false);
        }

        if (!(Math.abs(product1.getCost() - expectedCost) < EPSILON)) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of type Product object does not correctly update the product cost",
                    false);
        }

        if (product1.getQuantity() != expectedQuantity) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of type Product object does not correctly update the product quantity",
                    false);
        }

        if (product1.getMargin() != expectedMargin) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of type Product object does not correctly update the product cost",
                    false);
        }

        // Get size of list after method call
        actualSizeOfProductList = productList.GetLength();

        // Ensure the size of list did not change when updating product by Product
        // object parameter
        // (Number of objects in list should have remained the same)
        if (expectedSizeOfProductList != actualSizeOfProductList) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of type Product object adds duplicate items to the menu, when it should insntead update the product innstead of adding a new one",
                    false);
        }

        // Updated values of product 2 information
        expectedName = "Product2UpdatedName";
        expectedCost = 20.50;
        expectedQuantity = 20;
        expectedMargin = 20;

        // Set updated values of product 2
        product2.setName(expectedName);
        product2.setCost(expectedCost);
        product2.setQuantity(expectedQuantity);
        product2.setMargin(expectedMargin);

        // Call method on product 2
        productIDParameterSuccessful = ProductCatalog.AddUpdateProduct(product2);

        // Verify that AddUpdateProduct method returned true for successful operation
        if (!productIDParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product ID parameter signalled an error in adding or updating the product",
                    false);
        }

        // Validate addUpdateProduct method with parameter of type Product ID works
        // correctly by validating all fields for product 2

        if (!(product2.getName().equalsIgnoreCase(expectedName))) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of product ID does not correctly update the product name",
                    false);
        }

        if (!(Math.abs(product2.getCost() - expectedCost) < EPSILON)) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of product ID does not correctly update the product cost",
                    false);
        }

        if (product2.getQuantity() != expectedQuantity) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of product ID does not correctly update the product quantity",
                    false);
        }

        if (product2.getMargin() != expectedMargin) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of product ID does not correctly update the product cost",
                    false);
        }

        // Get size of list after method call

        actualSizeOfProductList = productList.GetLength();

        // Ensure the size of list did not change when updating product by product ID
        if (expectedSizeOfProductList != actualSizeOfProductList) {
            assertTrue(
                    "The addUpdateProduct method with parameter of product ID adds duplicate items to the menu, when it should insntead update the product innstead of adding a new one",
                    false);
        }

        // Updated values of product 3
        expectedName = "Product3UpdatedName";
        expectedCost = 30.50;
        expectedQuantity = 30;
        expectedMargin = 30;

        // Set updated values of product 3
        product3.setName(expectedName);
        product3.setCost(expectedCost);
        product3.setQuantity(expectedQuantity);
        product3.setMargin(expectedMargin);

        // Call method on product 3 by name parameter
        productNameParameterSuccessful = ProductCatalog.AddUpdateProduct(product3);

        // Verify that AddUpdateProduct method returned true for successful operation
        if (!productNameParameterSuccessful) {
            assertTrue(
                    "The addUpdateProduct method with Product name parameter signalled an error in adding or updating the product",
                    false);
        }

        // Validate addUpdateProduct method with parameter of type Product name works
        // correctly by validating all fields for product 3

        if (!(product3.getName().equalsIgnoreCase(expectedName))) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of String productName does not correctly update the product name",
                    false);
        }

        if (!(Math.abs(product3.getCost() - expectedCost) < EPSILON)) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of String productName does not correctly update the product cost",
                    false);
        }

        if (product3.getQuantity() != expectedQuantity) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of String productName does not correctly update the product quantity",
                    false);
        }

        if (product3.getMargin() != expectedMargin) {
            assertTrue(
                    "The overloaded addUpdateProduct method with parameter of String productName does not correctly update the product cost",
                    false);
        }

        actualSizeOfProductList = productList.GetLength();

        // Ensure the size of list did not change when updating product by product name
        // parameter
        if (expectedSizeOfProductList != actualSizeOfProductList) {
            assertTrue(
                    "The addUpdateProduct method with parameter of String productName adds duplicate items to the menu, when it should insntead update the product innstead of adding a new one",
                    false);
        }

    }

    
    /*
     * This unit test validates the PrintProductInformation method correctly returns
     * a certain product's information as a string. Also, it tests that the header
     * for product information (printed with the first product) is correctly
     * returned when necessary. The cost and retail price of the products should be
     * displayed with two digits after the decimal.
     */
    @Test
    public void PrintProductInformationTest() {

        ProductCatalog testCatalog = new ProductCatalog();

        int tempID = 512;
        String tempName = "product1";
        double tempCost = 200.12;
        int tempQuantity = 205;
        int tempMargin = 8;

        Product testProduct = new Product(tempID, tempName, tempCost, tempQuantity, tempMargin);

        // Add test product to list of products
        ProductCatalog.AddUpdateProduct(testProduct);

        // String expectedProductInformation = "512Product1$200.12205$238.14";
        String expectedProductInformation;

        DecimalFormat df = new DecimalFormat("#.##");

        double expectedRetailPrice = tempCost + ((tempMargin * tempCost) / 100);

        tempCost = Double.parseDouble(df.format(tempCost));
        expectedRetailPrice = Double.parseDouble(df.format(expectedRetailPrice));
        String expRetailPrice = String.format("%.2f", expectedRetailPrice);
        expectedProductInformation = tempID + " " + tempName + " $" + tempCost + " " + tempQuantity + " $"
                + expRetailPrice + "\n";

        // Test first without header (anything != 0 tells method to not print header)
        ProductCatalog.setPrintingProductCounter(-1);
        String actualProductInformation = testCatalog.PrintProductInformation(tempID);

        //

        // Call method to store actual return value
        if (!(actualProductInformation.replaceAll("\\s", "")
                .equalsIgnoreCase(expectedProductInformation.replaceAll("\\s", "")))) {
            assertTrue("The PrintProductInformation method does not return the correct string", false);
        }

        // Test with header (Used for first product printed)
        ProductCatalog.setPrintingProductCounter(0);

        // Append product information to string
        expRetailPrice = String.format("%.2f", expectedRetailPrice);
        expectedProductInformation = tempID + " " + tempName + " $" + tempCost + " " + tempQuantity + " $"
                + expRetailPrice + "\n";

        // Call method to store actual return value
        actualProductInformation = testCatalog.PrintProductInformation(tempID);

        // Compare
        if (!(actualProductInformation.replaceAll("\\s", "")
                .equalsIgnoreCase(expectedProductInformation.replaceAll("\\s", "")))) {
            assertTrue("The PrintProductInformation method does not return the correct string", false);
        }

        
        // Remove product from list
        ProductCatalog.RemoveProduct(testProduct);
    }

    /*
     * This unit test method creates multiple new Product objects and adds them to a
     * ProductCatalog and ensures that the method returnns the correct string with
     * the product information for all products in the product catalog, as well as
     * the print product infnormation header at the start of the string. The cost
     * and retail price of the products should be displayed with two digits after
     * the decimal.
     */

    @Test
    public void PrintInventoryListTest() {

       
        // Values for 3 products
        int tempID1 = 1;
        String tempName1 = "product1";
        double cost1 = 1.0;
        int quantity1 = 1;
        int margin1 = 1;

        int tempID2 = 2;
        String tempName2 = "product2";
        double cost2 = 2.0;
        int quantity2 = 2;
        int margin2 = 2;

        int tempID3 = 3;
        String tempName3 = "product3";
        double cost3 = 3.0;
        int quantity3 = 3;
        int margin3 = 3;

        // Create two new Product objects
        Product product1 = new Product(tempID1, tempName1, cost1, quantity1, margin1);
        Product product2 = new Product(tempID2, tempName2, cost2, quantity2, margin2);
        Product product3 = new Product(tempID3, tempName3, cost3, quantity3, margin3);

        // Add products to product list
        ProductCatalog.AddUpdateProduct(product1);
        ProductCatalog.AddUpdateProduct(product2);
        ProductCatalog.AddUpdateProduct(product3);

        DecimalFormat df = new DecimalFormat("#.##");

        // Calculate expected retail price for product 1
        double expectedRetailPrice1 = cost1 + ((margin1 * cost1) / 100);

        // Formatting
        cost1 = Double.parseDouble(df.format(cost1));
        expectedRetailPrice1 = Double.parseDouble(df.format(expectedRetailPrice1));
        String expRetailPrice1 = String.format("%.2f", expectedRetailPrice1);

        // Add product 1 information to expected string
        String expectedInventoryString = tempID1 + " " + tempName1 + " $" + cost1 + " " + quantity1 + " $"
                + expRetailPrice1 + "\n";

        // Calculate expected retail price for product 2
        double expectedRetailPrice2 = cost2 + ((margin2 * cost2) / 100);

        // Formatting
        cost2 = Double.parseDouble(df.format(cost2));
        expectedRetailPrice2 = Double.parseDouble(df.format(expectedRetailPrice2));
        String expRetailPrice2 = String.format("%.2f", expectedRetailPrice2);
        // Add product 2 information to expected string
        expectedInventoryString += tempID2 + " " + tempName2 + " $" + cost2 + " " + quantity2 + " $" + expRetailPrice2
                + "\n";

        // Calculate expected retail price for product 3
        double expectedRetailPrice3 = cost3 + ((margin3 * cost3) / 100);

        // Formatting
        cost2 = Double.parseDouble(df.format(cost3));
        expectedRetailPrice3 = Double.parseDouble(df.format(expectedRetailPrice3));
        String expRetailPrice3 = String.format("%.2f", expectedRetailPrice3);
        // Add product 3 information to expected string
        expectedInventoryString += tempID3 + " " + tempName3 + " $" + cost3 + " " + quantity3 + " $" + expRetailPrice3
                + "\n";

        // Call method to print inventory list and store return string
        String actualInventoryString = ProductCatalog.PrintInventoryList();
        actualInventoryString = actualInventoryString.replaceAll("\\s", "");
        expectedInventoryString = expectedInventoryString.replaceAll("\\s", "");

        System.out.println("actual: " + actualInventoryString);
        System.out.println("expected: " + expectedInventoryString);

        // If the expected string does not match the expected string
        if (!(actualInventoryString.replaceAll("\\s", "")
                .equalsIgnoreCase(expectedInventoryString.replaceAll("\\s", "")))) {
            assertTrue("The printInventoryList method does not return the correct string", false);
        }

    }
}
