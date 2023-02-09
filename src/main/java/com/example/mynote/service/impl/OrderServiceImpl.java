package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import com.example.mynote.model.Order;
import com.example.mynote.model.OrderDetail;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.OrderDetailInfo;
import com.example.mynote.payload.OrderInfor;
import com.example.mynote.payload.Period;
import com.example.mynote.repositories.*;
import com.example.mynote.service.OrderService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    private final Long DEFAULT_BEGIN_DATE = 0L;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository,
                            ProductRepository productRepository,
                            CustomerRepository customerRepository,
                            EmployeeRepository employeeRepository,
                            ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public Order addOrder(OrderInfor newOrder) {
        if(!checkOrder(newOrder)){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Order not valid");
            throw new BadRequestException(apiResponse);
        }
        Order order = mapper.map(newOrder,Order.class);
        order.setOrderDetail(null);
        Order savedOrder = orderRepository.save(order);
        List<OrderDetail> orderDetailList = addOrderDetail(savedOrder,newOrder.getOrderDetails());
        savedOrder.setOrderDetail(orderDetailList);
        return orderRepository.save(savedOrder);
    }

    @Override
    public Order updateOrder(OrderInfor modifiedRequest, Long id) {
        if(!checkOrder(modifiedRequest)){
            throw new BadRequestException(new ApiResponse(Boolean.FALSE, "Order is not valid"));
        }
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Order not found!")));
        order.setCustomer(customerRepository.findCustomerByCustomerId(modifiedRequest.getCustomer().getCustomerId())
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Customer not found!"))));
        order.setEmployee(employeeRepository.findById(modifiedRequest.getEmployee().getEmployeeId())
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Employee not found!"))));
        order.setOrderDate(modifiedRequest.getOrderDate());
        order.setRequiredDate(modifiedRequest.getRequiredDate());
        order.setShippedDate(modifiedRequest.getShippedDate());
        order.setFreight(modifiedRequest.getFreight());
        order.setShipName(modifiedRequest.getShipName());
        order.setShipAddress(modifiedRequest.getShipAddress());
        order.setShipCity(modifiedRequest.getShipCity());
        order.setShipRegion(modifiedRequest.getShipRegion());
        return orderRepository.save(order);
    }

    @Override
    public ApiResponse deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Delete order failed! Order not found!")));
        orderRepository.delete(order);
        return new ApiResponse(Boolean.TRUE, "Delete order successful");
    }

    @Override
    public OrderInfor getOrderInfor(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE,"Order not found!")));
        return mapper.map(order, OrderInfor.class);
    }

    @Override
    public List<OrderInfor> getCustomerOrderHistory(String customerId) {
        Customer customer = customerRepository.findCustomerByCustomerId(customerId)
                .orElseThrow(()-> new BadRequestException(new ApiResponse(Boolean.FALSE,"Customer not found!")));
        List<Order> orders = orderRepository.findAllByCustomer(customer);
        if(orders.isEmpty()) return Collections.emptyList();
        return orders.stream().map(order -> mapper.map(order, OrderInfor.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfor> getEmployeeOrderHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new BadRequestException(new ApiResponse(Boolean.FALSE,"Employee not found!")));
        List<Order> orders = orderRepository.findAllByEmployee(employee);
        if (orders.isEmpty()) return Collections.emptyList();
        return orders.stream().map(order -> mapper.map(order, OrderInfor.class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderInfor> getOrderByDate(Period timePeriod, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        checkPeriod(timePeriod);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,"orderDate");
        Page<Order> orders = orderRepository.findAll(pageable);
        if(orders.isEmpty()){
            return Collections.emptyList();
        }
        return orders.stream().filter(order -> timePeriod.checkIsInPeriod(order.getOrderDate()))
                .map(order -> mapper.map(order, OrderInfor.class))
                .collect(Collectors.toList());
    }

    private List<OrderDetail> addOrderDetail(Order order, List<OrderDetailInfo> orderDetails){
        List<OrderDetail> orderDetailList = orderDetails.stream().map(orderDetail -> mapper.map(orderDetail, OrderDetail.class)).collect(Collectors.toList());
        orderDetailList.forEach(orderDetail -> orderDetail.setOrder(order));
        return orderDetailRepository.saveAll(orderDetailList);
    }

    private List<OrderDetail> compareOldOrder(Order order, List<OrderDetailInfo> infor){
        List<OrderDetail> newOrderDetails = infor.stream().map(orderDetailInfo -> mapper.map(orderDetailInfo, OrderDetail.class)).collect(Collectors.toList());
        List<OrderDetail> oldOrderDetails = orderDetailRepository.findOrderDetailsByOrder_OrderId(order.getOrderId());
        newOrderDetails.forEach(od -> od.setOrder(order));
        if(isListEqual(newOrderDetails, oldOrderDetails)){
            return newOrderDetails;
        }
        else{
            return updateOrderDetail(newOrderDetails, oldOrderDetails);
        }
    }

    private List<OrderDetail> updateOrderDetail(List<OrderDetail> newOrderDetails, List<OrderDetail> oldOrderDetails) {
        ArrayList<OrderDetail> finalList = new ArrayList<>(newOrderDetails);
        ArrayList<OrderDetail> deleteList = new ArrayList<>(oldOrderDetails);
        for (OrderDetail od: finalList) {
            if(!deleteList.contains(od)){
                deleteList.remove(od);
            }
        }
        for (OrderDetail od: deleteList) {
            orderDetailRepository.deleteAll(deleteList);
        }
        return orderDetailRepository.saveAll(finalList);
    }

    private Boolean checkOrder(OrderInfor orderInfor){
        List<OrderDetailInfo> orderDetailInfos = orderInfor.getOrderDetails();
        if(orderDetailInfos.isEmpty()){
            return Boolean.FALSE;
        }
        return orderDetailInfos.stream().allMatch(orderDetailInfo -> checkProductInventory(orderDetailInfo.getProductId(),orderDetailInfo.getQuantity()));
    }

    private Boolean checkProductInventory(Long productId, int requireAmount){
        if(!productRepository.existsById(productId)){
            return Boolean.FALSE;
        }
        int inventoryAmount = productRepository.getProductAmount(productId);
        if(inventoryAmount < 0){
            return Boolean.FALSE;
        }
        if (inventoryAmount < requireAmount){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void checkPeriod(Period period){
        if(period.getBegin() == null){
            period.setBegin(new Date(DEFAULT_BEGIN_DATE));
        }
        if(period.getEnd() == null){
            Long currentTime = System.currentTimeMillis();
            period.setEnd(new Date(currentTime));
        }
    }

    private Boolean isListEqual(List<OrderDetail> l1, List<OrderDetail> l2){
        ArrayList<OrderDetail> temporaryList = new ArrayList<>(l1);
        for (OrderDetail od: l2) {
            if(!temporaryList.remove(od)){
                return false;
            }
        }
        return temporaryList.isEmpty();
    }

    private Boolean compareOrder(OrderDetail order1, OrderDetail order2){
        return !(order1.getProduct() == order2.getProduct() || order1.getUnitPrice() == order2.getUnitPrice()
                || order1.getQuantity() == order2.getQuantity()|| order1.getDiscount()== order2.getDiscount());
    }
}
