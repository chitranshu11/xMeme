package com.chitranshu.xmeme.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chitranshu.xmeme.exception.DuplicateMemeException;
import com.chitranshu.xmeme.json.MemeRequest;
import com.chitranshu.xmeme.json.MemeResponse;
import com.chitranshu.xmeme.json.PostMemeResponse;
import com.chitranshu.xmeme.model.Meme;
import com.chitranshu.xmeme.service.MemeService;

@CrossOrigin("https://xmeme-chitranshu.netlify.app")
@RestController
public class MemeController {

	@Autowired
	MemeService memeService;

	@GetMapping("/")
	public String entryPoint() {
		return "Chitranshu Gour";
	}

	@RequestMapping(value = "/memes", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<PostMemeResponse> addMeme(@RequestBody MemeRequest request) {

		PostMemeResponse response = new PostMemeResponse();
		try {
			Long id = memeService.addMeme(request.getName(), request.getCaption(), request.getUrl());
			response.setId(String.valueOf(id));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DuplicateMemeException e) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		} catch (Exception e) {
			// e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/memes", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<MemeResponse>> getMemes() {

		// GetMemeResponse response = new GetMemeResponse();
		try {

			List<Meme> memes = memeService.getLatestMemes();
			List<MemeResponse> response = new ArrayList<MemeResponse>();
			for (Meme meme : memes) {
				MemeResponse obj = new MemeResponse(String.valueOf(meme.getId()), meme.getName(), meme.getUrl(),
						meme.getCaption());
				response.add(obj);
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// response.setMemes(null);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/memes/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MemeResponse> getMeme(@PathVariable Long id) {

		try {
			Meme meme = memeService.getMemeById(id);

			MemeResponse response = new MemeResponse(String.valueOf(meme.getId()), meme.getName(), meme.getUrl(),
					meme.getCaption());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			MemeResponse response = new MemeResponse();
			response.setStatus("false");
			response.setMessage(e.getLocalizedMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/memes/{id}", method = RequestMethod.PATCH, produces = "application/json")
	public ResponseEntity<MemeResponse> updateMeme(@RequestBody MemeRequest resquest, @PathVariable Long id) {

		try {

			memeService.updateMeme(resquest.getUrl(), resquest.getCaption(), id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
