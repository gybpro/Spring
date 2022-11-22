package com.high.spring.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 人类
 *
 * @author high
 * @version 1.0
 * @since 1.0
 */
public class Person {
    private String[] favouriteFoods;

    private List<String> name;

    private Set<String> phone;

    private Map<String, String> address;

    public Person() {
    }

    public Person(String[] favouriteFoods, List<String> name, Set<String> phone, Map<String, String> address) {
        this.favouriteFoods = favouriteFoods;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "favouriteFoods=" + Arrays.toString(favouriteFoods) +
                ", name=" + name +
                ", phone=" + phone +
                ", address=" + address +
                '}';
    }

    public String[] getFavouriteFoods() {
        return favouriteFoods;
    }

    public void setFavouriteFoods(String[] favouriteFoods) {
        this.favouriteFoods = favouriteFoods;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }

    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }
}
