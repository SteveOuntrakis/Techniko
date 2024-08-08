package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;

import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PropertyUserServiceTest {

    private Repository repository =Mockito.mock(Repository.class);

    private PropertyOwnerService propertyUserService = new PropertyOwnerService(repository);

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }

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
