from .orderDetails import OrderDetails


class OrderHistoryPage:

    def __init__(self, page):
        self.page = page

    def selectOrder(self, orderId):
        row = self.page.locator("tr").filter(has_text=orderId)
        row.get_by_role("button", name="View").click()
        orderDetailsPage = OrderDetails(self.page)
        return orderDetailsPage