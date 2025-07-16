package com.postgresql.demo1.controller;


import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController@RequestMapping
public class OperatorsController {


//    arithmetic Operators
@GetMapping("/arithmetic/")
    public Map<String,Integer> arithmetic (@RequestParam int a, @RequestParam int b ){
        Map<String,Integer> result=new HashMap<>();

        result.put("Add",a+b);
        result.put("Sub",a-b);
        result.put("multiply",a*b);
        result.put("div", b !=0 ?a/b:0);
        result.put("Modulus",b!=0? a%b:0);
        return result;
    }
//    relational operators
    @GetMapping("/relational/{a},{b}")
    public Map<String,Boolean> relational(@PathVariable int a ,@PathVariable int b){
    Map<String,Boolean> result=new HashMap<>();
    result.put("Equal",a==b);
    result.put("not equal",a!=b);
    result.put("greater than",a>b);
    result.put("less than",a<b);
    result.put("greater than equal",a>=  b);
    result.put("less than or equal",a<= b);
    return result;
    }
//    Logical operators
    @GetMapping("/logical/{a}/{b}")
    public Map<String,Boolean> logial(@PathVariable boolean x,@PathVariable boolean y){
    Map<String,Boolean> result=new HashMap<>();
    result.put("AND",x&&y);
    result.put("OR",x || y);
    result.put("NOT X",!x);
    result.put("NOT Y",!y);
    return  result;

    }
//    Bitwise operators
    @GetMapping("/bitwise/{a}/{b}")
    public Map<String ,Integer> bitwise(@PathVariable int a ,@PathVariable int b ) {
        Map<String, Integer> result = new HashMap<>();
        result.put("AND ", a & b);
        result.put("OR ", a | b);
        result.put("XOR", a ^ b);
        result.put("Left shift (a << b)", a << 1);
        result.put("Right Shift (a >> 1)", a >> 1);
        result.put("Complement(~a)", ~a);
        return result;
    }
//    Assignment operators
    @GetMapping("/assignment/{a}")
public Map<String, Integer> assignment(@PathVariable int a) {
    Map<String, Integer> result = new HashMap<>();
    int x = a;
    x += 2; result.put("x += 2", x);
    x -= 1; result.put("x -= 1", x);
    x *= 3; result.put("x *= 3", x);
    x /= 2; result.put("x /= 2", x);
    x %= 5; result.put("x %= 5", x);
    return result;
}
// Unary operators
public Map<String, Integer> unary(@PathVariable int a) {
    Map<String, Integer> result = new HashMap<>();
    int preInc = ++a;
    int preDec = --a;
    int postInc = a++;
    int postDec = a--;
    result.put("Pre Increment", preInc);
    result.put("Pre Decrement", preDec);
    result.put("Post Increment", postInc);
    result.put("Post Decrement", postDec);
    return result;
}
//ternary
@GetMapping("/ternary/{a}/{b}")
public String ternary(@PathVariable int a, @PathVariable int b) {
    return (a > b) ? "A is greater" : (a == b) ? "Both are equal" : "B is greater";

}


}

