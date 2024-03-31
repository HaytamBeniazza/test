package com.youcode.petPlanet.service.serviceImpl;


import com.youcode.petPlanet.dto.dtoRequest.OrderLineRequest;
import com.youcode.petPlanet.dto.dtoResponse.OrderLineResponse;
import com.youcode.petPlanet.dto.dtoResponse.OrderResponse;
import com.youcode.petPlanet.entity.Order;
import com.youcode.petPlanet.entity.OrderLine;
import com.youcode.petPlanet.exception.ResourceNotFoundException;
import com.youcode.petPlanet.repository.OrderLineRepository;
import com.youcode.petPlanet.service.serviceInterface.OrderLineService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private final ModelMapper modelMapper;
    private final OrderLineRepository orderLineRepository;

    public OrderLineServiceImpl(ModelMapper modelMapper, OrderLineRepository orderLineRepository) {
        this.modelMapper = modelMapper;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public Optional<OrderLineResponse> Add(OrderLineRequest orderLine) {
        OrderLine orderLineToSave = modelMapper.map(orderLine , OrderLine.class);
        orderLineRepository.save(orderLineToSave);
        return Optional.of(modelMapper.map(orderLine, OrderLineResponse.class));
    }

    @Override
    public Optional<OrderLineResponse> findById(Long id) {
        Optional<OrderLine> orderLine = orderLineRepository.findById(id);
        if(orderLine.isPresent()){
            return Optional.of(modelMapper.map(orderLine,OrderLineResponse.class));
        }else{
            throw new ResourceNotFoundException("Order Line not found with ID : " + id);
        }
    }

    @Override
    public List<OrderLineResponse> getAll(int page, int size) {
        Page<OrderLine> ordersLinePage = orderLineRepository.findAll(PageRequest.of(page, size));
        List<OrderLine> orderLines = ordersLinePage.getContent();
        if(orderLines.isEmpty()){
            orderLines = orderLineRepository.findAll();
        }
        return orderLines.stream()
                .map(order -> modelMapper.map(order, OrderLineResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderLineResponse> delete(Long id) {
        Optional<OrderLine> orderLine = orderLineRepository.findById(id);
        if(orderLine.isPresent()){
            orderLineRepository.delete(orderLine.get());
            return Optional.of(modelMapper.map(orderLine, OrderLineResponse.class));
        }else{
            throw new ResourceNotFoundException("Order Line not found with name : " + id);
        }
    }
}
