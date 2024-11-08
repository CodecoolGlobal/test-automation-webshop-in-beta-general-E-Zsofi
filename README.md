# Webshop-in-Beta - Automated Testing Project

## Overview

This project is an automated testing setup for the **SauceDemo** webshop (https://www.saucedemo.com/). The purpose of this repository is to verify the functionality of the webshop using Selenium WebDriver and JUnit 5. The tests cover basic user interactions such as browsing products, adding them to the shopping cart, and completing a checkout process.

## Technologies Used

- **Java**: Programming language used for writing test scripts.
- **Selenium**: Browser automation tool for interacting with the webpage.
- **JUnit 5**: Testing framework for running the tests and generating reports.
- **CSV**: Used to store and manage environment variables like user credentials and other test data.
- **Maven**: Build automation tool to manage dependencies and build the project.

## Features

- Automated tests for the following functionalities:
    - Product browsing and selection.
    - Adding products to the shopping cart.
    - Proceeding to checkout and completing a purchase.
- Compatible with Java 17 for the latest features and performance improvements.
- Dependency management handled via Maven.

## Installation

To get started with the project, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-repository/webshop-in-beta.git
   ```
   ```bash
    cd webshop-in-beta
   ```


2. **Install dependencies:**

   Ensure you have Java 17 installed. Then, use Maven to download the necessary dependencies:

```bash
mvn install
```

3. **Run the tests:**
   To execute the tests, simply run the following Maven command:

```bash
mvn test
```
The tests will execute, and results will be displayed in the terminal.

## Test Scenarios
The automated tests in this project simulate user interactions with the SauceDemo webshop, covering:

1. **Login:**
Verify that the user can log in with valid credentials from the CSV file.

2. **Product Browsing:**
Ensure the user can browse and view products.
3. **Add to Cart:**
Test that items can be added to the shopping cart.
4. **Checkout:**
Validate the process of proceeding to checkout and completing the purchase.