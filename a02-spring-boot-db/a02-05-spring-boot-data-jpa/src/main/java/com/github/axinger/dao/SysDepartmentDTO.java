package com.github.axinger.dao;

import com.github.axinger.entity.SysDepartmentEntity;
import com.github.axinger.entity.SysUserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository
public interface SysDepartmentDTO extends JpaRepository<SysDepartmentEntity, Long> {


}
