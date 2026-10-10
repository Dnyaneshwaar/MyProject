package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
        Page page;
        String base_url;
        private static final String email_placeholder = "you@email.com";
        private static final String password_label = "Password";

        public LoginPage(Page page,String base_url)
        {
            this.page=page;
            this.base_url=base_url;
        }

        public DashBoardPage loginToApplication() {
            page.navigate(base_url);
            System.out.println("Page title is: " + page.title());
            assertThat(page).hasTitle("EventHub — Discover & Book Events");
            page.getByPlaceholder(email_placeholder).fill("dschandwade@yahoo.com");
            page.getByLabel(password_label).fill("9130135552@DSC");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
            DashBoardPage dashboardpage = new DashBoardPage(page);
            return dashboardpage;
        }
}
