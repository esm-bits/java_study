package stream.lesson;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import org.junit.Test;

import stream.lesson.StreamTest.Employee.Sex;

public class StreamTest {

    static class Employee {
        enum Sex {
            MALE, FEMALE
        }

        // 所属する事業部
        int department;

        // 名前
        String name;

        // 年齢
        int age;

        // 性別
        Sex sex;

        public Employee(int department, String name, int age, Sex sex) {
            this.department = department;
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public int getDepartment() {
            return department;
        }

        public void setDepartment(int department) {
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Sex getSex() {
            return sex;
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }
    }

    @Test
    public void 社員を事業部ごとにグループ化し事業部内は年齢で昇順に() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "谷田部 みね子", 18, Sex.FEMALE));
        employees.add(new Employee(100, "助川 時子", 19, Sex.FEMALE));
        employees.add(new Employee(200, "角谷 三男", 19, Sex.MALE));
        employees.add(new Employee(300, "永井 愛子", 42, Sex.FEMALE));
        employees.add(new Employee(300, "秋葉 幸子", 25, Sex.FEMALE));
        employees.add(new Employee(300, "夏井 優子", 21, Sex.FEMALE));

        Map<Integer, List<Employee>> grouped = employees.stream()
        		.sorted(comparing(Employee::getAge))
        		.collect(groupingBy(Employee::getDepartment));

        assertThat(grouped.size(), is(3));
        assertThat(grouped, hasKey(100));
        assertThat(grouped.get(100), hasSize(2));
        assertThat(grouped.get(100).get(0).name, is("谷田部 みね子"));
        assertThat(grouped.get(100).get(1).name, is("助川 時子"));
        assertThat(grouped.get(200), hasSize(1));
        assertThat(grouped.get(200).get(0).name, is("角谷 三男"));
        assertThat(grouped.get(300), hasSize(3));
        assertThat(grouped.get(300).get(0).name, is("夏井 優子"));
        assertThat(grouped.get(300).get(1).name, is("秋葉 幸子"));
        assertThat(grouped.get(300).get(2).name, is("永井 愛子"));
    }

    @Test
    public void 男性社員と女性社員の平均年齢を算出() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "Ken Tokugawa", 20, Sex.MALE));
        employees.add(new Employee(100, "Yumi Akita", 25, Sex.FEMALE));
        employees.add(new Employee(200, "Takashi Masuda", 30, Sex.MALE));
        employees.add(new Employee(300, "Masao Yoshino", 34, Sex.MALE));
        employees.add(new Employee(300, "Ryoko Hanamura", 40, Sex.FEMALE));
        employees.add(new Employee(300, "Miki Ichikawa", 43, Sex.FEMALE));

        Map<Sex, Double> averages = employees.stream()
        		.collect(groupingBy(Employee::getSex, averagingInt(Employee::getAge)));                  
                ;
        assertThat(averages.size(), is(2));
        assertThat(averages, hasKey(Sex.MALE));
        assertThat(averages.get(Sex.MALE), is(28.0));
        assertThat(averages.get(Sex.FEMALE), is(36.0));
    }
}
