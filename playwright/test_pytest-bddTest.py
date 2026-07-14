import pytest
from pytest_bdd import given, when, then, parsers, scenarios

from pageObjects.login import LoginPage
from utils.apiBaseFramework import APIUtils

scenarios('features/orderTransaction.feature')

@pytest.fixture
def shared_data():
    return {}


@given(parsers.parse('place the item order with {username} and {password}'))
def place_item_order(playwright,username,password,shared_data):
    user_credentials= {}
    user_credentials["userEmail"]= username
    user_credentials["userPassword"]= password

    api_utils = APIUtils()
    orderId = api_utils.createOrder(playwright, user_credentials)
    shared_data['order_id'] = orderId

@given('user is on landing page')
def user_on_landing_page(browserInstance, shared_data):
    loginPage = LoginPage(browserInstance)
    loginPage.navigate()
    shared_data['Login_page'] = loginPage


@when(parsers.parse('I login to portal with {username} and {password}'))
def login_to_portal(username,password,shared_data):
    loginPage = shared_data['Login_page']
    dashboardPage = loginPage.login(username, password)
    shared_data['dashboard_page'] = dashboardPage

@when('Navigate to order page')
def navigate_to_order_page(shared_data):
    dashboardPage = shared_data['dashboard_page']
    orderHistoryPage = dashboardPage.selectOrdersNavLink()
    shared_data['orderHistory_page'] = orderHistoryPage

@when('select the order id')
def select_order_id(shared_data):
    orderHistoryPage = shared_data['orderHistory_page']
    orderId = shared_data['order_id']
    orderDetailsPage = orderHistoryPage.selectOrder(orderId)
    shared_data['orderDetails_Page'] = orderDetailsPage


@then('verify the order success message')
def verify_order_success(shared_data):
    orderDetailsPage = shared_data['orderDetails_Page']
    orderDetailsPage.verifyOrderMessage()
