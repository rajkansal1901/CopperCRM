package com.qa.copperCrm.dataProvider;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider
    public Object[][] addPersonData() {
        return new Object[][]{
                {"Stephen", "Martinez", "Mr", "Stephen Ltd", "ABC", "4544333", "4444 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Carl", "Marx", "Mr", "Carl Ltd", "ABC", "5434635", "5675 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Rohan", "Kumar", "Mr", "Rohan Ltd", "ABC", "454445433", "565 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"John", "Mid", "Mr", "John Ltd", "ABC", "6353543", "666 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
                {"Carry", "Anderson", "Mr", "Carry Ltd", "ABC", "6344", "322 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
        };
    }

    @DataProvider
    public Object[][] deletePeopleRecordData() {
        return new Object[][]{
                {"Stephen", "Martinez", "Mr", "Stephen Ltd", "ABC", "4544333", "4444 Avenue", "Montreal", "Quebec", "H3NNN4", "Canada", "New"},
        };
    }

    @DataProvider
    public Object[][] addTask() {
        return new Object[][]{
                {"Carl", "November 2026", "2"},
                {"Roshan", "June 2025", "24"}
        };
    }
}
