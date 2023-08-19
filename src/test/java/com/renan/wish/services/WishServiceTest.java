package com.renan.wish.services;

import com.renan.wish.domain.Product;
import com.renan.wish.domain.Wish;
import com.renan.wish.repository.WishRepository;
import com.renan.wish.services.exception.BusinessErrorException;
import com.renan.wish.services.exception.ExceededLimitWishesException;
import com.renan.wish.services.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WishServiceTest {

    @Mock
    private WishRepository wishRepository;

    @InjectMocks
    private WishService wishService;


    private Wish wishTest;

    @BeforeEach
    public void setUp() throws Exception {

        wishTest = new Wish(
                "idTest",
                "idCustomerTest",
                new Product("idProductTest", "nameProductTest"));


    }

    @DisplayName("Test Given IdCustomer And IdProduct When FindByIdCustomerAndIdProduct Then Return Object Wish")
    @Test
    public void testGivenIdCustomerAndIdProduct_WhenFindByIdCustomerAndIdProduct_ThenReturnObjectWish() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.of(wishTest));

        Wish foundedWish = wishService.findByIdCustomerAndIdProduct(wishTest.getIdCustomer(), wishTest.getProduct().getId());

        assertNotNull(foundedWish);
        assertEquals("idTest", foundedWish.getId());
        assertEquals("idCustomerTest", foundedWish.getIdCustomer());
        assertEquals("idProductTest", foundedWish.getProduct().getId());
    }

    @DisplayName("Test Given Not Registered IdCustomer And IdProduct When FindByIdCustomerAndIdProduct Then Throws Exception")
    @Test
    public void testGivenNotRegisteredIdCustomerAndIdProduct_WhenFindByIdCustomerAndIdProduct_ThenThrowsException() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.empty());


        assertThrows(ObjectNotFoundException.class, () -> {
            wishService.findByIdCustomerAndIdProduct(wishTest.getIdCustomer(), wishTest.getProduct().getId());
        });
    }

    @DisplayName("Test Given IdCustomer when FindAllByIdCustomer then Return A WishList")
    @Test
    public void testGivenIdCustomer_whenFindAllByIdCustomer_thenReturnAWishList() {

        Wish wishTest1 = new Wish(
                "idTest1",
                "idCustomerTest",
                new Product("idProductTest1", "nameProductTest1"));

        given(wishRepository.findAllByIdCustomer(anyString())).willReturn(List.of(wishTest, wishTest1));

        List<Wish> wishList = wishService.findAllByIdCustomer(wishTest.getIdCustomer());

        assertNotNull(wishList);
        assertEquals(2, wishList.size());
    }

    @DisplayName("test Given Object Wish When AddNewWish then Return Object Wish")
    @Test
    public void testGivenObjectWish_WhenAddNewWish_thenReturnObjectWish() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.empty());
        given(wishRepository.countByIdCustomer(anyString())).willReturn(1L);
        given(wishRepository.insert(wishTest)).willReturn(wishTest);

        Wish addedWish = wishService.addNewWish(wishTest);

        assertNotNull(addedWish);
        assertEquals("idCustomerTest", addedWish.getIdCustomer());
        assertEquals("idProductTest", addedWish.getProduct().getId());
    }

    @DisplayName("test Given Registered Wish Product For Customer When AddNewWish then Throws Exception")
    @Test
    public void testGivenRegisteredWishProductForCustomer_WhenAddNewWish_thenThrowsException() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.of(wishTest));

        assertThrows(BusinessErrorException.class, () ->{
            wishService.addNewWish(wishTest);
        });

        verify(wishRepository, never()).insert(any(Wish.class));
    }

    @DisplayName("test Given Twenty First For Exceeded Limit Wishes When AddNewWish then Throws Exception")
    @Test
    public void testGivenTwentyFirstForExceededLimitWishes_WhenAddNewWish_thenThrowsException() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.empty());
        given(wishRepository.countByIdCustomer(anyString())).willReturn(20L);

        assertThrows(ExceededLimitWishesException.class, () ->{
            wishService.addNewWish(wishTest);
        });

        verify(wishRepository, never()).insert(any(Wish.class));
    }

    @DisplayName("test Given IdCustomer And ProductId When DeleteByIdCustomerAndProductId then Return Nothing")
    @Test
    public void testGivenIdCustomerAndProductId_WhenDeleteByIdCustomerAndProductId_thenReturnNothing() {

        given(wishRepository.findByIdCustomerAndProductId(anyString(), anyString())).willReturn(Optional.of(wishTest));
        willDoNothing().given(wishRepository).deleteById(wishTest.getId());

        wishService.deleteByIdCustomerAndProductId(wishTest.getIdCustomer(), wishTest.getProduct().getId());

        verify(wishRepository, times(1)).deleteById(wishTest.getId());

    }

}