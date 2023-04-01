package com.lg;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("JPA project");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate_JPA");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User u1 = new User(null, "test_1","test_1","Andrzej", "Kowalski", Sex.MALE);
        User u2 = new User(null, "test_2","test_2","Baśka", "Kowalska", Sex.FEMALE);
        User u3 = new User(null, "test_3","test_3","Karolina", "Kowal", Sex.FEMALE);
        User u4 = new User(null, "test_4","test_4","Aleksander", "Kowalski", Sex.MALE);
        User u5 = new User(null, "test_5","test_5","Leszek", "Kowal", Sex.MALE);
        User u6 = new User(null, "test_6","test_6","Ernest", "Kowalo", Sex.MALE);
        Role r1 = new Role(null, "pracownik A");
        Role r2 = new Role(null, "pracownik B");
        Role r3 = new Role(null, "pracownik C");
        Role r4 = new Role(null, "pracownik D");
        Role r5 = new Role(null, "pracownik E");
        UsersGroup gr1 = new UsersGroup();
        UsersGroup gr2 = new UsersGroup();
        u1.addGroup(gr1);
        u3.addGroup(gr1);
        u2.addGroup(gr2);
        u4.addGroup(gr2);
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);
        em.persist(u5);
        em.persist(u6);
        u6.addRole(r1);
        u6.addRole(r2);
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.persist(r5);

        User user = em.find(User.class, 1L);
        if (user != null) {
            user.setPassword("noweHaslo");
            em.merge(user);
        }

        Role role = em.find(Role.class, 5L);
        em.remove(role);

        Query query = em.createQuery("SELECT u FROM User u WHERE u.lastName = 'Kowalski'");
        List<User> kowalscy = query.getResultList();
        for(User i : kowalscy){
            System.out.printf("%s %s%n", i.getFirstName(), i.getLastName());
        }

        Query query2 = em.createQuery("SELECT u FROM User u WHERE u.sex = 'FEMALE'");
        List<User> baby = query2.getResultList();
        System.out.printf("Kobiety: ");
        for(User i : baby){
            System.out.printf("%s %s%n", i.getFirstName(), i.getLastName());
        }

        BufferedImage image = null;
        ByteArrayOutputStream obr = new ByteArrayOutputStream();
        try {
            image = ImageIO.read(new File("img.jpg"));
            ImageIO.write(image, "jpg", obr);
        } catch (IOException e) {
        }
        byte[] imageData = obr.toByteArray();
        imageData = obr.toByteArray();
        User u7 = new User(null, "test_8","test_6","Ernest", "Kawiński", Sex.MALE);
        u7.setImage(imageData);
        em.persist(u7);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
