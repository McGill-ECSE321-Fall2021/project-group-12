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
	
	@GetMapping(value = { "/creator/{creatorId}", "/creator/{creatorId}/" })
	public CreatorDto getCreator(@PathVariable("creatorId") Long creatorId) throws IllegalArgumentException {
		return convertToDto(service.getCreator(creatorId));
	}
	
	@GetMapping(value = { "/creator/items/{creatorId}", "/creator/items/{creatorId}/" })
	public List<ItemDto> getCreatorItems(@PathVariable("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = service.getCreator(creatorId);
		return itemDtosForCreator(creator);
	}
		
	
	@PostMapping(value = { "/creator/create", "/creator/create/" })
	public CreatorDto createCreator(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="creatorType") CreatorType creatorType) throws IllegalArgumentException {
		Creator creator = service.createCreator(firstName, lastName, creatorType);
		return convertToDto(creator);
	}
	
	@PutMapping(value = { "/creator/update/{creatorId}", "/creator/update/{creatorId}/" })
	public CreatorDto updateCreator(@PathVariable("creatorId") Long creatorId, @RequestParam(value="firstName") String newFirstName, @RequestParam(value="lastName") String newLastName, @RequestParam(value="creatorType") CreatorType newCreatorType) throws IllegalArgumentException {
		Creator creator = service.updateCreator(creatorId, newFirstName, newLastName, newCreatorType);
		return convertToDto(creator);
	}
	
	@DeleteMapping(value = { "/creator/delete/{creatorId}", "/creator/delete/{creatorId}/" })
	public CreatorDto deleteCreator(@PathVariable("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = service.getCreator(creatorId);
		CreatorDto creatorDto = convertToDto(creator);
		service.deleteCreator(creatorId);
		return creatorDto;
	}

	
	private CreatorDto convertToDto(Creator creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator does not exist.");
		}
		
		CreatorDto creatorDto = new CreatorDto(creator.getFirstName(), creator.getLastName(), creator.getCreatorType(), creator.getCreatorId() ,itemDtosForCreator(creator));
		return creatorDto;
	}
	
	private ItemDto convertToDto(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item does not exist.");
		}
		ItemDto itemDto = null; // To be updated when ItemDto gets updated
		return itemDto;
	}
	
	private List<ItemDto> itemDtosForCreator(Creator creator){
		List<Item> items = service.getItemsByCreator(creator.getCreatorId());
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if (items != null) {
			for (Item i:items) {
				itemDtos.add(convertToDto(i));
			}
		}
		return itemDtos;
	}
}
