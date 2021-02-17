package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Role;
import com.mamuya.datrastocospringbootapi.repository.RoleRepository;
import com.mamuya.datrastocospringbootapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return roleRepository.existsById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteById(int id) {
        roleRepository.deleteById(id);
    }
}
