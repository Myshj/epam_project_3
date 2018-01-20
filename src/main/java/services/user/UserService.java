package services.user;

import services.ServletService;
import services.ServletServiceContext;
import services.user.commands.CancelOrder;
import services.user.commands.PurchaseTicket;
import services.user.commands.ShowOrdersOfUser;

public class UserService extends ServletService {
    public UserService(ServletServiceContext context) {
        super(context);
        registerCommand(url("showUserOrdersTemplate"), new ShowOrdersOfUser(context));
        registerCommand(url("cancelUserOrderTemplate"), new CancelOrder(context));
        registerCommand(url("purchaseTicketTemplate"), new PurchaseTicket(context));
    }
}
