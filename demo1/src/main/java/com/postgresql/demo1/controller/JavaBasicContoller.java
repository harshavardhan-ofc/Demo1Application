package com.postgresql.demo1.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class JavaBasicContoller {
    private static final Logger logger = LogManager.getLogger(JavaBasicContoller.class);
    int x = 10;


    //System.out.println(x);


    @GetMapping("/getDatatypes")
    public String showVariables() {
        int number = 10;

//        String name="Java";(non primitive )
        float small = 12.22f;
        double decimal = 110.3;
        char letter = 'A';
        boolean bool = true;
        Boolean bool1 = true;
        String abc = "123";


        x = 11;
        Integer a = Integer.parseInt(abc); //a = 123
        a = 1; //a=1
        int b = 0;
        System.out.println(x);
        return "Integer :" + number + "float:" + small + "double :" + decimal + "char :" + letter + "boolean :" + bool;

    }


    //========================================================
//    (if else (conditional  statements ))
    @GetMapping("/getConditional/{value}")
    public String checkCondition(@PathVariable int value) {
        if (value > 0) {
            return "Positive number";
        } else if (value < 0) {
            return "Negative number";
        } else {
            return "Zero";
        }
    }

    //    ===================================================================
//    ( for loop)
    @GetMapping("/getLoop/{count}")
    public String loopExample(@PathVariable int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            sb.append(i).append(" ");
        }
        return sb.toString();
    }

    //============================================
    @GetMapping("/getArray")
    public String showArray() {
        int[] arr = {10, 20, 30};
        StringBuilder sb = new StringBuilder("Array: ");
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }

    //    =======================================================================================================
// method overloading
    @GetMapping("/addNumbers")
    public String addNumbers() {
        return "Sum (2+3): " + add(2, 3) + ", Sum (2+3+4): " + add(2, 3, 4);
    }

    private int add(int a, int b) {
        return a + b;
    }

    private int add(int a, int b, int c) {
        return a + b + c;
    }

    //    ============================================================================
//    (Using switch )
    @GetMapping("/day/{dayNumber}")
    public String getDayName(@PathVariable int dayNumber) {
        String day;
        switch (dayNumber) {
            case 1 -> day = "Monday";
            case 2 -> day = "Tuesday";
            case 3 -> day = "Wednesday";
            case 4 -> day = "Thursday";
            case 5 -> day = "Friday";
            case 6 -> day = "Saturday";
            case 7 -> day = "Sunday";
            default -> day = "Invalid day number! Must be between 1 and 7.";
        }

        return "Day: " + day;
    }
//    ============================================================================================
    // Animal.main


//    public class Animal {
//        public String makeSound() {
//            return "Some generic animal sound";
//        }
//    }
//    dog
// method overriding
//    public class Dog extends Animal {
//        @Override
//        public String makeSound() {
//            return "Woof!";
//        }
//    }

    //    ======================
//    (while loop )
    @GetMapping("/sum/{n}")
    public String sumUsingWhile(@PathVariable int n) {
        int i = 1;
        int sum = 0;

        while (i <= n) {
            sum += i;
            i++;
        }

        return "Sum of first " + n + " natural numbers is: " + sum;
    }

    //=======================================================================
// date and time
    @RestController
    @RequestMapping("/time")
    public static class TimeController {

        @GetMapping("/now")
        public String getCurrentTime() {
            LocalDateTime now = LocalDateTime.now();
            return "Current Date & Time: " + now.toString();
        }

        @GetMapping("/formatted")
        public String getFormattedTime() {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            return "Formatted: " + now.format(format);
        }

        //    ================================================
//    i/o stream api
        @RestController
        @RequestMapping("/file")
        public class FileController {

            private static final String FILE_PATH = "demo.txt";

            // Write to file
            @PostMapping("/write")
            public String writeToFile(@RequestParam String data) {
                try (FileWriter writer = new FileWriter(FILE_PATH)) {
                    writer.write(data);
                    return "Data written to file successfully!";
                } catch (IOException e) {
                    return "Error writing file: " + e.getMessage();
                }
            }

            //file/write?data=Hello amar , you rock!
            // Read from a file
            @GetMapping("/read")
            public String readFromFile() {
                try (FileReader reader = new FileReader(FILE_PATH)) {
                    StringBuilder content = new StringBuilder();
                    int ch;
                    while ((ch = reader.read()) != -1) {
                        content.append((char) ch);
                    }
                    return "File Content: " + content;
                } catch (IOException e) {
                    return "Error reading file: " + e.getMessage();
                }
            }

            //==============================================================
            // stream api
            @RestController
            @RequestMapping("/stream")
            public static class StreamController {

                @PostMapping("/process")
                public List<Integer> processNumbers(@RequestBody List<Integer> numbers) {
                    return numbers.stream()
                            .filter(n -> n % 2 == 0)
                            .map(n -> n * n)
                            .collect(Collectors.toList());
//                 it will collect and return as a list
                }
            }

            // =================================================
//public static class UnderAgeException extends RuntimeException {
//    public UnderAgeException(String message) {
//        super(message);
//    }
//}
//
//        //        throw and throws
//@RestController
//@RequestMapping("/check")
//public static class ExceptionDemoController {
//
//    @GetMapping("/age/{age}")
//    public String checkAge(@PathVariable int age) throws UnderAgeException {
//        if (age < 18) {
//            throw new UnderAgeException("You must be 18+ to access this.");
//        }
//        return "You are eligible!";
//    }
//}
//==========================================================================
// wrapper classes
            @RestController
            @RequestMapping("/wrapper")
            public static class WrapperDemoController {

                @GetMapping("/all")
                public String demoWrappers() {
                    // Integer
                    String intStr = "100";
                    Integer intValue = Integer.parseInt(intStr); // String to int Object

                    // Double
                    String doubleStr = "3.14159";
                    Double doubleValue = Double.parseDouble(doubleStr); // String to double

                    // Character
                    char letter = 'A';
                    Character charValue = letter; // auto-boxing to Character

                    // Boolean
                    String boolStr = "true";
                    Boolean boolValue = Boolean.parseBoolean(boolStr); // String to boolean

                    return "Integer: " + intValue +
                            ", Double: " + doubleValue +
                            ", Character: " + charValue +
                            ", Boolean: " + boolValue;
                }
            }

            //        varargs ==============================
            @RestController
            @RequestMapping("/varargs")
            public class VarargsController {

                @GetMapping("/sum")
                public String sumNumbers(@RequestParam int[] values) {
                    return "Sum: " + add(values);
                }

                // Varargs method
                public int add(int... nums) {
                    int sum = 0;
                    for (int n : nums) {
                        sum += n;
                    }
                    return sum;
                }
            }
//========================================================
//    exception handling

            @RestController
            @RequestMapping("/custom")
            public class AgeCheckController {

                @GetMapping("/checkAge/{age}")
                public String checkAge(@PathVariable int age) {
                    if (age < 18) {
                        String x = "abc";
                        String y = new String("abc");
//                x==y
//                        x===y
//                                x.equals(y)
//                                        x.hashCode() y.hashCode()

                        throw new UnderAgeException("You must be 18+ to access this!");
                    }
                    return "You're eligible!";
                }
            }

//    public class UnderAgeException extends RuntimeException {
//        public UnderAgeException(String message) {
//            super(message);
//        }
//    }


        }


    }


}

