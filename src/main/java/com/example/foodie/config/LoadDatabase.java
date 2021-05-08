package com.example.foodie.config;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            Category category1 = new Category("Чипсы");
            Category category2 = new Category("Шоколад");

            Brand brand1 = new Brand("Lays", "");
            Brand brand2 = new Brand("Рахат", "");

            Product product1 = new Product("Чипсы Lays шашлык 90 г", 342, category1, brand1,
                    "Хрустящие картофельные чипсы со вкусом шашлыка. Чипсы — это не только быстрый и питательный перекус, это еще компонент многих закусок и салатов, они придают запеканкам хрустящую корочку, а салатам оригинальный вкус.",
                    "1.jpg");
            Product product2 = new Product("Конфеты Рахат Маска кг", 2102, category2, brand2,
                    "Ванильный аромат и нежнейшее молочно-ореховое пралине в сочетании с шоколадной глазурью делают эти конфеты достойным угощением вашего стола.",
                    "2.jpg");

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            brandRepository.save(brand1);
            brandRepository.save(brand2);
            productRepository.save(product1);
            productRepository.save(product2);
        };
    }
}
