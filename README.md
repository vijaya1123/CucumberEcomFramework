# Selenium + Cucumber BDD Automation Framework — SauceDemo

![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.21-green)
![Cucumber](https://img.shields.io/badge/Cucumber-7.18-brightgreen)
![Build Status](https://github.com/YOUR_USERNAME/selenium-cucumber-saucedemo/actions/workflows/maven-tests.yml/badge.svg)

A BDD-driven UI test automation framework built with **Selenium WebDriver**, **Cucumber**, and **TestNG**, testing the end-to-end user journey on [saucedemo.com](https://www.saucedemo.com/) — login, product sorting, cart management, and checkout.

## Why this project

This repo demonstrates a production-style automation setup rather than a single test script:

- **Page Object Model (POM)** — locators and actions separated from test logic
- **BDD with Gherkin** — readable feature files any stakeholder can understand
- **Data-driven testing** — `Scenario Outline` + `Examples` for multiple input sets
- **Parallel execution** — TestNG data provider runs scenarios concurrently
- **Auto-managed browser drivers** — via WebDriverManager, no manual driver downloads
- **Reporting** — Cucumber HTML/JSON reports, with screenshots auto-attached on failure
- **Logging** — Log4j2 writes execution logs to console and file
- **CI/CD** — GitHub Actions runs the full suite on every push and weekly on a schedule
- **Config-driven** — browser, headless mode, and base URL are all externalized to `config.properties`

## Tech stack

| Layer | Tool |
|---|---|
| Language | Java 17 |
| Browser automation | Selenium WebDriver 4 |
| BDD | Cucumber 7 |
| Test runner | TestNG |
| Build tool | Maven |
| Driver management | WebDriverManager |
| Logging | Log4j2 |
| CI | GitHub Actions |

## Project structure

```
selenium-cucumber-saucedemo/
├── src/
│   ├── main/java/
│   │   ├── pages/          # Page Object classes (LoginPage, InventoryPage, CartPage, CheckoutPage)
│   │   └── utils/          # ConfigReader, DriverManager
│   └── test/
│       ├── java/
│       │   ├── stepdefinitions/   # Cucumber step definitions
│       │   ├── hooks/             # Before/After hooks, screenshot on failure
│       │   └── runners/           # Cucumber-TestNG runner
│       └── resources/
│           ├── features/          # .feature files (Gherkin)
│           ├── config.properties  # browser, headless, base URL
│           └── log4j2.xml
├── .github/workflows/maven-tests.yml
├── pom.xml
├── testng.xml
└── README.md
```

## Scenarios covered

- Successful login with a valid user
- Login blocked for a locked-out user
- Login validation errors (missing/invalid credentials) — data-driven
- Add multiple products to cart and verify cart badge count
- Full checkout flow through to order confirmation
- Sort products by price and verify ascending order
- Remove a product from the cart

## Running locally

**Prerequisites:** Java 17+, Maven 3.8+, Google Chrome installed.

```bash
git clone https://github.com/YOUR_USERNAME/selenium-cucumber-saucedemo.git
cd selenium-cucumber-saucedemo
mvn clean test
```

Test settings (browser, headless mode) can be changed in `src/test/resources/config.properties`, or overridden on the command line:

```bash
mvn clean test -Dheadless=false -Dbrowser=firefox
```

## Reports

After a run, open:

```
target/cucumber-reports/cucumber.html
```

Failed scenarios automatically attach a screenshot inside the Cucumber report.

## CI/CD

Every push to `main` triggers the GitHub Actions workflow, which runs the full suite headlessly on Chrome and uploads the Cucumber report as a downloadable artifact. See `.github/workflows/maven-tests.yml`.

## Possible next steps

- Add cross-browser matrix (Chrome + Firefox) in CI
- Integrate Allure reporting
- Add API-level setup (skip UI login for data setup) using SauceDemo's REST endpoints if available
- Dockerize for consistent local/CI execution

---
*Built as a personal automation testing portfolio project.*
