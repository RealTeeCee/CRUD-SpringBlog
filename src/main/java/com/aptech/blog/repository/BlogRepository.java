package com.aptech.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.aptech.blog.model.Blog;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer>, CrudRepository<Blog, Integer> {
    
}
