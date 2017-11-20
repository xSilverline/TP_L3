package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class DefaultBooktoreFacade implements BookstoreFacade
{
    private BookDBService bookDBService;
    private CustomerDBService customerDBService;
    private CustomerNotificationService customerNotificationService;
    private OrderingService orderingService;
    private WharehouseService warehouseService;

    @Override
    public void placeOrder(String customerId, String isbn)
    {
        Book book =bookDBService.findBookByISBN(isbn);
        Customer customer = customerDBService.findCustomerById(customerId);
        Order order = orderingService.createOrder(customer,book);
        DispatchReceipt dispatchReceipt= warehouseService.dispatch(order);
        customerNotificationService.notifyClient(dispatchReceipt);
    }



    public void setBookDBService(BookDBService bookDBService)
    {
        this.bookDBService = bookDBService;
    }

    public void setCustomerDBService(CustomerDBService customerDBService)
    {
        this.customerDBService = customerDBService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService)
    {
        this.customerNotificationService = customerNotificationService;
    }

    public void setOrderingService(OrderingService orderingService)
    {
        this.orderingService = orderingService;
    }

    public void setWarehouseService(WharehouseService warehouseService)
    {
        this.warehouseService = warehouseService;
    }
}
