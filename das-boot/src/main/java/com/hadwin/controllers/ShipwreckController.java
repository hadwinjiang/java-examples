package com.hadwin.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hadwin.models.Shipwreck;
import com.hadwin.repositories.ShipwreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

	@Autowired
	private ShipwreckRepository shipwreckRepository;

	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> list() {
		// return ShipwreckStub.list();
		return shipwreckRepository.findAll();
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		// return ShipwreckStub.create(shipwreck);
		return shipwreckRepository.saveAndFlush(shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Optional<Shipwreck> get(@PathVariable Long id) {
		// return ShipwreckStub.get(id);
		return shipwreckRepository.findById(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Optional<Shipwreck> update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		// return ShipwreckStub.update(id, shipwreck);
		Optional<Shipwreck> optionalShipwreck = shipwreckRepository.findById(id);
		if (optionalShipwreck.isPresent()) {
			Shipwreck existingShipwreck = optionalShipwreck.get();
			BeanUtils.copyProperties(shipwreck, existingShipwreck);
			return Optional.of(shipwreckRepository.saveAndFlush(existingShipwreck));
		} else {
			return Optional.empty();
		}
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Optional<Object> delete(@PathVariable Long id) {
		// return ShipwreckStub.delete(id);
		Optional<Shipwreck> optionalShipwreck = shipwreckRepository.findById(id);
		if (optionalShipwreck.isPresent()) {
			Shipwreck existingShipwreck = optionalShipwreck.get();
			shipwreckRepository.delete(existingShipwreck);
			return Optional.of(existingShipwreck);
		} else {
			return Optional.empty();
		}
	}

}
