package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.model.User;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /*
        Creating default products
         */
        Product diorProduct = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.00));
        Product limeCrimeProduct = new Product("Lime Crime Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.00));
        Product guerlainProduct = new Product("Guerlain Cils D'Enfer So Volume Mascara",
                BigDecimal.valueOf(1200.00));
        productService.create(diorProduct);
        productService.create(limeCrimeProduct);
        productService.create(guerlainProduct);
        /*
        Creating two users
         */
        User firstUser =
                new User("Ashley", "halsey", "YungbludRocks789");
        User secondUser =
                new User("Max", "maxim_dao", "BeautyBlogger456");
        userService.create(firstUser);
        userService.create(secondUser);
        /*
        Creating shopping carts for the new users
         */
        ShoppingCart firstCart = new ShoppingCart(firstUser.getId());
        ShoppingCart secondCart = new ShoppingCart(secondUser.getId());
        shoppingCartService.create(firstCart);
        shoppingCartService.create(secondCart);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String timeValue = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString().concat("   ");
        req.setAttribute("time", timeValue);
        req.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req, resp);
    }
}