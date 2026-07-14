import time

from pytest_playwright.pytest_playwright import browser
from playwright.sync_api import Page, expect


def test_playwrightBasics(playwright):

    browser = playwright.chromium.launch(headless=False)
    context = browser.new_context()
    page = context.new_page()
    page.goto('http://rahulshettyacademy.com')

# if multiple browser requirement then below shortcut will not work
def test_playwrightShortcut(page:Page):
    page.goto('http://rahulshettyacademy.com')
    print(page.title())
    print("shortcut code executed")

# Locator
#-- CSS selector syntax in case of referring id - #id
#-- CSS selector syntax in case of class - .classname
def test_coreLocator(page:Page):
    page.goto('https://rahulshettyacademy.com/loginpagePractise/')
    page.get_by_label("Username:").fill("rahulshettyacademy" )
    page.get_by_label("Password:").fill("Learning@830$3mK1" )
    page.get_by_role("combobox").select_option("teach")
    page.locator('#terms').check()
    page.get_by_role("link", name="terms and conditions").click()
    page.get_by_role("button", name="Sign In").click()
    expect(page.get_by_text("Incorrect username/password")).to_be_visible()


def test_fireFoxBrowser(playwright):
    firefoxBrowser = playwright.firefox.launch(headless=False)
    context = firefoxBrowser.new_context()
    page = context.new_page()
    page.goto('https://rahulshettyacademy.com/loginpagePractise/')
    page.get_by_label("Username:").fill("rahulshettyacademy")
    page.get_by_label("Password:").fill("Learning@830$3mK1")
    page.get_by_role("combobox").select_option("teach")
    page.locator('#terms').check()
    page.get_by_role("link", name="terms and conditions").click()
    page.get_by_role("button", name="Sign In").click()
    expect(page.get_by_text("Incorrect username/password")).to_be_visible()