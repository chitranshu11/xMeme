package com.chitranshu.xmeme.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chitranshu.xmeme.exception.DuplicateMemeException;
import com.chitranshu.xmeme.exception.MemeNotFoundException;
import com.chitranshu.xmeme.model.Meme;
import com.chitranshu.xmeme.repository.MemeRepository;

@Service
public class MemeService {

	@Autowired
	MemeRepository memeRepository;

	public long addMeme(String name, String caption, String url) throws DuplicateMemeException {

		Meme tempMeme = memeRepository.findByNameAndUrlAndCaption(name, url, caption);
		if (tempMeme != null)
			throw new DuplicateMemeException("Meme already exit with same payloads");

		Meme meme = new Meme();
		meme.setName(name);
		meme.setCaption(caption);
		meme.setUrl(url);
		meme.setDate(new Timestamp(System.currentTimeMillis()));
		meme = memeRepository.saveAndFlush(meme);

		return meme.getId();
	}

	public List<Meme> getLatestMemes() {

		// To get latest 100 entries from database
		Page<Meme> page = memeRepository.findAll(PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "id")));

		List<Meme> memes = page.getContent();

		return memes;

	}

	public Meme getMemeById(Long id) throws MemeNotFoundException {
		Meme meme = memeRepository.findMemeById(id);
		if (meme == null)
			throw new MemeNotFoundException("Meme with id: " + id + " doesn't exist");

		return meme;
	}

	public void updateMeme(String url, String caption, Long id) throws MemeNotFoundException {
		Meme meme = memeRepository.findMemeById(id);
		if (meme == null)
			throw new MemeNotFoundException("Meme with id: " + id + " doesn't exist");

		if (url != null)
			meme.setUrl(url);
		if (caption != null)
			meme.setCaption(caption);

		memeRepository.saveAndFlush(meme);
	}
}
