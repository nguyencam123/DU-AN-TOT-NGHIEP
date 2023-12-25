package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.repository.CustomerDetailHomestayRepository;
import com.example.demo.cors.customer.repository.CustomerHomestayRepository;
import com.example.demo.cors.customer.repository.CustomerLoginRepository;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import com.example.demo.entities.DetailHomestay;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerHomestayServiceImpl implements CustomerHomestayService {

    @Autowired
    private CustomerHomestayRepository customerHomestayRepository;

    @Autowired
    private CustomerDetailHomestayRepository customerDetailHomestayRepository;

    @Autowired
    private CustomerLoginRepository customerLoginRepository;

    @Override
    public PageableObject<Homestay> getListHomestay(CustomerHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = customerHomestayRepository.getAllHomestay(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public Homestay getHomestayById(CustomerHomestayRequest request) {
        return customerHomestayRepository.findHomestayById(request.getHomestayId());
    }

    private List<String> getHomestayByConvenient(List<String> convenientHomestayList, List<Homestay> homestayList) {
        List<DetailHomestay> lists = new ArrayList<>();
        List<String> homestayIdList = new ArrayList<>();
        List<DetailHomestay> detailHomestayList = customerDetailHomestayRepository.findAll();
        if (convenientHomestayList == null || convenientHomestayList.isEmpty()) {
            return null;
        } else {
            for (DetailHomestay detailHomestay : detailHomestayList) {
                for (String convenientHomestay : convenientHomestayList) {
                    if (detailHomestay.getConvenientHomestay().getId().equals(convenientHomestay)) {
                        lists.add(detailHomestay);
                    }
                }
            }
            for (DetailHomestay detailHomestay : lists) {
                for (Homestay homestay : homestayList) {
                    if (detailHomestay.getHomestay().getId().equals(homestay.getId())) {
                        homestayIdList.add(homestay.getId());
                    }
                }
            }
            return homestayIdList;
        }
    }

    @Override
    public PageableObject<Homestay> findAllBetweenDate(CustomerHomestayRequest request) {
        List<Homestay> res = new ArrayList<>();
        List<Homestay> lists = customerHomestayRepository.findAllBetweenDate(request);
        if (getHomestayByConvenient(request.getConvenientHomestayList(), lists) == null) {
            for (Homestay homestay : lists) {
                if ((homestay.getName().contains(request.getNameOrAddress()) || homestay.getAddress().contains(request.getNameOrAddress()))
                        && (homestay.getNumberPerson() >= request.getNumberPerson())
                        && (homestay.getRoomNumber() >= request.getRoomNumber())
                        && (homestay.getPrice().compareTo(request.getPriceMin()) > 0)
                        && (homestay.getPrice().compareTo(request.getPriceMax()) < 0)
                ) {
                    res.add(homestay);
                }
            }
        } else {
            for (Homestay homestay : lists) {
                for (String homestayIdByConvenient : getHomestayByConvenient(request.getConvenientHomestayList(), lists))
                    if ((homestay.getName().contains(request.getNameOrAddress()) || homestay.getAddress().contains(request.getNameOrAddress()))
                            && (homestay.getNumberPerson() >= request.getNumberPerson())
                            && (homestay.getRoomNumber() >= request.getRoomNumber())
                            && (homestay.getId().equals(homestayIdByConvenient))
                            && (homestay.getPrice().compareTo(request.getPriceMin()) > 0)
                            && (homestay.getPrice().compareTo(request.getPriceMax()) < 0)
                    ) {
                        res.add(homestay);
                    }
            }
        }
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res1 = new PageImpl<>(res, pageable, res.size());
        return new PageableObject<>(res1);
    }

    @Override
    public User getCustomerByToken(String token) {
        return customerLoginRepository.findUserByToken(token);
    }

    @Override
    public PageableObject<Homestay> searchHomestayByPromotion(CustomerHomestayRequest request) {
        List<Homestay> lists = customerHomestayRepository.findAllBetweenDate(request);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> res = new PageImpl<>(lists, pageable, lists.size());
        return new PageableObject<>(res);
    }

}

