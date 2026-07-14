from asyncio import wait

from playwright.sync_api import Page, Playwright, expect


def test_cartValidation(playwright):
    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto("https://rahulshettyacademy.com/client/#/auth/login")
    page.locator("#userEmail").fill("dsc03692@gmail.com")
    page.locator("#userPassword").fill("03692Dsc@")
    forgot_link = page.locator(".forgot-password-link").is_disabled()
    print(forgot_link)
    page.get_by_role("Button", name="Login").click()

    #row = page.locator(".row")

    page.get_by_role("button", name="HOME").click()
    row = page.locator(".card-body").filter(has_text="iphone 13 pro")
    row.get_by_role("button", name=" Add To Cart").click()

    li = page.locator("li").filter(has_text="  Cart ")
    li.get_by_role("button", name="  Cart ").click()
   # page.locator(".cartSection").filter(has_text="iphone 13 pro").is_disabled()
    cartSection = page.locator(".cartSection").filter(has_text="iphone 13 pro")
    actual = expect(cartSection.get_by_text("iphone 13 pro")).to_be_visible()
    print(actual)







