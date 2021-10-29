package ca.mcgill.ecse321.library.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.dto.CreatorDto;
import ca.mcgill.ecse321.library.dto.ItemDto;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Creator.CreatorType;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.service.CreatorService;

@CrossOrigin(origins = "*")
@RestController
public class CreatorRestController {
	
	@Autowired
	private CreatorService service;
	
	@GetMapping(value = { "/creators", "/creators/" })
	public List<CreatorDto> getAllCreators(){
		return service.getAllCreators().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/creators/{creatorName}", "/creators/{creatorId}/" })
	public CreatorDto getCreator(@PathVariable("creatorName") String creatorName) throws IllegalArgumentException {
		return convertToDto(service.getCreator(creatorName));
	}
	
	@GetMapping(value = { "/creators/items/{creatorName}", "/creators/items/{creatorId}/" })
	public List<ItemDto> getCreatorItems(@PathVariable("creatorName") String creatorName) throws IllegalArgumentException {
		Creator creator = service.getCreator(creatorName);
		return itemDtosForCreator(creator);
	}
		
	
	@PostMapping(value = { "/creators/create", "/creators/create/" })
	public CreatorDto createCreator(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="creatorType") CreatorType creatorType) throws IllegalArgumentException {
		Creator creator = service.createCreator(firstName, firstName, creatorType);
		return convertToDto(creator);
	}
	
	@PutMapping(value = { "/creators/update", "/creators/update/" })
	public CreatorDto updateCreator(@RequestParam(value="oldFirstName") String oldFirstName, @RequestParam(value="oldLastName") String oldLastName, @RequestParam(value="oldCreatorType") CreatorType oldCreatorType, @RequestParam(value="newFirstName") String newFirstName, @RequestParam(value="newLastName") String newLastName, @RequestParam(value="newCreatorType") CreatorType newCreatorType) throws IllegalArgumentException {
		Creator creator = service.updateCreator(oldFirstName, oldLastName, oldCreatorType, newFirstName, newLastName, newCreatorType);
		return convertToDto(creator);
	}
	
	@DeleteMapping(value = { "/creators/delete/{creatorName}", "/creators/delete/{creatorId}/" })
	public CreatorDto deleteCreator(@PathVariable("creatorName") String creatorName) throws IllegalArgumentException {
		Creator creator = service.deleteCreator(creatorName);
		return convertToDto(creator);
	}

	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), itemDtosForCreator(creator));
		return creatorDto;
	}
	
	private ItemDto convertToDto(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item does not exist.");
		}
		ItemDto itemDto = new ItemDto(); // To be updated when ItemDto gets updated
		return itemDto;
	}
	
	private List<ItemDto> itemDtosForCreator(Creator creator){
		List<Item> items = service.getItemsByCreator(creator.getCreatorName());
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if (items != null) {
			for (Item i:items) {
				itemDtos.add(convertToDto(i));
			}
		}
		return itemDtos;
	}
}
