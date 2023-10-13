package com.example.projectstool;

import com.example.demo.entities.Booking;
import com.example.demo.entities.CancellationPolicyRoom;
import com.example.demo.entities.Comment;
import com.example.demo.entities.ConvenientHotel;
import com.example.demo.entities.ConvenientHotelType;
import com.example.demo.entities.ConvenientRoom;
import com.example.demo.entities.ConvenientRoomType;
import com.example.demo.entities.CustomerRank;
import com.example.demo.entities.DetailBooking;
import com.example.demo.entities.DetailRoom;
import com.example.demo.entities.Hotel;
import com.example.demo.entities.Province;
import com.example.demo.entities.Region;
import com.example.demo.entities.Role;
import com.example.demo.entities.Room;
import com.example.demo.entities.Sale;
import com.example.demo.entities.ScenicSpot;
import com.example.demo.entities.User;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.infrastructure.contant.TypeSale;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.CancellationPolicyRoomRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.ConvenientHotelRepository;
import com.example.demo.repositories.ConvenientHotelTypeRepository;
import com.example.demo.repositories.ConvenientRoomRepository;
import com.example.demo.repositories.ConvenientRoomTypeRepository;
import com.example.demo.repositories.ProvinceRepository;
import com.example.demo.repositories.CustomerRankRepository;
import com.example.demo.repositories.DetailBookingRepository;
import com.example.demo.repositories.DetailRoomRepository;
import com.example.demo.repositories.HotelRepository;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.SaleRepository;
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
    private ProvinceRepository provinceRepository;
    @Autowired
    private CustomerRankRepository customerRankRepository;
    @Autowired
    private DetailBookingRepository detailBookingRepository;
    @Autowired
    private DetailRoomRepository detailRoomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ConvenientHotelRepository convenientHotelRepository;
    @Autowired
    private ConvenientRoomRepository convenientRoomRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    public void run(String... args) throws Exception {

        CustomerRank customerRank1 = new CustomerRank();
        customerRank1.setName("Dong");
        customerRank1.setMinimunPoint(1000);
        customerRank1.setTypeSale(TypeSale.TIEN);
        customerRank1.setStatus(Status.HOAT_DONG);
        customerRankRepository.save(customerRank1);

        Role role1 = new Role();
        role1.setCode("0");
        role1.setName("Khach hang");
        roleRepository.save(role1);

        Role role2 = new Role();
        role1.setCode("1");
        role1.setName("Admin");
        roleRepository.save(role2);

        Role role3 = new Role();
        role1.setCode("2");
        role1.setName("Super Admin");
        roleRepository.save(role3);

        Role role4 = new Role();
        role1.setCode("3");
        role1.setName("Chủ homestay");
        roleRepository.save(role4);

        Hotel hotel1 = new Hotel();
        hotel1.setName("Tuy Anh");
        hotel1.setAddress("Ha Noi");
        hotel1.setStar(4.1);
        hotelRepository.save(hotel1);

        User user1 = new User();
        user1.setCode("US01");
        user1.setName("Nguyen Quoc Cuong");
        user1.setAddress("Nam Dinh");
        user1.setUsername("user@gmail.com");
        user1.setPassword("12345678");
        user1.setCustomerRank(customerRank1);
        user1.setRole(role1);
        userRepository.save(user1);

        User user2 = new User();
        user2.setCode("US02");
        user2.setName("Vuong Tien Sang");
        user2.setAddress("Ha Noi");
        user2.setUsername("admin@gmail.com");
        user2.setPassword("12345678");
        user2.setCustomerRank(customerRank1);
        user2.setRole(role2);
        userRepository.save(user2);

        User user3 = new User();
        user3.setCode("US03");
        user3.setName("Tran Quang Huy");
        user3.setAddress("Ha Noi");
        user3.setUsername("superadmin@gmail.com");
        user3.setPassword("12345678");
        user3.setCustomerRank(customerRank1);
        user3.setRole(role3);
        userRepository.save(user3);

        User user4 = new User();
        user4.setCode("US04");
        user4.setName("Nguyen Manh Cam");
        user4.setAddress("Ha Noi");
        user4.setUsername("homestayowner@gmail.com");
        user4.setPassword("12345678");
        user4.setCustomerRank(customerRank1);
        user4.setRole(role4);
        userRepository.save(user4);

        Sale sale1 = new Sale();
        sale1.setUser(user1);
        sale1.setName("Dai ha gia");
        sale1.setType(TypeSale.TIEN);
        saleRepository.save(sale1);

        Booking booking1 = new Booking();
        booking1.setUser(user1);
        booking1.setSale(sale1);
        booking1.setCustomerRank(customerRank1);
        bookingRepository.save(booking1);

        Comment comment1 = new Comment();
        comment1.setHotel(hotel1);
        comment1.setComment("Khach san dep vl");
        comment1.setPoint(8.0);
        comment1.setUser(user1);
        commentRepository.save(comment1);

        Room room1 = new Room();
        room1.setHotel(hotel1);
        room1.setCode("R01");
        room1.setName("Phong luxury");
        room1.setAcreage(30.0);
        room1.setMaxAdult(3);
        room1.setMaxChildren(2);
        room1.setBath(1);
        room1.setKingBed(1);
        room1.setQueenBed(1);
        room1.setSingleBed(1);
        roomRepository.save(room1);

        DetailRoom detailRoom1 = new DetailRoom();
        detailRoom1.setRoom(room1);
        detailRoom1.setPrice(new BigDecimal(20000000));
        detailRoom1.setPaymentType(1);
        detailRoomRepository.save(detailRoom1);

        CancellationPolicyRoom cancellationPolicyRoom1 = new CancellationPolicyRoom();
        cancellationPolicyRoom1.setName("Huy mien phi");
        cancellationPolicyRoom1.setPrice(new BigDecimal(10000000));
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom1);

        CancellationPolicyRoom cancellationPolicyRoom2 = new CancellationPolicyRoom();
        cancellationPolicyRoom1.setName("Huy khong hoan tien");
        cancellationPolicyRoom1.setPrice(new BigDecimal(5000000));
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom2);

        CancellationPolicyRoom cancellationPolicyRoom3 = new CancellationPolicyRoom();
        cancellationPolicyRoom1.setName("Huy dac biet");
        cancellationPolicyRoom1.setPrice(new BigDecimal(50000000));
        cancellationPolicyRoomRepository.save(cancellationPolicyRoom3);

        ConvenientHotelType convenientHotelType1 = new ConvenientHotelType();
        convenientHotelType1.setName("Gan cho");
        convenientHotelTypeRepository.save(convenientHotelType1);

        ConvenientRoomType convenientRoomType1 = new ConvenientRoomType();
        convenientRoomType1.setName("Wifi mien phi");
        convenientRoomTypeRepository.save(convenientRoomType1);

        ConvenientHotel convenientHotel1 = new ConvenientHotel();
        convenientHotel1.setName("Khach san VIP");
        convenientHotel1.setHotel(hotel1);
        convenientHotel1.setConvenientHotelType(convenientHotelType1);
        convenientHotelRepository.save(convenientHotel1);

        ConvenientRoom convenientRoom1 = new ConvenientRoom();
        convenientRoom1.setRoom(room1);
        convenientRoom1.setName("Phong VIP");
        convenientRoom1.setConvenientRoomType(convenientRoomType1);
        convenientRoomRepository.save(convenientRoom1);

        Region region1 = new Region();
        region1.setCode("RG01");
        region1.setName("TP Nam Dinh");
        regionRepository.save(region1);

        Region region2 = new Region();
        region1.setCode("RG02");
        region1.setName("Hai Hau");
        regionRepository.save(region2);

        Province province1 = new Province();
        province1.setCode("PR01");
        province1.setName("Nam Dinh");
        province1.setRegion(region1);
        provinceRepository.save(province1);

        ScenicSpot scenicSpot1 = new ScenicSpot();
        scenicSpot1.setName("Quat Lam");
        scenicSpot1.setStar(4.5);
        scenicSpotRepository.save(scenicSpot1);

        DetailBooking detailBooking1 = new DetailBooking();
        detailBooking1.setBooking(booking1);
        detailBooking1.setDetailRoom(detailRoom1);
        detailBooking1.setPrice(new BigDecimal(1500000));
        detailBooking1.setDateStart(1672510731L);
        detailBooking1.setDateEnd(1675275531L);
        detailBookingRepository.save(detailBooking1);

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}