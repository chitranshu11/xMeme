package com.chitranshu.xmeme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chitranshu.xmeme.model.Meme;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long> {

	@Query("select e from Meme e where e.id = :id")
	public Meme findMemeById(Long id);

	public Meme findByNameAndUrlAndCaption(String name, String url, String caption);

}
