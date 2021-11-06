package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ProductDao productDao;

    @Test
    void testInvoiceDaoSave() {
        //Given
        Item item1 = new Item(new BigDecimal("11.99"), 2, new BigDecimal("22.98"));
        Item item2 = new Item(new BigDecimal("5.66"), 5, new BigDecimal("28.30"));

        Product potatoe = new Product("Potatoe");
        Product cucumber = new Product("Cucumber");

        Invoice invoice = new Invoice("fv/10/10/2051");

        invoice.setItem(item1);
        invoice.setItem(item2);

        potatoe.setItem(item1);
        cucumber.setItem(item2);

   //     invoiceDao.save(invoice);
        productDao.save(potatoe);

        //When

        //Then

        //CleanUp
 //      invoiceDao.deleteAll();

    }
}