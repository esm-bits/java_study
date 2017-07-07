package stream.lesson;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {

    static class Employee {
        // 所属する事業部
        int department;

        // 名前
        String name;

        // 年齢
        int age;

        public Employee(int department, String name, int age) {
            this.department = department;
            this.name = name;
            this.age = age;
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
    }

    @Test
    public void 社員を事業部ごとにグループ化し事業部内は年齢で昇順に() throws Exception {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(100, "谷田部 みね子", 18));
        employees.add(new Employee(100, "助川 時子", 19));
        employees.add(new Employee(200, "角谷 三男", 19));
        employees.add(new Employee(300, "永井 愛子", 42));
        employees.add(new Employee(300, "秋葉 幸子", 25));
        employees.add(new Employee(300, "夏井 優子", 21));

        Map<Integer, List<Employee>> grouped = employees.stream()
        		.sorted(Comparator.comparing(Employee::getAge)) 			// 年齢でソート
                .collect(Collectors.groupingBy(Employee::getDepartment))  	// 事業部でグルーピング
                ;

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
}
