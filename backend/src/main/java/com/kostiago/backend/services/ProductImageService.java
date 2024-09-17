package com.kostiago.backend.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kostiago.backend.dto.ProductImageDTO;
import com.kostiago.backend.entities.Product;
import com.kostiago.backend.entities.ProductImage;
import com.kostiago.backend.repositories.ProductImageRepository;
import com.kostiago.backend.repositories.ProductRepository;
import com.kostiago.backend.services.exceptions.ResourceNotFoundExeception;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductImageDTO> findAllPaged(Integer page, Integer size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<ProductImage> productImage = repository.findAll(pageable);

        return productImage.map(img -> new ProductImageDTO(img));
    }

    @Transactional(readOnly = true)
    public ProductImageDTO findById(Long id) {

        Optional<ProductImage> object = repository.findById(id);
        ProductImage entity = object.orElseThrow(() -> new ResourceNotFoundExeception("Imagem não encontrada"));
        return new ProductImageDTO(entity);
    }

    @Transactional
    public ProductImageDTO insert(Long id, MultipartFile file) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeception("Produto não encontrado"));

        ProductImage object = new ProductImage();

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                String imageName = String.valueOf(product.getId() + file.getOriginalFilename());

                Optional<ProductImage> existingImage = repository.findByName(imageName);

                if(existingImage.isPresent()) {
                    throw new ResourceNotFoundExeception("Imagem ja existe");
                }

                Path path = Paths.get("c:/imagens/" + imageName);
                Files.write(path, bytes);

                object.setName(imageName);

                System.err.println("AQUI");

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar o arquivo de imagem");
        }

        object.setProduct(product);
        repository.saveAndFlush(object);
        System.err.println("OU AQUI");
        return new ProductImageDTO(object);

    }

}
