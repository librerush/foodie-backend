package com.example.foodie.service;

import com.example.foodie.dto.BrandDto;
import com.example.foodie.dto.ProductDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadProductService {
    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ProductService productService;

    public LoadProductService(CategoryService categoryService, BrandService brandService,
                              ProductService productService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productService = productService;
    }

    public List<String[]> oneByOne(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }

    public Reader getReaderForFile(final Path path) throws IOException {
        return Files.newBufferedReader(path);
    }

    public boolean saveCategories(final String path) throws Exception {
        if (categoryService.existsAny()) {
            return false;
        }

        List<String[]> list = oneByOne(getReaderForFile(Path.of(path)));
        list.forEach(strings -> categoryService.create(strings[1]));
        return true;
    }

    public void saveBrand(final String path) throws Exception {
        if (brandService.existsAny()) {
            return;
        }

        List<String[]> list = oneByOne(getReaderForFile(Path.of(path)));
        list.forEach(strings -> brandService.create(new BrandDto(strings[1])));
    }

    public void saveProduct(final String path) throws Exception {
        if (productService.existsAny()) {
            return;
        }

        List<String[]> list = oneByOne(getReaderForFile(Path.of(path)));
        list.forEach(strings -> {
            ProductDto productDto = new ProductDto();
            productDto.setName(strings[1]);
            productDto.setImage(strings[2]);
            productDto.setPrice(Double.parseDouble(strings[3]));
            List<Brand> brand = brandService.findByName(strings[4]);
            if (brand.isEmpty()) { return; }
            productDto.setBrand(brand.get(0));
            productDto.setDescription(strings[5]);
            List<Category> category = categoryService.findByName(strings[6]);
            if (category.isEmpty()) { return; }
            productDto.setCategory(category.get(0));

            productService.create(productDto);
        });
    }
}
