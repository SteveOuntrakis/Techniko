package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PropertyUserServiceTest {

    @Mock
    private Repository repository;

    @InjectMocks
    private PropertyOwnerService propertyUserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindOwnerByUsername() {
        String username = "Stevoun";
        PropertyOwner owner = new PropertyOwner();
        owner.setUsername(username);

        when(repository.findAllByUsername(username)).thenReturn(Arrays.asList(owner));

        Optional<PropertyOwner> result = propertyUserService.findOwnerByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
        verify(repository, times(1)).findAllByUsername(username);
    }

}
