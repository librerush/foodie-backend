package com.example.foodie.service;

import com.example.foodie.dto.BrandDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.repository.BrandRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements ServiceTemplate<Brand, Long, BrandDto> {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand create(BrandDto brandDto) {
        return brandRepository.save(new Brand(brandDto.getName()));
    }

    @Override
    public ResultDto delete(Brand brand) {
        try {
            brandRepository.delete(brand);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No brand: " + brand);
        }
        return new ResultDto(true, "Deleted: " + brand);
    }

    @Override
    public ResultDto deleteById(Long aLong) {
        try {
            brandRepository.deleteById(aLong);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No brand: " + aLong);
        }
        return new ResultDto(true, "Deleted: " + aLong);
    }

    @Override
    public Brand update(Brand brand) {
        return null;
    }

    @Override
    public Optional<Brand> findById(Long aLong) {
        return brandRepository.findById(aLong);
    }

    @Override
    public Brand getById(Long aLong) throws ResponseStatusException {
        Optional<Brand> brandOptional = brandRepository.findById(aLong);
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No brand with id: " + aLong);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public List<Brand> findByName(String name) {
        return brandRepository.findBrandByName(name);
    }
}
