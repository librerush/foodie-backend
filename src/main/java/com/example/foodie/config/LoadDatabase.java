package com.example.foodie.config;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;
import com.example.foodie.entity.Product;
import com.example.foodie.entity.User;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import com.example.foodie.repository.UserRepository;
import com.example.foodie.service.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class LoadDatabase {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;

    @Value("${INIT_DB}")
    private String initDb;

    public LoadDatabase(BrandRepository brandRepository, CategoryRepository categoryRepository,
                        ProductRepository productRepository, UserRepository userRepository, PasswordEncoderService passwordEncoderService) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    /**
     * Testing data.
     */
    @Bean
    @Transactional
    @Modifying
    CommandLineRunner initDatabase() {
        return args -> {
            if ("false".equals(initDb)) {
                return;
            }

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

            User user1 = new User("Alex", "alex@mail.com", "alex", "Almaty");

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            brandRepository.save(brand1);
            brandRepository.save(brand2);
            productRepository.save(product1);
            productRepository.save(product2);

            user1.setPassword(passwordEncoderService.encode(user1.getPassword()));
            userRepository.save(user1);
        };
    }
}
