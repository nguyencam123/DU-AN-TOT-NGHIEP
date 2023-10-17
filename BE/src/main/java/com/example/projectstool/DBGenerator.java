package com.example.projectstool;

import com.example.demo.entities.Address;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Booking;
import com.example.demo.entities.CancellationPolicyRoom;
import com.example.demo.entities.Comment;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
import com.example.demo.entities.DetailBooking;
import com.example.demo.entities.DetailCancellationPolicyRoom;
import com.example.demo.entities.HistoryServicePack;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgComment;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.entities.ImgScenicSpot;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.Payment;
import com.example.demo.entities.Promotion;
import com.example.demo.entities.Province;
import com.example.demo.entities.Region;
import com.example.demo.entities.Role;
import com.example.demo.entities.ScenicSpot;
import com.example.demo.entities.ServicePack;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.contant.StatusServicePack;
import com.example.demo.infrastructure.contant.TypePromotion;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CancellationPolicyRoomRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.ConvenientHomestayRepository;
import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import com.example.demo.repositories.DetailBookingRepository;
import com.example.demo.repositories.DetailCancellationPolicyRoomRepository;
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
import com.example.demo.repositories.RoleRepository;
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
    private DetailCancellationPolicyRoomRepository detailCancellationPolicyRoomRepository;
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
    private PaymentRepository paymentRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;
    @Autowired
    private ServicePackRepository servicePackRepository;
    @Autowired
    private UserRepository userRepository;

    public void run(String... args) throws Exception {

        //role
        Role role1 = new Role();
        role1.setCode("KH01");
        role1.setName("Supper Admin");
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setCode("AD01");
        role2.setName("Admin");
        roleRepository.save(role2);

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

        //admin
        Admin admin1 = new Admin();
        admin1.setRole(role1);
        admin1.setAddress("Lang Son");
        admin1.setCode("A01");
        admin1.setName("Tran Quang Huy");
        admin1.setGender(true);
        admin1.setUsername("huytq@gmail.com");
        admin1.setPassword("12345678");
        adminRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setRole(role2);
        admin2.setAddress("Nam Dinh");
        admin2.setCode("A02");
        admin2.setName("Nguyen Quoc Cuong");
        admin2.setGender(true);
        admin2.setUsername("cuongnq@gmail.com");
        admin2.setPassword("12345678");
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

        //payment
        Payment payment1 = new Payment();
        payment1.setName("Paypal");
        paymentRepository.save(payment1);

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
        promotion1.setUser(user1);
        promotion1.setType(TypePromotion.TIEN);
        promotion1.setValue(50000.0);
        promotionRepository.save(promotion1);

        //service pack
        ServicePack servicePack1 = new ServicePack();
        servicePack1.setCode("SP01");
        servicePack1.setName("Goi Free");
        servicePack1.setPrice(new BigDecimal(0));
        servicePack1.setTime(1);
        servicePack1.setQuantityPost(1);
        servicePackRepository.save(servicePack1);

        ServicePack servicePack2 = new ServicePack();
        servicePack2.setCode("SP02");
        servicePack2.setName("Goi Thuong");
        servicePack2.setPrice(new BigDecimal(200000));
        servicePack2.setTime(3);
        servicePack2.setQuantityPost(3);
        servicePackRepository.save(servicePack2);

        ServicePack servicePack3 = new ServicePack();
        servicePack3.setCode("SP03");
        servicePack3.setName("Goi VIP");
        servicePack3.setPrice(new BigDecimal(500000));
        servicePack3.setTime(6);
        servicePack3.setQuantityPost(10);
        servicePackRepository.save(servicePack3);

        //homestay
        Homestay homestay1 = new Homestay();
        homestay1.setName("Fpoly Homestay");
        homestay1.setStar(3.5);
        homestay1.setAddress(address1);
        homestay1.setRegion(region2);
        homestay1.setProvince(province1);
        homestay1.setPromotion(promotion1);
        homestay1.setPayment(payment1);
        homestay1.setServicePack(servicePack1);
        homestay1.setOwnerHomestay(ownerHomestay1);
        homestay1.setStatusServicePack(StatusServicePack.CON_HAN);
        homestayRepository.save(homestay1);

        Homestay homestay2 = new Homestay();
        homestay2.setName("Son Tra Homestay");
        homestay2.setStar(4.0);
        homestay2.setAddress(address2);
        homestay2.setRegion(region1);
        homestay2.setProvince(province1);
        homestay2.setPayment(payment1);
        homestay2.setServicePack(servicePack2);
        homestay2.setOwnerHomestay(ownerHomestay1);
        homestay2.setStatusServicePack(StatusServicePack.CON_HAN);
        homestayRepository.save(homestay2);

        Homestay homestay3 = new Homestay();
        homestay3.setName("Celadon Homestay");
        homestay3.setStar(4.5);
        homestay3.setAddress(address3);
        homestay3.setRegion(region3);
        homestay3.setProvince(province3);
        homestay3.setPayment(payment1);
        homestay3.setServicePack(servicePack3);
        homestay3.setOwnerHomestay(ownerHomestay2);
        homestay3.setStatusServicePack(StatusServicePack.CON_HAN);
        homestayRepository.save(homestay3);

        //comment
        Comment comment1 = new Comment();
        comment1.setComment("Good");
        comment1.setHomestay(homestay1);
        comment1.setPoint(8.0);
        comment1.setUser(user1);
        commentRepository.save(comment1);

        //img comment
        ImgComment imgComment1 = new ImgComment();
        imgComment1.setComment(comment1);
        imgComment1.setImgUrl("scasdqweqeq");
        imgCommentRepository.save(imgComment1);

        ImgComment imgComment2 = new ImgComment();
        imgComment2.setComment(comment1);
        imgComment2.setImgUrl("abcxyz");
        imgCommentRepository.save(imgComment2);

        //img homestay
        ImgHomestay imgHomestay1 = new ImgHomestay();
        imgHomestay1.setHomestay(homestay1);
        imgHomestay1.setImgUrl("jkluio");
        imgHomestayRepository.save(imgHomestay1);

        ImgHomestay imgHomestay2 = new ImgHomestay();
        imgHomestay2.setHomestay(homestay2);
        imgHomestay2.setImgUrl("abcxyz");
        imgHomestayRepository.save(imgHomestay2);

        ImgHomestay imgHomestay3 = new ImgHomestay();
        imgHomestay3.setHomestay(homestay3);
        imgHomestay3.setImgUrl("qwewcx");
        imgHomestayRepository.save(imgHomestay3);

        //history service pack
        HistoryServicePack historyServicePack1 = new HistoryServicePack();
        historyServicePack1.setServicePack(servicePack1);
        historyServicePack1.setHomestay(homestay1);
        historyServicePackRepository.save(historyServicePack1);

        HistoryServicePack historyServicePack2 = new HistoryServicePack();
        historyServicePack2.setServicePack(servicePack2);
        historyServicePack2.setHomestay(homestay2);
        historyServicePackRepository.save(historyServicePack2);

        HistoryServicePack historyServicePack3 = new HistoryServicePack();
        historyServicePack3.setServicePack(servicePack3);
        historyServicePack3.setHomestay(homestay3);
        historyServicePackRepository.save(historyServicePack3);

        //cancellation policy room
        CancellationPolicyRoom cancellationPolicyRoom1 = new CancellationPolicyRoom();
        cancellationPolicyRoom1.setName("Huy mien phi");
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom1);

        CancellationPolicyRoom cancellationPolicyRoom2 = new CancellationPolicyRoom();
        cancellationPolicyRoom2.setName("Huy khong hoan tien");
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom2);

        CancellationPolicyRoom cancellationPolicyRoom3 = new CancellationPolicyRoom();
        cancellationPolicyRoom3.setName("Huy dac biet");
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom3);

        //detail cancellation policy room
        DetailCancellationPolicyRoom detailCancellationPolicyRoom1 = new DetailCancellationPolicyRoom();
        detailCancellationPolicyRoom1.setCancellationPolicyRoom(cancellationPolicyRoom1);
        detailCancellationPolicyRoom1.setPrice(new BigDecimal(0));
        detailCancellationPolicyRoom1.setHomestay(homestay1);
        detailCancellationPolicyRoomRepository.save(detailCancellationPolicyRoom1);

        DetailCancellationPolicyRoom detailCancellationPolicyRoom2 = new DetailCancellationPolicyRoom();
        detailCancellationPolicyRoom2.setCancellationPolicyRoom(cancellationPolicyRoom2);
        detailCancellationPolicyRoom2.setPrice(new BigDecimal(500000));
        detailCancellationPolicyRoom2.setHomestay(homestay2);
        detailCancellationPolicyRoomRepository.save(detailCancellationPolicyRoom2);

        DetailCancellationPolicyRoom detailCancellationPolicyRoom3 = new DetailCancellationPolicyRoom();
        detailCancellationPolicyRoom3.setCancellationPolicyRoom(cancellationPolicyRoom3);
        detailCancellationPolicyRoom3.setPrice(new BigDecimal(3000000));
        detailCancellationPolicyRoom3.setHomestay(homestay3);
        detailCancellationPolicyRoomRepository.save(detailCancellationPolicyRoom3);

        //convenient homestay type
        ConvenientHomestayType convenientHomestayType1 = new ConvenientHomestayType();
        convenientHomestayType1.setName("type 1");
        convenientHomestayType1.setUser(user1);
        convenientHomestayTypeRepository.save(convenientHomestayType1);

        ConvenientHomestayType convenientHomestayType2 = new ConvenientHomestayType();
        convenientHomestayType2.setName("type 2");
        convenientHomestayType2.setUser(user2);
        convenientHomestayTypeRepository.save(convenientHomestayType2);

        //convenient homestay
        ConvenientHomestay convenientHomestay1 = new ConvenientHomestay();
        convenientHomestay1.setHomestay(homestay1);
        convenientHomestay1.setName("convenient homestay 1");
        convenientHomestay1.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay1);

        ConvenientHomestay convenientHomestay2 = new ConvenientHomestay();
        convenientHomestay2.setHomestay(homestay2);
        convenientHomestay2.setName("convenient homestay 2");
        convenientHomestay2.setConvenientHomestayType(convenientHomestayType1);
        convenientHomestayRepository.save(convenientHomestay2);

        //booking
        Booking booking1 = new Booking();
        booking1.setUser(user1);
        booking1.setTotalPrice(new BigDecimal(1000000));
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setUser(user2);
        booking2.setTotalPrice(new BigDecimal(1300000));
        bookingRepository.save(booking2);

        //detail booking
        DetailBooking detailBooking1 = new DetailBooking();
        detailBooking1.setPrice(new BigDecimal(1000000));
        detailBooking1.setBooking(booking1);
        detailBookingRepository.save(detailBooking1);

        DetailBooking detailBooking2 = new DetailBooking();
        detailBooking2.setPrice(new BigDecimal(1300000));
        detailBooking2.setBooking(booking2);
        detailBookingRepository.save(detailBooking2);

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}