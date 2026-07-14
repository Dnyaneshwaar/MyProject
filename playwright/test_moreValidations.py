import time

from playwright.sync_api import Page, expect


def test_UIChecks(page: Page):
    page.goto("https://rahulshettyacademy.com/AutomationPractice/")

    #Hide/Display and PlaceHolder
    expect(page.get_by_placeholder("Hide/Show Example")).to_be_visible()
    page.get_by_role("button", name='Hide').click()
    expect(page.get_by_placeholder("Hide/Show Example")).to_be_hidden()

    #handel Alert boxes.
    page.on("dialog", lambda dialog:dialog.accept())
    page.get_by_role("button", name='Confirm').click()
    time.sleep(5)

    # MouseHover
    page.locator("#mousehover").hover()
    page.get_by_role("link", name="Top").click()

    # Handling frames

    pageFrame = page.frame_locator("#courses-iframe")
    pageFrame.get_by_role("link", name="All Access plan").click()
    expect(pageFrame.locator("body")).to_contain_text("Happy Subscibers")


    #check the price of rice is equal to 37


    page.goto("https://rahulshettyacademy.com/seleniumPractise/#/offers")

    for index in range(page.locator("th").count()):
        if page.locator("th").nth(index).filter(has_text="Price").count()>0:
            pricecolValue = index
            print(f"Column value is {pricecolValue}")
            break

    riceRow = page.locator("tr").filter(has_text="Rice")
    expect(riceRow.locator("td").nth(pricecolValue)).to_have_text("37")




