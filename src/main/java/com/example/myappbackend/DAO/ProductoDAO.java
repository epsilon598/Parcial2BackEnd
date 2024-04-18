package com.example.myappbackend.DAO;

import com.example.myappbackend.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDAO extends JpaRepository<Producto,Long> { }
