package com.siemens.metal_forming.repository;

import com.siemens.metal_forming.entity.Plc;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DisplayName("<= Plc Repository Specification =>")
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlcRepositorySpec {
    @Autowired
    private PlcRepository plcRepository;



    @Test @DisplayName("plc with address \"null\" can not be stored") @Disabled
    void storePlcWithNullIpAddress(){
        Plc plc = new Plc();
        plcRepository.save(plc);
    }

}
