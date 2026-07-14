import time
from shlex import split

from pytest_playwright.pytest_playwright import browser
from playwright.sync_api import Page, expect


def test_UIValidationDynamicScript(page:Page):

#iphone X, Nokia edge -> verify 2 items are showing in cart
    page.goto('https://rahulshettyacademy.com/loginpagePractise/')
    page.get_by_label("Username:").fill("rahulshettyacademy")
    page.get_by_label("Password:").fill("Learning@830$3mK2")
    page.get_by_role("combobox").select_option("teach")
    page.locator('#terms').check()
    page.get_by_role("link", name="terms and conditions").click()
    page.get_by_role("button", name="Sign In").click()
    iphoneLocator = page.locator("app-card").filter(has_text='iphone X')
    iphoneLocator.get_by_role("button").click()
    nokiaedgeLocator = page.locator("app-card").filter(has_text='Nokia Edge')
    nokiaedgeLocator.get_by_role("button").click()
    page.get_by_text("checkout").click()
    expect(page.locator(".media-body")).to_have_count(2)
    expect(page.get_by_role("link", name="iphone X")).to_be_visible()




def test_childWindowHandel(page:Page):
    page.goto("https://rahulshettyacademy.com/loginpagePractise/")

    with page.expect_popup() as newPage_info:
        page.get_by_role("link", name="Free Access to InterviewQues/ResumeAssistance/Material").click()
        childPage = newPage_info.value
        text = childPage.locator(".red").text_content()
        print(text)#Please email us at mentor@rahulshettyacademy.com with below template to receive response
        words = text.split("at")
        print(words)
        email = words[1].split(" ")[1]

        assert email == 'mentor@rahulshettyacademy.com'
