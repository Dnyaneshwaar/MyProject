import json
#pytest --browser_name chrome -m smoke -n 3 --tracing on --html=report.html

import pytest
from playwright.sync_api import Playwright, expect

from pageObjects.login import LoginPage
from pageObjects.dashboard import DashboardPage
from utils.apiBaseFramework import APIUtils

# json file->utils ->access into test

with open('data/credentials.json') as f:
    test_data = json.load(f)
    print(test_data)
    user_credential_list = test_data['user-credentials']


@pytest.mark.smoke
@pytest.mark.parametrize('user_credentials', user_credential_list)
def test_e2e_web_api(playwright: Playwright, browserInstance, user_credentials):
    userName = user_credentials["userEmail"]
    password = user_credentials["userPassword"]

    #Create order -> orderId
    api_utils = APIUtils()
    orderId = api_utils.createOrder(playwright, user_credentials)

    #Login
    loginPage = LoginPage(browserInstance)  #Object for Login page class
    loginPage.navigate()
    dashboardPage = loginPage.login(userName, password)

    #Dashboard Page
    orderHistoryPage = dashboardPage.selectOrdersNavLink()
    orderDetailsPage = orderHistoryPage.selectOrder(orderId)

    # Order History page-> order is present
    orderDetailsPage.verifyOrderMessage()

    #tagline = page.locator(".tagline").text_content()
    #print(tagline)
