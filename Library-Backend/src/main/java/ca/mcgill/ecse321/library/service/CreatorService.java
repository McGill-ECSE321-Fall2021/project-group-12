package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.CreatorRepository;

import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Item;

@Service
public class CreatorService {
	
	@Autowired
	CreatorRepository creatorRepository;
	
	
	@Transactional
	public Creator createCreator(String firstName, String lastName, CreatorType creatorType) throws IllegalArgumentException {
		if (firstName == null || lastName == null || creatorType == null) {
			throw new IllegalArgumentException("Cannot create creator with empty name or type.");
		}
		if (firstName.length() == 0 || lastName.length() == 0) {
			throw new IllegalArgumentException("Cannot create creator with empty name.");
		}
		if (firstName.trim().length() == 0 || lastName.length() == 0) {
			throw new IllegalArgumentException("Cannot create creator with empty name.");
		}
		Creator creator = new Creator();
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(creatorType);
		creatorRepository.save(creator);
		return creator;	
	}
	
	@Transactional 
	public Creator updateCreator(Long creatorId, String firstName, String lastName, Creator.CreatorType creatorType) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		if (firstName == null || lastName == null || creatorType == null) {
			throw new IllegalArgumentException("Cannot create creator with empty name or type.");
		}
		if (firstName.length() == 0 || lastName.length() == 0) {
			throw new IllegalArgumentException("Cannot create creator with empty name.");
		}
		if (firstName.trim().length() == 0 || lastName.length() == 0) {
			throw new IllegalArgumentException("Cannot create creator with empty name.");
		}
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(creatorType);
		creatorRepository.save(creator);
		return creator;
	}
	
	@Transactional
	public Creator deleteCreator(Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		creatorRepository.delete(creator);
		return creator;
		
	}
	
	
	@Transactional
	public Creator getCreator(Long creatorId) {
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		return creator;
	}
	
	@Transactional
	public List<Creator> getAllCreators(){
		return toList(creatorRepository.findAll());
	}
	
	
	@Transactional
	public List<Item> getItemsByCreator(Long creatorId){
		Creator creator = creatorRepository.findCreatorByCreatorId(creatorId);
		if (creator.getItems() != null) {
			return creator.getItems();
		} else {
			return new ArrayList<Item>(); // We do not want to return null, so return an empty ArrayList
		}
	}
	
	public List<Creator> toList(Iterable<Creator> iterable){
		List<Creator> creators = new ArrayList<Creator>();
		for (Creator c:iterable) {
			creators.add(c);
		}
		return creators;
	}
	
}