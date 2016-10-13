/**
 * Created by lWeRl on 12.10.2016.
 */
class HomeWork4 {
    public static void main(String[] args) {
        Person[] array = new Person[5];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Person("name" + i, "job" + i, "email" + i, "phone" + i, i * 10000, 38 + i);
        }
        for (Person p : array) {
            if (p.age >= 40) System.out.println(p.toString());
        }
    }
}

class Person {
    String name;
    String job;
    String email;
    String phone;
    int salary;
    int age;

    Person(String name, String job, String email, String phone, int salary, int age) {
        this.name = name;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    @Override
    public String toString(){
        return "Name:"+name+" Job:"+job+" Email:"+email+" Phone:"+phone+" Salary:"+salary+" Age:"+age;
    }
}