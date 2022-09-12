package com.example.demo.services.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserApiImpl implements UserApi{

    final static int ID = 0;
    final static int NAME = 1;
    final static int SURNAME = 2;
    final static int EMAIL = 3;
    final static int REGISTRATION_TIME = 4;
    final static int COUNTRY = 5;
    final static int IP_ADDRESS = 6;

    final static SimpleDateFormat REGISTRATION_DATE_FORMAT = new SimpleDateFormat("MM/DD/YYYY");
    final static int FIRST_EXCEL_SHEET_PAGE = 0;

    @Value("classpath:Data.xlsx")
    Resource resourceFile;

    List<UserStatsData> userStatsData;
    List<User> users;

    @PostConstruct
    public void init() throws IOException {
        this.users = generateUsers(resourceFile.getFile());
        this.userStatsData = generateUserData(users);
    }

    public List<UserStatsData> getUserStatsData() {
        return this.userStatsData;
    }

    public List<User> getUsers() {
        return this.users;
    }

    private static List<User> generateUsers(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return parseUsers(fileInputStream);
        } catch (Exception e) {
            return null;
        }
    }

    private static List<UserStatsData> generateUserData(List<User> users) {
        Map<String, Long> result = users.stream()
                .collect(Collectors.groupingBy(User::getCountry, TreeMap::new, Collectors.counting()));
        long totalOccurrences = result.values().stream().reduce(0L, Long::sum);
        return result.entrySet().stream().map(e -> UserStatsData.builder()
                .country(e.getKey())
                .occurance(e.getValue())
                .occuranceRate(e.getValue() / (float) totalOccurrences * 100)
                .build())
                .collect(Collectors.toList());
    }

    private static List<User> parseUsers(FileInputStream file) throws Exception {
        List<User> userItems = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(FIRST_EXCEL_SHEET_PAGE);

        for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);

            int userId = (int) row.getCell(ID).getNumericCellValue();
            String name = row.getCell(NAME).getStringCellValue();
            String surname = row.getCell(SURNAME).getStringCellValue();
            String email = row.getCell(EMAIL).getStringCellValue();
            Date registrationDate = REGISTRATION_DATE_FORMAT.parse(row.getCell(REGISTRATION_TIME).getStringCellValue());
            String country = row.getCell(COUNTRY).getStringCellValue();
            String ipAddress = row.getCell(IP_ADDRESS).getStringCellValue();

            userItems.add(User.builder().id(userId)
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .registrationDate(registrationDate)
                    .country(country)
                    .ipAddress(ipAddress)
                    .build());
        }
        return userItems;
    }

    @Getter
    @Setter
    @Builder
    public static class UserStatsData {
        String country;
        float occuranceRate;
        long occurance;
    }

    @Getter
    @Setter
    @Builder
    public static class User {
        int id;
        String name;
        String surname;
        String email;
        Date registrationDate;
        String country;
        String ipAddress;
    }

}
