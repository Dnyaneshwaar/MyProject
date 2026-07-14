from playwright.sync_api import Page

fakePayloadResponse = {"data":[], "message": "No Orders"}

def intercept_response(route):
    route.fulfill(
        json=fakePayloadResponse
    )

def test_Network_1(page : Page):
    # Login
    page.goto("https://rahulshettyacademy.com/client")
    page.route("https://rahulshettyacademy.com/api/ecom/order/get-orders-for-customer/*",intercept_response)
    page.get_by_placeholder("email@example.com").fill("dsc03692@gmail.com")
    page.get_by_placeholder("enter your passsword").fill("03692Dsc@")
    page.get_by_role("button", name="Login").click()
    page.get_by_role("button", name="ORDERS").click()
    order_text = page.locator(".mt-4").text_content()
    print(order_text)


