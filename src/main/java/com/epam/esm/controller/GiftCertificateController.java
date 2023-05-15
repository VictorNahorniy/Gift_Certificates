package com.epam.esm.controller;

import com.epam.esm.dao.SortType;
import com.epam.esm.errorHandling.customException.Ceritficate.WrongSortParamException;
import com.epam.esm.errorHandling.customException.Tag.TagNotFoundException;
import com.epam.esm.models.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagGiftCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Controller for GiftCertificate
 * <p>
 * This class is responsible for handling requests related to GiftCertificate
 * and sending the response back to the client.
 * The controller is a REST controller that provides the possibility
 * of manipulation with GiftCertificate.
 * The controller returns data in JSON format.
 * The controller handles requests from /giftCertificates.
 * The controller has methods for:
 * <ul>
 *     <li>getting all GiftCertificates</li>
 *     <li>getting GiftCertificate by id</li>
 *     <li>getting GiftCertificate by name</li>
 *      <li>adding GiftCertificate</li>
 *      <li>updating GiftCertificate</li>
 *      <li>deleting GiftCertificate</li>
 *      <li>getting GiftCertificates by tag name</li>
 *      <li>getting GiftCertificates by part of name and description</li>
 *      <li>sorting GiftCertificates by name or date</li>
 *  </ul>
 *  </p>
 */
@RestController
@AllArgsConstructor
public class GiftCertificateController {
    @Autowired
    private GiftCertificateService giftCertificateService;
    @Autowired
    private TagGiftCertificateService tagGiftCertificateService;

    @GetMapping("/giftCertificates")
    public Iterable<GiftCertificate> getAllGiftCertificates() {
        return giftCertificateService.getAllGiftCertificates();
    }

    @GetMapping("/giftCertificates/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable Integer id) {
        GiftCertificate giftCertificate = giftCertificateService.getGiftCertificateById(id);
        if (giftCertificate == null) {
            throw new TagNotFoundException("Gift certificate with id " + id + " not found", 40402);
        }
        return giftCertificate;
    }

    @PostMapping("/giftCertificates")
    public void createGiftCertificate(@RequestBody GiftCertificate giftCertificate) {
        giftCertificateService.createGiftCertificate(giftCertificate);
    }

    @PutMapping("/giftCertificates")
    public void updateGiftCertificate(@RequestBody GiftCertificate giftCertificate) throws InvocationTargetException, IllegalAccessException {
        giftCertificateService.updateGiftCertificate(giftCertificate);
    }

    @DeleteMapping("/giftCertificates/{id}")
    public void deleteGiftCertificate(@PathVariable Integer id) {
        giftCertificateService.deleteGiftCertificate(id);
    }

    @GetMapping("/giftCertificates/byTagName/{tagName}")
    Iterable<GiftCertificate> getCertificatesByTagName(@PathVariable String tagName) {
        return tagGiftCertificateService.getCertificatesByTagName(tagName);
    }

    @GetMapping("/giftCertificates/byTemplate/{template}")
    Iterable<GiftCertificate> getCertificatesByPartOfNameOrDescription(@PathVariable String template) {
        return tagGiftCertificateService.getCertificatesByPartOfNameOrDescription(template);
    }

    @GetMapping("/giftCertificates/sort/{sortParam}/{sortType}")
    Iterable<GiftCertificate> sortCertificatesByDate(@PathVariable String sortParam, @PathVariable String sortType) {
        SortType sortTypeEnum = SortType.valueOf(sortType.toUpperCase());
        if (sortParam.equals("name")) {
            return tagGiftCertificateService.sortCertificatesByName(sortTypeEnum);
        } else if (sortParam.equals("date")) {
            return tagGiftCertificateService.sortCertificatesByDate(sortTypeEnum);
        }
        throw new WrongSortParamException("Wrong sort param", 40001);
    }
}
