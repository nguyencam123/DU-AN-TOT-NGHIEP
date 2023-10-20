package com.example.projectstool;

import com.example.demo.entities.Address;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Comment;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
import com.example.demo.entities.DetailBooking;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgComment;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.entities.ImgScenicSpot;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.Promotion;
import com.example.demo.entities.Province;
import com.example.demo.entities.Region;
import com.example.demo.entities.ScenicSpot;
import com.example.demo.entities.ScenicSpotHomestay;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.contant.Role;
import com.example.demo.infrastructure.contant.TypePromotion;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CancellationPolicyRoomRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.ConvenientHomestayRepository;
import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import com.example.demo.repositories.DetailBookingRepository;
import com.example.demo.repositories.DetailHomestayRepository;
import com.example.demo.repositories.HistoryServicePackRepository;
import com.example.demo.repositories.HomestayRepository;
import com.example.demo.repositories.ImgCommentRepository;
import com.example.demo.repositories.ImgHomestayRepository;
import com.example.demo.repositories.ImgScenicSpotRepository;
import com.example.demo.repositories.OwnerHomestayRepository;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.repositories.PromotionRepository;
import com.example.demo.repositories.ProvinceRepository;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.repositories.ScenicSpotHomestayRepository;
import com.example.demo.repositories.ScenicSpotRepository;
import com.example.demo.repositories.ServicePackRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.example.demo.repositories"
)
public class DBGenerator implements CommandLineRunner {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CancellationPolicyRoomRepository cancellationPolicyRoomRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ConvenientHomestayRepository convenientHomestayRepository;
    @Autowired
    private ConvenientHomestayTypeRepository convenientHomestayTypeRepository;
    @Autowired
    private DetailBookingRepository detailBookingRepository;
    @Autowired
    private DetailHomestayRepository detailHomestayRepository;
    @Autowired
    private HistoryServicePackRepository historyServicePackRepository;
    @Autowired
    private HomestayRepository homestayRepository;
    @Autowired
    private ImgCommentRepository imgCommentRepository;
    @Autowired
    private ImgHomestayRepository imgHomestayRepository;
    @Autowired
    private ImgScenicSpotRepository imgScenicSpotRepository;
    @Autowired
    private OwnerHomestayRepository ownerHomestayRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScenicSpotHomestayRepository scenicSpotHomestayRepository;

    public void run(String... args) throws Exception {

        //region
        Region region1 = new Region();
        region1.setCode("RG01");
        region1.setName("Mien Trung");
        regionRepository.save(region1);

        Region region2 = new Region();
        region2.setCode("RG02");
        region2.setName("Mien Bac");
        regionRepository.save(region2);

        Region region3 = new Region();
        region3.setCode("RG03");
        region3.setName("Mien Nam");
        regionRepository.save(region3);

        //province
        Province province1 = new Province();
        province1.setCode("PR01");
        province1.setName("Da Nang");
        province1.setRegion(region1);
        provinceRepository.save(province1);

        Province province2 = new Province();
        province2.setCode("PR02");
        province2.setName("Ha Noi");
        province2.setRegion(region2);
        provinceRepository.save(province2);

        Province province3 = new Province();
        province3.setCode("PR03");
        province3.setName("TP Ho Chi Minh");
        province3.setRegion(region3);
        provinceRepository.save(province3);

        //scenic spot
        ScenicSpot scenicSpot1 = new ScenicSpot();
        scenicSpot1.setName("Quat Lam");
        scenicSpot1.setStar(3.5);
        scenicSpot1.setProvince("Nam Dinh");
        scenicSpot1.setRegion("Mien Bac");
        scenicSpotRepository.save(scenicSpot1);

        ScenicSpot scenicSpot2 = new ScenicSpot();
        scenicSpot2.setName("Ho Hoan Kiem");
        scenicSpot2.setStar(4.5);
        scenicSpot2.setProvince("Ha Noi");
        scenicSpot2.setRegion("Mien Bac");
        scenicSpotRepository.save(scenicSpot2);

        ScenicSpot scenicSpot3 = new ScenicSpot();
        scenicSpot3.setName("TP Ho Chi Minh");
        scenicSpot3.setStar(4.0);
        scenicSpot3.setProvince("TP Ho Chi Minh");
        scenicSpot3.setRegion("Mien Nam");
        scenicSpotRepository.save(scenicSpot3);

        //img scenic spot
        ImgScenicSpot imgScenicSpot1 = new ImgScenicSpot();
        imgScenicSpot1.setScenicSpot(scenicSpot1);
        imgScenicSpot1.setImgUrl("abcxyz");
        imgScenicSpotRepository.save(imgScenicSpot1);

        ImgScenicSpot imgScenicSpot2 = new ImgScenicSpot();
        imgScenicSpot2.setScenicSpot(scenicSpot1);
        imgScenicSpot2.setImgUrl("qwerty");
        imgScenicSpotRepository.save(imgScenicSpot2);

        ImgScenicSpot imgScenicSpot3 = new ImgScenicSpot();
        imgScenicSpot3.setScenicSpot(scenicSpot2);
        imgScenicSpot3.setImgUrl("abcxyz");
        imgScenicSpotRepository.save(imgScenicSpot3);

        //user
        User user1 = new User();
        user1.setCode("US01");
        user1.setName("Vuong Tien Sang");
        user1.setAddress("Ha Noi");
        user1.setGender(true);
        user1.setPhoneNumber("0123456789");
        user1.setEmail("sangvt@gmail.com");
        user1.setUsername("sangvt@gmail.com");
        user1.setPassword("12345678");
        userRepository.save(user1);

        User user2 = new User();
        user2.setCode("US02");
        user2.setName("Vuong Tien Tuan");
        user2.setAddress("Ha Noi");
        user2.setGender(true);
        user2.setPhoneNumber("0123456789");
        user2.setEmail("tuanvt@gmail.com");
        user2.setUsername("tuanvt@gmail.com");
        user2.setPassword("12345678");
        userRepository.save(user2);

        //owner homestay
        OwnerHomestay ownerHomestay1 = new OwnerHomestay();
        ownerHomestay1.setCode("OH01");
        ownerHomestay1.setName("Vu Tien Dung");
        ownerHomestay1.setPhoneNumber("0987654321");
        ownerHomestay1.setEmail("dungvt@gmail.com");
        ownerHomestay1.setUsername("dungvt@gmail.com");
        ownerHomestay1.setPassword("12345678");
        ownerHomestay1.setGender(true);
        ownerHomestayRepository.save(ownerHomestay1);

        OwnerHomestay ownerHomestay2 = new OwnerHomestay();
        ownerHomestay2.setCode("OH02");
        ownerHomestay2.setName("Nguyen Van Ly");
        ownerHomestay2.setPhoneNumber("0987654321");
        ownerHomestay2.setEmail("dungvt@gmail.com");
        ownerHomestay2.setUsername("lynv@gmail.com");
        ownerHomestay2.setPassword("12345678");
        ownerHomestay2.setGender(false);
        ownerHomestayRepository.save(ownerHomestay2);

        //address
        Address address1 = new Address();
        address1.setName("Ha Noi");
        addressRepository.save(address1);

        Address address2 = new Address();
        address2.setName("Da Nang");
        addressRepository.save(address2);

        Address address3 = new Address();
        address3.setName("Nha Trang");
        addressRepository.save(address3);

        //promotion
        Promotion promotion1 = new Promotion();
        promotion1.setName("Chao mung ngay 20/10");
        promotion1.setType(TypePromotion.TIEN);
        promotion1.setValue(50000.0);
        promotionRepository.save(promotion1);

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}