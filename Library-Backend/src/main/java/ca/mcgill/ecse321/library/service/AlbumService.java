package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.AlbumRepository;

import ca.mcgill.ecse321.library.model.Album;
import ca.mcgill.ecse321.library.model.Album.MusicGenre;


@Service
public class AlbumService {
	
	@Autowired
	AlbumRepository albumRepository;
	
	@Transactional
	public Album createAlbum(String title, boolean isArchive, boolean isReservable, Date releaseDate, int numSongs, boolean available, MusicGenre genre) throws IllegalArgumentException {
		if (title == null || releaseDate == null) {
			throw new IllegalArgumentException("Cannot create album with empty fields.");
		}
		if (title  == "") {
			throw new IllegalArgumentException("Cannot create album with empty title.");
		}
		boolean valid = false;
		for (int i=0; i<title.length();i++) {
			if (title.charAt(i) != ' ') {
				valid = true;
				break;
			}
		}
		
		if(numSongs == 0) {
			valid = false;
		}
		
		if (!valid) {
			throw new IllegalArgumentException("Cannot create album");
		}
		
		Album album = new Album();
		album.setTitle(title);
		album.setIsArchive(isArchive);
		album.setIsReservable(isReservable);
		album.setReleaseDate(releaseDate);
		album.setNumSongs(numSongs);
		album.setIsAvailable(available);
		album.setGenre(genre);
		albumRepository.save(album);
		return album;	
	}
	
	@Transactional
	public Album deleteAlbum(Long albumId) throws IllegalArgumentException {
		Album album = albumRepository.findAlbumByItemId(albumId);
		if (album == null) {
			throw new IllegalArgumentException("Album does not exist.");
		}
		albumRepository.delete(album);
		return album;
		
	}
	
	@Transactional
	public Album getAlbum(Long albumId) {
		Album album = albumRepository.findAlbumByItemId(albumId);
		if (album == null) {
			throw new IllegalArgumentException("Album does not exist.");
		}
		return album;
	}
	
	@Transactional
	public List<Album> getAllAlbums(){
		return toList(albumRepository.findAll());
	}
	
	public List<Album> toList(Iterable<Album> iterable){
		List<Album> albums = new ArrayList<Album>();
		for (Album a:iterable) {
			albums.add(a);
		}
		return albums;
	}

}
