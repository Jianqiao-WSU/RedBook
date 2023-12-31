package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    /**
     * JPQL
     * use Entity name other than database table name.
     * index Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = ?1 or p.title = ?2")
    Post getPostByIdOrTitleWithJPQLIndexParameters(Long id, String title);

    /**
     * JPQL
     * use Entity name other than database table name.
     * named Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = :key or p.title = :title")
    Post getPostByIdOrTitleWithJPQLNamedParameters(@Param("key") Long id,
                                                   @Param("title") String title);

    /**
     * SQL
     * use database table name.
     * index Parameters
     * @return post
     */
    @Query(value = "select * from posts p where p.id = ?1 or p.title = ?2", nativeQuery = true)
    Post getPostByIdOrTitleWithSQLIndexParameters(Long id, String title);

    /**
     * SQL
     * use database table name.
     * named Parameters
     * @return post
     */
    @Query(value = "select * from posts p where p.id = :key or p.title = :title", nativeQuery = true)
    Post getPostByIdOrTitleWithSQLNamedParameters(@Param("key") Long id,
                                                  @Param("title") String title);
}
