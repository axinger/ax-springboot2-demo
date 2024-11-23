package com.github.axinger.service;

import com.github.axinger.dto.DIY;
import com.github.axinger.dto.DateDTO;
import com.github.axinger.dto.ObjectDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
//@Valid
public interface ValidationService {
    void strNo(String name);

    void strNotBlank(@NotBlank(message = "不能空字符串,也不能空格") String name);

    void strNotEmpty(@NotEmpty(message = "字符串不能空,可以空格") String name);

    void strNotNull(String name);

    @Validated({DIY.Add.class, Default.class})
        // 不能Controller,时候必须放方法上面
    void funOne(@Valid ObjectDTO one);
//    void funOne(@Valid  @Validated({DIY.Add.class, Default.class}) ObjectDTO one); // 不可以
//    void funOne(@Validated({DIY.Add.class, Default.class}) ObjectDTO one); // 不可以
//    void funOne(ObjectDTO one); // 不可以

    @Validated({DIY.Edit.class})
    void funList(@Valid List<ObjectDTO> list);

    @Validated
    void testDate(@Valid DateDTO dto);
}
