package com.hadwin;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hadwin.controllers.ShipwreckController;
import com.hadwin.models.Shipwreck;
import com.hadwin.repositories.ShipwreckRepository;

public class ShipwreckControllerTest {
	
	@InjectMocks
	private ShipwreckController sc;
	
	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testShipwreckGet() {
		Shipwreck sw = new Shipwreck();
		sw.setId(1L);
		when(shipwreckRepository.findById(1L)).thenReturn(Optional.of(sw));
		
		// ShipwreckController sc = new ShipwreckController();
		Optional<Shipwreck> wreck = sc.get(1L);
		
		verify(shipwreckRepository).findById(1L);
		
		assertEquals(1L, wreck.get().getId().longValue());
	}

}
