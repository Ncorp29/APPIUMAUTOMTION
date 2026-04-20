# AI Fix Notes

Session: seq-1776664286162-8t0farnlw
Repository: Ncorp29/APPIUMAUTOMTION

- [1] (high) BaseTest.java: Test setup logic is likely doing too much in a single @BeforeMethod method and depends on a global DriverFactory. This reduces test isolation, makes parallel execution risky, and increases the chance of driver state leakage between tests. Consider creating and tearing down the driver per test in a dedicated lifecycle manager and passing the driver explicitly or via a well-scoped fixture.
- [2] (high) BaseTest.java: The setup method declares 'throws Exception' broadly, which hides specific failure modes and makes test failures harder to diagnose. Narrow the exception type and fail with descriptive errors when driver initialization or Appium server connection fails.
- [3] (high) HomePage.java: The page object uses driver.findElement(...) directly without waits or retry logic. This is brittle in mobile automation because elements may not be immediately available. Use explicit waits (WebDriverWait/Appium wait conditions) before interacting or asserting visibility.
- [4] (high) HomePage.java: The driver is stored in a package-private mutable field and initialized from a static DriverFactory. This couples the page object to global state and makes unit testing harder. Prefer constructor injection and make the driver field private final.
- [5] (medium) BaseTest.java: Creating a new Appium driver for every test method can be expensive and slow, especially if startup time is high. If tests do not require full isolation, consider reusing session setup at class/suite level or using a pooled/managed lifecycle to reduce execution time.

