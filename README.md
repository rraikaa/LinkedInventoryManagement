# LinkedInventoryManagement
 This program is an inventory management system with Role Based Access Control (RBAC) . There are multiple types of
 users in the program: admin, managers, and employees. A customly created doubly linked list, which stores a 
 reference to a node's next node in the list, as well as a reference to the previous node. The linked
 list stores a generic type object, allowing lists of multiple data types to be created. The program reads
 information from three different files. It reads a list of users from a file,
 and stores each user in an custom linked list. Each user has specific data fields, but
 the most important one stores a boolean value for if they are a manager or not. Certain menu items
 are restricted to only managers, so if the user is not a manager, these menu items will
 not be loaded into the menu when it is dynaically loaded and displayed to them. If the user is the admin or a manager, all menu
 items will be displayed to them. These menuItems are read in from a file and
 stored in an custom linked ilst. There is also a file that stores the product
 inventory, which is also read in and stored in a custom linked list. The program
 begins by prompting the user for a username and password. These credentials
 are passed to an AuthenticateUser method, which validates this user's information is stored in the file.
 There are many different menu items that are displayed as a menu to the user once the user is
 authenticated. Each menu item has a specific command that it executes if the
 menu item is selected by the user, which executes a specific task, and is a subclass of a parent Command class. These
 tasks include adding a new user, removing a user, changing a user's password,
 adding a new product to the inventory, updating a product in the inventory,
 deleting a product from the inventory, specific the information about a
 certain product, or displaying all products in the inventory file. The custom linked list data structure has 
 numerous methods to operate on the elements in the linked list. 
