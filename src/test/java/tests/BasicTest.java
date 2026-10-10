package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasicTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeTest
    public void setUp()
    {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
    }

    @Test(description = "create event- Book the event & verify if it's booked")
    public void demoTest() {

        System.out.println("Page title is: "+page.title());
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        page.getByPlaceholder("you@email.com").fill("dschandwade@yahoo.com");
        page.getByLabel("Password").fill("9130135552@DSC");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        assertThat(page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();

        // Creation of event from admin page

        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
        page.locator("#event-title-input").fill("Pune QA summit");
        page.locator("#admin-event-form textarea").fill("AI talk- QA summit");
        page.getByLabel("category").selectOption("Concert");
        page.getByLabel("city").fill("Pune");
        page.getByLabel("venue").fill("Public Ground");
        page.getByLabel("Event Date & Time").fill("2026-09-02T11:45");
        page.getByLabel("Price ($)").fill("20.25");
        page.getByLabel("Total Seats").fill("500");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+ Add Event")).click();
        assertThat(page.getByText("Event created!")).isVisible();

        // Validation of after added event in event page

        page.locator("#nav-events").click();
        Locator eventCards = page.getByTestId("event-card");
        assertThat(eventCards.first()).isVisible();
        System.out.println("Event card count is: "+eventCards.count());

        Locator targetCard = eventCards.filter(new Locator.FilterOptions().setHasText("Pune QA summit"));
        assertThat(targetCard).isVisible();
        String seatsText = targetCard.getByText("seats").innerText();
        System.out.println("Card inner text is: "+seatsText);
        int seatsnumBbooking = Integer.parseInt(seatsText.split(" ")[0]);
        targetCard.getByTestId("book-now-btn").click();;

// Book Tickets page

        //page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+")).click(new Locator.ClickOptions().setTimeout(3000));
        page.getByLabel("Full Name").fill("Elon");
        page.getByLabel("Email").fill("Musk@gmail.com");
        page.getByLabel("Phone Number").fill("98-2525-9999");
       // page.locator("#confirm-booking").click();
        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Confirm Booking")).click();

       assertThat(page.getByText("Your tickets are reserved.")).isVisible();
       String bookingRef = page.locator(".booking-ref").innerText();
       page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("View My Bookings")).click();


// Verify in booking page

        Locator bookingCards = page.locator("#booking-card");
        Locator targetBookingCard = bookingCards.filter(new Locator.FilterOptions().setHasText(bookingRef));
        assertThat(targetBookingCard).isVisible();

//Seat count reduction change

        page.locator("#nav-events").click();
        page.waitForTimeout(1000);

        Locator eventCardsAfterBooking = page.getByTestId("event-card");
//        assertThat(eventCardsAfterBooking.first()).isVisible();
//        System.out.println("Event card count is: "+eventCardsAfterBooking.count());

        Locator targetCardAfterBooking = eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText("Pune QA summit"));
//        assertThat(targetCard).isVisible();
        String seatsTextAfterBooking = targetCardAfterBooking.getByText("seats").innerText();
        System.out.println("Card inner text is: "+seatsTextAfterBooking);
//      AfterBooking<BeforeBooking
        int seatsnumAbooking = Integer.parseInt(seatsTextAfterBooking.split(" ")[0]);

        Assert.assertTrue(seatsnumBbooking>seatsnumAbooking);




    }
}
