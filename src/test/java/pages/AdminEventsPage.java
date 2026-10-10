package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminEventsPage {
    Page page;
    private static final String Event_Title = "#event-title-input";
    private static final String Event_Desc ="#admin-event-form textarea";
    private static final String Event_Category = "category";
    private static final String Event_City = "city";
    private static final String Event_Venue = "venue";
    private static final String Event_Date = "Event Date & Time";
    private static final String Event_Price ="Price ($)";
    private static final String Event_Total_Seats ="Total Seats";
    private static final String Add_Event_Btn = "+ Add Event";
    private static final String Success_Toast = "Event created!";


    public AdminEventsPage(Page page)
    {
    this.page=page;
    }

    public void goTo()
    {
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
    }

    public void creatEvent(String title,String description,String category,String city,
                           String venue, String date,String price,String TotalSeats)
    {
        page.locator(Event_Title).fill(title);
        page.locator(Event_Desc).fill(description);
        page.getByLabel(Event_Category).selectOption(category);
        page.getByLabel(Event_City).fill(city);
        page.getByLabel(Event_Venue).fill(venue);
        page.getByLabel(Event_Date).fill(date);
        page.getByLabel(Event_Price).fill(price);
        page.getByLabel(Event_Total_Seats).fill(TotalSeats);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(Add_Event_Btn)).click();
        assertThat(page.getByText(Success_Toast)).isVisible();
    }
}
