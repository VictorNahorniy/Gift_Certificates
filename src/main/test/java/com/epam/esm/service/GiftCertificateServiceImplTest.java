package com.epam.esm.service;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.errorHandling.customException.Ceritficate.CertificateNotFoundException;
import com.epam.esm.errorHandling.customException.Ceritficate.DuplicateCertificateException;
import com.epam.esm.models.GiftCertificate;
import com.epam.esm.service.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceImplTest {
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @InjectMocks
    private GiftCertificateServiceImpl giftCertificateService;

    private GiftCertificate newCertificate;

    @BeforeEach
    public void setUp() {
        newCertificate = new GiftCertificate();
        newCertificate.setName("Test Certificate");
        newCertificate.setDescription("This is a test certificate");
        newCertificate.setPrice(50.00);
        newCertificate.setDuration(7);
    }

    @Test
    @DisplayName("Test createGiftCertificate() method when a new certificate is added successfully")
    public void testCreateGiftCertificateWithNewCertificate() {
        when(giftCertificateDao.findByName(any(String.class))).thenReturn(Optional.empty());
        doNothing().when(giftCertificateDao).save(newCertificate);

        // when
        giftCertificateService.createGiftCertificate(newCertificate);

        // then
        verify(giftCertificateDao, times(1)).findByName(any(String.class));
        verify(giftCertificateDao, times(1)).save(newCertificate);
    }

    @Test
    @DisplayName("Test createGiftCertificate() method when a new certificate is not added because it already exists")
    public void testCreateGiftCertificateWithExistingCertificate() {
        // when
        when(giftCertificateDao.findByName(any(String.class))).thenReturn(Optional.of(newCertificate));
        // then
        Assertions.assertThrows(DuplicateCertificateException.class, () ->
                giftCertificateService.createGiftCertificate(newCertificate));
        verify(giftCertificateDao, times(1)).findByName(any(String.class));
        verify(giftCertificateDao, times(0)).save(newCertificate);
    }

    @Test
    @DisplayName("Test updateGiftCertificate() method when a certificate is updated successfully")
    public void testUpdateGiftCertificateWithExistingCertificate() throws InvocationTargetException, IllegalAccessException {
        // given
        GiftCertificate existingCertificate = new GiftCertificate();
        existingCertificate.setId(1);
        existingCertificate.setName("Existing Certificate");
        existingCertificate.setDescription("This is an existing certificate");
        existingCertificate.setPrice(50.00);
        existingCertificate.setDuration(7);

        when(giftCertificateDao.findById(any(Integer.class))).thenReturn(Optional.of(existingCertificate));
        when(giftCertificateDao.update(any(GiftCertificate.class), any(List.class))).thenReturn(existingCertificate);

        // when
        giftCertificateService.updateGiftCertificate(existingCertificate);
        // then
        verify(giftCertificateDao, times(1)).findById(any(Integer.class));
        verify(giftCertificateDao, times(1)).update(any(GiftCertificate.class), any(List.class));
    }

    @Test
    @DisplayName("Test updateGiftCertificate() method when a certificate is not updated because it does not exist")
    public void testUpdateGiftCertificateWhenCertificateDoesNotExist() {
        GiftCertificate testGiftCertificate = new GiftCertificate();
        testGiftCertificate.setId(100);
        testGiftCertificate.setDuration(113);

        when(giftCertificateDao.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(CertificateNotFoundException.class, () -> {
            giftCertificateService.updateGiftCertificate(testGiftCertificate);
        });
    }

    @Test
    @DisplayName("Test deleteGiftCertificate() method when a certificate is deleted successfully")
    public void testDeleteGiftCertificate() {
        // given
        GiftCertificate existingCertificate = new GiftCertificate();
        existingCertificate.setId(1);

        when(giftCertificateDao.findById(any(Integer.class))).thenReturn(Optional.of(existingCertificate));
        doNothing().when(giftCertificateDao).delete(existingCertificate.getId());

        // when
        giftCertificateService.deleteGiftCertificate(existingCertificate.getId());

        // then
        verify(giftCertificateDao, times(1)).findById(any(Integer.class));
        verify(giftCertificateDao, times(1)).delete(existingCertificate.getId());
    }
}

