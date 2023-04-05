package com.aptech.blog.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.aptech.blog.model.Blog;
import com.aptech.blog.repository.BlogRepository;



@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;

    public Page<Blog> getAll(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return repository.findAll(pageable);
    }

    public Optional<Blog> getById(int blogId) {
        return repository.findById(blogId);
    }

    public boolean save(@RequestBody Optional<Blog> blogDetails) {
        try {            
            repository.save(blogDetails.get());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateById(int blogId, @RequestBody Optional<Blog> blogDetails) {
        try {
            Optional<Blog> blog = repository.findById(blogId);

            blog.get().setBlogId(blogDetails.get().getBlogId());
            blog.get().setTitle(blogDetails.get().getTitle());
            blog.get().setUrl(blogDetails.get().getUrl());
            blog.get().setRating(blogDetails.get().getRating());

            repository.save(blog.get());

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById( int blogId) {
        try {
            repository.deleteById(blogId);            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }        
    }
}
