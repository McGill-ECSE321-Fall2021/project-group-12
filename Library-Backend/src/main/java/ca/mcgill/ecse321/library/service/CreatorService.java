package ca.mcgill.ecse321.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.CreatorRepository;

import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;

@Service
public class CreatorService {
	
	@Autowired
	CreatorRepository creatorRepository;
	
	
	@Transactional
	public Creator createCreator(String firstName, String lastName, Creator.CreatorType creatorType) {
		Creator creator = new Creator();
		creator.setFirstName(firstName);
		creator.setLastName(lastName);
		creator.setCreatorType(creatorType);
		creatorRepository.save(creator);
		return creator;	
	}
	
	@Transactional 
	public Creator updateCreator(Long id, String newFirstName, String newLastName, Creator.CreatorType newCreatorType) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(id);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		creator.setFirstName(newFirstName);
		creator.setLastName(newLastName);
		creator.setCreatorType(newCreatorType);
		creatorRepository.save(creator);
		return creator;
	}
	
	@Transactional
	public Creator deleteCreator(Long id) throws IllegalArgumentException {
		Creator creator = creatorRepository.findCreatorByCreatorId(id);
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		creatorRepository.deleteById(id);
		return creator;
		
	}
	
	@Transactional
	public Creator getCreator(Long id) {
		Creator creator = creatorRepository.findCreatorByCreatorId(id);
		return creator;
	}
	
	@Transactional
	public List<Creator> getAllCreators(){
		return toList(creatorRepository.findAll());
	}
	
	@Transactional
	public List<Item> getItemsByCreator(Long id){
		Creator creator = creatorRepository.findCreatorByCreatorId(id);
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
