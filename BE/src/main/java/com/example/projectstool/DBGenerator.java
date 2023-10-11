package com.example.projectstool;

import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CancellationPolicyRoomRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.ConvenientHotelTypeRepository;
import com.example.demo.repositories.ConvenientRoomTypeRepository;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.CustomerRankRepository;
import com.example.demo.repositories.DetailBookingRepository;
import com.example.demo.repositories.DetailRoomRepository;
import com.example.demo.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.example.demo.repositories"
)
public class DBGenerator implements CommandLineRunner {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CancellationPolicyRoomRepository cancellationPolicyRoomRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ConvenientHotelTypeRepository convenientHotelTypeRepository;
    @Autowired
    private ConvenientRoomTypeRepository convenientRoomTypeRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CustomerRankRepository customerRankRepository;
    @Autowired
    private DetailBookingRepository detailBookingRepository;
    @Autowired
    private DetailRoomRepository detailRoomRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public void run(String... args) throws Exception {

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}