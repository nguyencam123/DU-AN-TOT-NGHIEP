package com.example.projectstool;

import com.example.demo.entities.Admin;
import com.example.demo.entities.ApprovalHistory;
import com.example.demo.entities.Booking;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Comment;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
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
import com.example.demo.infrastructure.contant.TypePromotion;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.ApprovalHistoryRepository;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.ConvenientHomestayRepository;
import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import com.example.demo.repositories.HomestayRepository;
import com.example.demo.repositories.ImgCommentRepository;
import com.example.demo.repositories.ImgHomestayRepository;
import com.example.demo.repositories.ImgScenicSpotRepository;
import com.example.demo.repositories.OwnerHomestayRepository;
import com.example.demo.repositories.PromotionRepository;
import com.example.demo.repositories.ProvinceRepository;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.repositories.ScenicSpotHomestayRepository;
import com.example.demo.repositories.ScenicSpotRepository;
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
    private AdminRepository adminRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ConvenientHomestayRepository convenientHomestayRepository;
    @Autowired
    private ConvenientHomestayTypeRepository convenientHomestayTypeRepository;
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
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ApprovalHistoryRepository approvalHistoryRepository;

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
        province1.setName("TP Da Nang");
        province1.setRegion(region1);
        provinceRepository.save(province1);

        Province province2 = new Province();
        province2.setCode("PR02");
        province2.setName("TP Ha Noi");
        province2.setRegion(region2);
        provinceRepository.save(province2);

        Province province3 = new Province();
        province3.setCode("PR03");
        province3.setName("TP Ho Chi Minh");
        province3.setRegion(region3);
        provinceRepository.save(province3);

        Province province4 = new Province();
        province4.setCode("PR04");
        province4.setName("TP Nha Trang");
        province4.setRegion(region3);
        provinceRepository.save(province4);

        Province province5 = new Province();
        province5.setCode("PR05");
        province5.setName("TP Hue");
        province5.setRegion(region1);
        provinceRepository.save(province5);

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

        //admin
        Admin admin1 = new Admin();
        admin1.setCode("AD01");
        admin1.setName("Tran Quang Huy");
        admin1.setEmail("huytq@gmail.com");
        admin1.setAddress("Lang son");
        admin1.setGender(true);
        admin1.setPhoneNumber("0123456777");
        admin1.setUsername("huytq@gmail.com");
        admin1.setPassword("12345678");
        admin1.setAvatarUrl("avcqeqw");
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setCode("AD02");
        admin2.setName("Nguen Quoc Cuong");
        admin2.setEmail("cuongnq@gmail.com");
        admin2.setAddress("Lang son");
        admin2.setGender(true);
        admin2.setPhoneNumber("0123456333");
        admin2.setUsername("cuongnq@gmail.com");
        admin2.setPassword("12345678");
        admin2.setAvatarUrl("xyzjkl");
        adminRepository.save(admin2);

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
        user2.setPhoneNumber("0987654321");
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
        ownerHomestay2.setPhoneNumber("0987654414");
        ownerHomestay2.setEmail("lynv@gmail.com");
        ownerHomestay2.setUsername("lynv@gmail.com");
        ownerHomestay2.setPassword("12345678");
        ownerHomestay2.setGender(false);
        ownerHomestayRepository.save(ownerHomestay2);

        //promotion
        Promotion promotion1 = new Promotion();
        promotion1.setName("Chao mung ngay 20/10");
        promotion1.setType(TypePromotion.TIEN);
        promotion1.setValue(50000.0);
        promotionRepository.save(promotion1);

        //homestay
        Homestay homestay1 = new Homestay();
        homestay1.setName("Fpoly Homestay");
        homestay1.setAddress("Đường Trịnh Văn Bô, Nam Từ Liêm, Hà Nội");
        homestay1.setRegion(region2);
        homestay1.setProvince(province2);
        homestay1.setPromotion(promotion1);
        homestay1.setOwnerHomestay(ownerHomestay1);
        homestay1.setPoint(8.0);
        homestay1.setPrice(new BigDecimal(1200000));
        homestay1.setNumberPerson(10);
        homestayRepository.save(homestay1);

        Homestay homestay2 = new Homestay();
        homestay2.setName("H2 homestay");
        homestay2.setAddress("số nhà 11 ngõ 465 Đường Hoàng Hoa Thám, Ba Đình, Hà Nội, Quận Ba Đình, Hà Nội");
        homestay2.setRegion(region2);
        homestay2.setProvince(province2);
        homestay2.setPromotion(promotion1);
        homestay2.setOwnerHomestay(ownerHomestay1);
        homestay2.setPoint(8.8);
        homestay2.setPrice(new BigDecimal(1500000));
        homestay2.setNumberPerson(12);
        homestayRepository.save(homestay2);

        Homestay homestay3 = new Homestay();
        homestay3.setName("Sweet Home Homestay");
        homestay3.setAddress("41 An Thượng 3, Đà Nẵng, Việt Nam");
        homestay3.setRegion(region1);
        homestay3.setProvince(province1);
        homestay3.setPromotion(promotion1);
        homestay3.setOwnerHomestay(ownerHomestay1);
        homestay3.setPoint(9.0);
        homestay3.setPrice(new BigDecimal(1000000));
        homestay3.setNumberPerson(8);
        homestayRepository.save(homestay3);

        Homestay homestay4 = new Homestay();
        homestay4.setName("Cozy Homestay");
        homestay4.setAddress("100 Cô Giang 21, Quận 1, TP.Hồ Chí Minh, Việt Nam");
        homestay4.setRegion(region3);
        homestay4.setProvince(province3);
        homestay4.setPromotion(promotion1);
        homestay4.setOwnerHomestay(ownerHomestay2);
        homestay4.setPoint(7.0);
        homestay4.setPrice(new BigDecimal(2000000));
        homestay4.setNumberPerson(12);
        homestayRepository.save(homestay4);

        Homestay homestay5 = new Homestay();
        homestay5.setName("Melissa Homestay Nha Trang");
        homestay5.setAddress("100A Trần Phú, Lộc Thọ, Nha Trang, Khánh Hòa, Việt Nam");
        homestay5.setRegion(region3);
        homestay5.setProvince(province4);
        homestay5.setPromotion(promotion1);
        homestay5.setOwnerHomestay(ownerHomestay2);
        homestay5.setPoint(6.9);
        homestay5.setPrice(new BigDecimal(800000));
        homestay1.setNumberPerson(10);
        homestayRepository.save(homestay5);

        //img Homestay
        ImgHomestay imgHomestay1 = new ImgHomestay();
        imgHomestay1.setImgUrl("xyzqeqr");
        imgHomestay1.setHomestay(homestay1);
        imgHomestayRepository.save(imgHomestay1);

        ImgHomestay imgHomestay2 = new ImgHomestay();
        imgHomestay2.setImgUrl("asdfgh");
        imgHomestay2.setHomestay(homestay2);
        imgHomestayRepository.save(imgHomestay2);

        ImgHomestay imgHomestay3 = new ImgHomestay();
        imgHomestay3.setImgUrl("qwertyu");
        imgHomestay3.setHomestay(homestay3);
        imgHomestayRepository.save(imgHomestay3);

        ImgHomestay imgHomestay4 = new ImgHomestay();
        imgHomestay4.setImgUrl("ghjklli");
        imgHomestay4.setHomestay(homestay4);
        imgHomestayRepository.save(imgHomestay4);

        ImgHomestay imgHomestay5 = new ImgHomestay();
        imgHomestay5.setImgUrl("ruiootpp");
        imgHomestay5.setHomestay(homestay5);
        imgHomestayRepository.save(imgHomestay5);

        //Scenic Spot Homestay
        ScenicSpotHomestay scenicSpotHomestay1 = new ScenicSpotHomestay();
        scenicSpotHomestay1.setScenicSpot(scenicSpot2);
        scenicSpotHomestay1.setHomestay(homestay1);
        scenicSpotHomestayRepository.save(scenicSpotHomestay1);

        ScenicSpotHomestay scenicSpotHomestay2 = new ScenicSpotHomestay();
        scenicSpotHomestay2.setScenicSpot(scenicSpot3);
        scenicSpotHomestay2.setHomestay(homestay4);
        scenicSpotHomestayRepository.save(scenicSpotHomestay2);

        //comment
        Comment comment1 = new Comment();
        comment1.setComment("Good");
        comment1.setUser(user1);
        comment1.setHomestay(homestay1);
        comment1.setPoint(8.0);
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setComment("Very Good");
        comment2.setUser(user2);
        comment2.setHomestay(homestay1);
        comment2.setPoint(8.0);
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setComment("Not ok");
        comment3.setUser(user1);
        comment3.setHomestay(homestay5);
        comment3.setPoint(6.8);
        commentRepository.save(comment3);

        Comment comment4 = new Comment();
        comment4.setComment("Good");
        comment4.setUser(user1);
        comment4.setHomestay(homestay3);
        comment4.setPoint(8.5);
        commentRepository.save(comment4);

        Comment comment5 = new Comment();
        comment5.setComment("Not Bad");
        comment5.setUser(user2);
        comment5.setHomestay(homestay4);
        comment5.setPoint(7.8);
        commentRepository.save(comment5);

        //img comment
        ImgComment imgComment1 = new ImgComment();
        imgComment1.setImgUrl("acbxaqwe");
        imgComment1.setComment(comment1);
        imgCommentRepository.save(imgComment1);

        //Convenient Homestay Type
        ConvenientHomestayType convenientHomestayType1 = new ConvenientHomestayType();
        convenientHomestayType1.setName("Type 1");
        convenientHomestayTypeRepository.save(convenientHomestayType1);

        ConvenientHomestayType convenientHomestayType2 = new ConvenientHomestayType();
        convenientHomestayType2.setName("Type 2");
        convenientHomestayTypeRepository.save(convenientHomestayType2);


        //Convenient homestay
        ConvenientHomestay convenientHomestay1 = new ConvenientHomestay();
        convenientHomestay1.setName("Wifi miễn phí");
        convenientHomestay1.setHomestay(homestay1);
        convenientHomestay1.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay1);

        ConvenientHomestay convenientHomestay2 = new ConvenientHomestay();
        convenientHomestay2.setName("Wifi miễn phí");
        convenientHomestay2.setHomestay(homestay2);
        convenientHomestay2.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay2);

        ConvenientHomestay convenientHomestay3 = new ConvenientHomestay();
        convenientHomestay3.setName("Wifi miễn phí");
        convenientHomestay3.setHomestay(homestay3);
        convenientHomestay3.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay3);

        ConvenientHomestay convenientHomestay4 = new ConvenientHomestay();
        convenientHomestay4.setName("Hồ bơi");
        convenientHomestay4.setHomestay(homestay4);
        convenientHomestay4.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay4);

        ConvenientHomestay convenientHomestay5 = new ConvenientHomestay();
        convenientHomestay5.setName("Chỗ đỗ xe");
        convenientHomestay5.setHomestay(homestay5);
        convenientHomestay5.setConvenientHomestayType(convenientHomestayType2);
        convenientHomestayRepository.save(convenientHomestay5);

        ConvenientHomestay convenientHomestay6 = new ConvenientHomestay();
        convenientHomestay6.setName("Wifi miễn phí");
        convenientHomestay6.setHomestay(homestay4);
        convenientHomestay6.setConvenientHomestayType(convenientHomestayType2);
        convenientHomestayRepository.save(convenientHomestay6);

        ConvenientHomestay convenientHomestay7 = new ConvenientHomestay();
        convenientHomestay7.setName("Wifi miễn phí");
        convenientHomestay7.setHomestay(homestay5);
        convenientHomestay7.setConvenientHomestayType(convenientHomestayType2);
        convenientHomestayRepository.save(convenientHomestay7);

        //booking
        Booking booking1 = new Booking();
        booking1.setUser(user1);
        booking1.setTotalPrice(new BigDecimal(1200000));
        booking1.setPromotion(promotion1);
        booking1.setHomestay(homestay1);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setUser(user2);
        booking2.setTotalPrice(new BigDecimal(1200000));
        booking2.setHomestay(homestay2);
        bookingRepository.save(booking2);

        //cart
        Cart cart1 = new Cart();
        cart1.setUser(user1);
        cart1.setPromotion(promotion1);
        cart1.setHomestay(homestay3);
        cart1.setTotalPrice(new BigDecimal(1200000));
        cartRepository.save(cart1);

        Cart cart2 = new Cart();
        cart2.setUser(user1);
        cart2.setHomestay(homestay1);
        cart2.setTotalPrice(new BigDecimal(1000000));
        cartRepository.save(cart2);

        Cart cart3 = new Cart();
        cart3.setUser(user2);
        cart3.setHomestay(homestay5);
        cart3.setTotalPrice(new BigDecimal(1500000));
        cartRepository.save(cart3);

        // approval history
        ApprovalHistory approvalHistory1 = new ApprovalHistory();
        approvalHistory1.setAdmin(admin1);
        approvalHistory1.setHomestay(homestay1);
        approvalHistoryRepository.save(approvalHistory1);

        ApprovalHistory approvalHistory2 = new ApprovalHistory();
        approvalHistory2.setAdmin(admin1);
        approvalHistory2.setHomestay(homestay2);
        approvalHistoryRepository.save(approvalHistory2);

        ApprovalHistory approvalHistory3 = new ApprovalHistory();
        approvalHistory3.setAdmin(admin1);
        approvalHistory3.setHomestay(homestay3);
        approvalHistoryRepository.save(approvalHistory3);

        ApprovalHistory approvalHistory4 = new ApprovalHistory();
        approvalHistory4.setAdmin(admin1);
        approvalHistory4.setHomestay(homestay4);
        approvalHistoryRepository.save(approvalHistory4);

        ApprovalHistory approvalHistory5 = new ApprovalHistory();
        approvalHistory5.setAdmin(admin1);
        approvalHistory5.setHomestay(homestay5);
        approvalHistoryRepository.save(approvalHistory5);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}