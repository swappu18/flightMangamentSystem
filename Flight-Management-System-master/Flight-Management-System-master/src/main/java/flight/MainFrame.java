package flight;

import javax.swing.*;
import java.util.Date;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private AdminLogin adminLogin;
    private ClientMenu clientMenu;
    private ErrorPage errorPage;
    private AdminMenu adminMenu;
    private PurchasePage purchasePage;
    private FindFlightPage findFlightPage;
    private OnlineBoard onlineBoard;
    private FillInfoPage fillInfoPage;
    private PLoginPage pLoginPage;
    private BookingManagement bookingManagement;
    private SendMessagePage sendMessagePage;
    private ChangeStatusPage changeStatusPage;
    private ChangeAirlinePage changeAirlinePage;

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("X-APPLICATION");
        setSize(800, 800);
        setLayout(null);

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        adminLogin = new AdminLogin(this);
        adminLogin.setVisible(false);
        add(adminLogin);

        clientMenu = new ClientMenu(this);
        clientMenu.setVisible(false);
        add(clientMenu);

        errorPage = new ErrorPage(this);
        errorPage.setVisible(false);
        add(errorPage);

        adminMenu = new AdminMenu(this);
        adminMenu.setVisible(false);
        add(adminMenu);

        purchasePage = new PurchasePage(this);
        purchasePage.setVisible(false);
        add(purchasePage);

        findFlightPage = new FindFlightPage(this);
        findFlightPage.setVisible(false);
        add(findFlightPage);

        onlineBoard = new OnlineBoard(this);
        onlineBoard.setVisible(false);
        add(onlineBoard);

        fillInfoPage = new FillInfoPage(this);
        fillInfoPage.setVisible(false);
        add(fillInfoPage);

        pLoginPage = new PLoginPage(this);
        pLoginPage.setVisible(false);
        add(pLoginPage);

        bookingManagement = new BookingManagement(this);
        bookingManagement.setVisible(false);
        add(bookingManagement);

        sendMessagePage = new SendMessagePage(this);
        sendMessagePage.setVisible(false);
        add(sendMessagePage);

        changeStatusPage = new ChangeStatusPage(this);
        changeStatusPage.setVisible(false);
        add(changeStatusPage);

        changeAirlinePage = new ChangeAirlinePage(this);
        changeAirlinePage.setVisible(false);
        add(changeAirlinePage);
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public AdminLogin getAdminLogin() {
        return adminLogin;
    }

    public ClientMenu getClientMenu() {
        return clientMenu;
    }

    public ErrorPage getErrorPage() {
        return errorPage;
    }

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public PurchasePage getPurchasePage() {
        return purchasePage;
    }

    public OnlineBoard getOnlineBoard() {
        return onlineBoard;
    }

    public FindFlightPage getFindFlightPage(Date date) {
        findFlightPage.setDate(date);
        return findFlightPage;
    }

    public FillInfoPage getFillInfoPage(int choose, Date date) {
        fillInfoPage.setChoose(choose);
        fillInfoPage.setDate(date);
        return fillInfoPage;
    }

    public PLoginPage getpLoginPage() {
        return pLoginPage;
    }

    public BookingManagement getBookingManagement(Passenger passenger) {
        bookingManagement.setPassenger(passenger);
        return bookingManagement;
    }

    public SendMessagePage getSendMessagePage() {
        return sendMessagePage;
    }

    public ChangeStatusPage getChangeStatusPage() {
        return changeStatusPage;
    }

    public ChangeAirlinePage getChangeAirlinePage() {
        return changeAirlinePage;
    }
}
