package com.example.projectstool;

import com.example.demo.entities.Province;
import com.example.demo.entities.Region;
import com.example.demo.entities.Role;
import com.example.demo.entities.ScenicSpot;
import com.example.demo.repositories.ProvinceRepository;
import com.example.demo.repositories.RegionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.ScenicSpotRepository;
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
    private ProvinceRepository provinceRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ScenicSpotRepository scenicSpotRepository;

    public void run(String... args) throws Exception {

        Role role1 = new Role();
        role1.setCode("KH01");
        role1.setName("Khach hang");
        roleRepository.save(role1);

        Role role2 = new Role();
        role1.setCode("AD01");
        role1.setName("Admin");
        roleRepository.save(role2);

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

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}