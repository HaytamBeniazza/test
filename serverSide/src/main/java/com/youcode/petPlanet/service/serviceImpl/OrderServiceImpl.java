package com.youcode.petPlanet.service.serviceImpl;

import com.youcode.petPlanet.auth.user.User;
import com.youcode.petPlanet.auth.user.UserRepository;
import com.youcode.petPlanet.dto.dtoRequest.OrderLineRequest;
import com.youcode.petPlanet.dto.dtoRequest.OrderRequest;
import com.youcode.petPlanet.dto.dtoResponse.OrderResponse;
import com.youcode.petPlanet.dto.dtoResponse.ProductResponse;
import com.youcode.petPlanet.entity.Order;
import com.youcode.petPlanet.entity.OrderLine;
import com.youcode.petPlanet.entity.PetProduct;
import com.youcode.petPlanet.entity.Product;
import com.youcode.petPlanet.exception.InsufficientQuantityException;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.OrderLineRepository;
import com.youcode.petPlanet.repository.OrderRepository;
import com.youcode.petPlanet.repository.PetProductRepository;
import com.youcode.petPlanet.repository.ProductRepository;
import com.youcode.petPlanet.service.serviceInterface.OrderService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final ProductServiceImpl productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PetProductRepository petProductRepository;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository, ProductServiceImpl productService, ProductRepository productRepository, UserRepository userRepository, PetProductRepository petProductRepository) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userRepository = userRepository;
        this.petProductRepository = petProductRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void add(OrderRequest orderRequest) {
        // Retrieve or create the existing order
        Order existingOrder = (orderRequest.getOrderId() != null) ?
                orderRepository.findById(orderRequest.getOrderId())
                        .orElseThrow(() -> new NotFoundException("Order not found")) :
                new Order();

        // Retrieve or create the existing user
        User existingUser = (orderRequest.getClientId() != null) ?
                userRepository.findById(orderRequest.getClientId())
                        .orElseThrow(() -> new NotFoundException("Client not found")) :
                new User();

        // Calculate the total price of the order
        BigDecimal totalPrice = calculateTotalPrice(orderRequest);

        // Keep track of encountered PetProduct IDs to avoid duplicates
        Set<Long> petProductIds = new HashSet<>();

        // Check and update order lines in the order
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineRequest orderLineRequest : orderRequest.getOrderLines()) {
            Product product = productRepository.findById(orderLineRequest.getPetProduct().getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + orderLineRequest.getPetProduct().getProduct().getId()));

            PetProduct petProduct = petProductRepository.findById(orderLineRequest.getPetProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("PetProduct not found with ID: " + orderLineRequest.getPetProduct().getId()));


            // Check if the requested quantity is available
            if (product.getQuantity() < orderLineRequest.getQuantity()) {
                throw new InsufficientQuantityException("Insufficient quantity for product with ID: " + product.getId());
            }

            // If the PetProduct ID is already encountered, update the quantity of the existing order line
            if (petProductIds.contains(petProduct.getId())) {
                Optional<OrderLine> existingOrderLine = existingOrder.getOrderLines().stream()
                        .filter(ol -> ol.getPetProduct().getId().equals(petProduct.getId()))
                        .findFirst();

                existingOrderLine.ifPresent(orderLine -> orderLine.setQuantity(orderLine.getQuantity() + orderLineRequest.getQuantity()));
            } else {
                // If the PetProduct ID is not encountered, create a new order line and add it to the list of order lines
                OrderLine orderLine = new OrderLine();
                orderLine.setDateTime(LocalDateTime.now());
                orderLine.setPetProduct(petProduct);
                orderLine.setQuantity(orderLineRequest.getQuantity());
                orderLine.setOrder(existingOrder);
                orderLines.add(orderLine);
                petProductIds.add(petProduct.getId()); // Add the PetProduct ID to the set
            }
        }

        // Set order details
        existingOrder.setTotal(totalPrice);
        existingOrder.getOrderLines().addAll(orderLines);
        existingOrder.setClient(existingUser);
        existingOrder.setStatus(orderRequest.getStatus());

        // Save the order
        orderRepository.save(existingOrder);
    }




    private BigDecimal calculateTotalPrice(OrderRequest orderRequest) {
        return orderRequest.getOrderLines().stream()
                .map(orderLineRequest -> {
                    ProductResponse product = productService.findById(orderLineRequest.getPetProduct().getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + orderLineRequest.getPetProduct().getId()));
                    return BigDecimal.valueOf(Long.parseLong(product.getPrice())).multiply(BigDecimal.valueOf(orderLineRequest.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public Optional<OrderResponse> findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return Optional.of(modelMapper.map(order.get(), OrderResponse.class));
        } else {
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
    }

    @Override
    public List<OrderResponse> getAll(int page, int size) {
        Page<Order> ordersPage = orderRepository.findAll(PageRequest.of(page, size));
        List<Order> orders = ordersPage.getContent();
        if(orders.isEmpty()){
            orders = orderRepository.findAll();
        }
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderResponse> delete(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return Optional.of(modelMapper.map(order.get(), OrderResponse.class));
        } else {
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
    }

    @Override
    public Optional<OrderResponse> changeStatus(Long id, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(newStatus);
            orderRepository.save(order);
            return Optional.of(modelMapper.map(order, OrderResponse.class));
        } else {
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
    }

}
