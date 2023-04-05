package com.aptech.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blog.model.Blog;
import com.aptech.blog.service.BlogService;

@RestController 

@CrossOrigin("http://localhost:3000")
public class BlogController {

    @Autowired
    private BlogService service;

    @GetMapping(path = "/blogs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Blog>> getBlogs(
        @RequestParam(defaultValue = "${paging.pageNo}") int pageNo, 
        @RequestParam(defaultValue = "${paging.pageSize}") int pageSize) {

        return new ResponseEntity<Page<Blog>>(service.getAll(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = "/blog/{blogId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Blog>> getBlog(int blogId) {   
        return new ResponseEntity<Optional<Blog>>(service.getById(blogId), HttpStatus.OK);
    }

    @PostMapping(path = "/blog",consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> createBlog(int blogId, @RequestBody Optional<Blog> blogDetails) {   
        return new ResponseEntity<Boolean>(service.save(blogDetails), HttpStatus.OK);
    }

    @PutMapping(path = "/blog/{blogId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> updateBlog(int blogId, @RequestBody Optional<Blog> blogDetails) {   
        return new ResponseEntity<Boolean>(service.updateById(blogId, blogDetails), HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/blog/{blogId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteBlog(int blogId) {   
        return new ResponseEntity<Boolean>(service.deleteById(blogId), HttpStatus.OK);
    }
}
