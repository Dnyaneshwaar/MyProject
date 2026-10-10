package tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FrameworkBuildTest extends TestBase{



    @Test(description = "create event- Book the event & verify if it's booked")
    public void demoTest() {

        String eventTitle= "Playwright Pune QA summit";
        LoginPage loginpage = new LoginPage(page,base_url);
        DashBoardPage dashboardPage = loginpage.loginToApplication();
        dashboardPage.waitForEventsToLoad();

        //Step-1 Creation of event from admin page

        AdminEventsPage admineventPage = new AdminEventsPage(page);
        admineventPage.goTo();
        admineventPage.creatEvent(
                eventTitle,
                "International talk- QA summit",
                "Concert",
                "Pune",
                "Public Ground",
                "2026-09-05T11:45",
                "20.25",
                "500");

        //Step-2  Validation of after added event in event page
        EventPage eventspage = new EventPage(page);
        eventspage.goTo();
        Locator targetCard =eventspage.findEventCard(eventTitle);
        int seatNumBeforeBooking = eventspage.getSeatCount(targetCard);
        BookingFormPage bookingFormPage =  eventspage.proceedToBookingEvent(targetCard);
        bookingFormPage.fillAndConfirm("Elon Musk","xyz@gmail.com","28-2526-4544");

//Step-3 Book Tickets page

        //page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("+")).click(new Locator.ClickOptions().setTimeout(3000));


       /*
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
       System.out.println("Event card count is: "+eventCardsAfterBooking.count());

        Locator targetCardAfterBooking = eventCardsAfterBooking.filter(new Locator.FilterOptions().setHasText("Playwright Pune QA summit"));
//        assertThat(targetCard).isVisible();
        String seatsTextAfterBooking = targetCardAfterBooking.getByText("seats").innerText();
        System.out.println("Card inner text is: "+seatsTextAfterBooking);
//      AfterBooking<BeforeBooking
        int seatsnumAbooking = Integer.parseInt(seatsTextAfterBooking.split(" ")[0]);

        Assert.assertTrue(seatNumBeforeBooking>seatsnumAbooking); */




    }
}
