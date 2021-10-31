package ca.mcgill.ecse321.library.controller;

import java.sql.Date;
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
import ca.mcgill.ecse321.library.dto.NewspaperDto;
import ca.mcgill.ecse321.library.model.Creator;
import ca.mcgill.ecse321.library.model.Item;
import ca.mcgill.ecse321.library.model.Newspaper;
import ca.mcgill.ecse321.library.service.CreatorService;
import ca.mcgill.ecse321.library.service.NewspaperService;

@CrossOrigin(origins= "*")
@RestController
public class NewspaperRestController {

	@Autowired
	private NewspaperService newspaperService;
	
	@Autowired
	private CreatorService creatorService;
	
	@GetMapping(value = {"/newspapers", "/newspapers/"})
	public List<NewspaperDto> getAllNewspapers(){
		return newspaperService.getAllNewspapers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {"/newspaper/{itemId}", "/newspaper/{itemId}/" })
	public NewspaperDto getNewspaper(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(newspaperService.getNewspaper(itemId));
	}

	@GetMapping(value = {"/newspaper/creator/{itemId}", "newspaper/creator/{itemId}/"})
	public CreatorDto getNewspaperCreator(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		return convertToDto(newspaperService.getNewspaper(itemId)).getCreator();
	}
	
	@PostMapping(value = {"/newspaper/create", "/newspaper/create/"})
	public NewspaperDto createNewspaper(@RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(newspaperService.createNewspaper(title, isArchive, releaseDate, creator));
	}
	
	@PutMapping(value = {"/newspaper/update/{itemId}", "/newspaper/update/{itemId}/"})
	public NewspaperDto updateNewspaper(@PathVariable("itemId") Long itemId, @RequestParam("title") String title, @RequestParam("isArchive") boolean isArchive, @RequestParam("releaseDate") Date releaseDate, @RequestParam("creatorId") Long creatorId) throws IllegalArgumentException {
		Creator creator = creatorService.getCreator(creatorId);
		return convertToDto(newspaperService.updateNewspaper(itemId, title, isArchive, releaseDate, creator));
	}
	
	@DeleteMapping(value = {"/newspaper/delete/{itemId}", "/newspaper/delete/{itemId}/"})
	public NewspaperDto deleteNewspaper(@PathVariable("itemId") Long itemId) throws IllegalArgumentException {
		Newspaper newspaper = newspaperService.getNewspaper(itemId);
		NewspaperDto  newspaperDto = convertToDto(newspaper);
		newspaperService.deleteNewspaper(itemId);
		return newspaperDto;
	}
	
	public NewspaperDto convertToDto(Newspaper newspaper) {
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper does not exist.");
		}
		NewspaperDto newspaperDto = new NewspaperDto(newspaper.getTitle(), newspaper.getIsArchive(), newspaper.getReleaseDate(), convertToDto(newspaper.getCreator()));
		return newspaperDto;
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
		ItemDto itemDto = new ItemDto(); // To be updated when ItemDto gets updated
		return itemDto;
	}
	
	private List<ItemDto> itemDtosForCreator(Creator creator){
		List<Item> items = creatorService.getItemsByCreator(creator.getCreatorId());
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		if (items != null) {
			for (Item i:items) {
				itemDtos.add(convertToDto(i));
			}
		}
		return itemDtos;
	}
}
