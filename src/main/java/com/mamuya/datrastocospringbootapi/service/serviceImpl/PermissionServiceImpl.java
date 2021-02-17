package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Permission;
import com.mamuya.datrastocospringbootapi.repository.PermissionRepository;
import com.mamuya.datrastocospringbootapi.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findById(int id) {
        Optional<Permission> permission = permissionRepository.findById(id);
        return permission.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return permissionRepository.existsById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public long count() {
        return permissionRepository.count();
    }

    @Override
    public void deleteById(int id) {
        permissionRepository.deleteById(id);
    }
}
