
// 1. Variables and Mutability (Variables ko change karna)
// By default, variables immutable hote hain. Agar change karna hai toh 'mut' lagana padta hai.
fn variables_mutability() {
    let x = 5; // immutable
    // x = 6; // Error! x ko change nahi kar sakte
    let mut y = 10; // mutable
    y = 15; // ab y ko change kar sakte hain
    println!("y: {}", y);
}

// 2. Data Types (Alag alag type ke data)
// Rust me har variable ka type fix hota hai.
fn data_types() {
    let a: i32 = 10; // integer
    let b: f64 = 3.14; // floating point
    let c: bool = true; // boolean
    let d: char = 'A'; // character
    println!("a: {}, b: {}, c: {}, d: {}", a, b, c, d);
    // println!("a: {}, b: {}, c: {}, d: {}", a, b, c, d);
}

// 3. Functions (Kaam ko function me todna)
fn add(a: i32, b: i32) -> i32 {
    a + b // last line return hoti hai
}

// 4. Control Flow (if, else, match)
// Decision lene ke liye
fn control_flow(x: i32) {
    if x > 0 {
        println!("Positive number");
    } else if x < 0 {
        println!("Negative number");
    } else {
        println!("Zero");
    }

    // match (switch jaisa)
    match x {
        1 => println!("One"),
        2 => println!("Two"),
        _ => println!("Something else"),
    }
}

// 5. Ownership, Borrowing, aur Lifetimes (Sabse important concept!)
// Rust me memory ka dhyan rakhna padta hai. Ek value ka ek hi owner hota hai.
fn ownership() {
    let s1 = String::from("hello");
    let s2 = s1; // s1 ab valid nahi hai, ownership s2 ko mil gayi
    // println!("{}", s1); // Error! s1 ab use nahi kar sakte

    let s3 = String::from("world");
    let s4 = &s3; // borrow kar rahe hain, s3 abhi bhi valid hai
    println!("s3: {}, s4: {}", s3, s4);
}

// 6. Structs and Enums (Apna khud ka data type banana)
struct Person {
    name: String,
    age: u8,
}

enum Color {
    Red,
    Green,
    Blue,
}

fn structs_enums() {
    let p = Person { name: String::from("Amit"), age: 25 };
    println!("Name: {}, Age: {}", p.name, p.age);

    let c = Color::Red;
    match c {
        Color::Red => println!("Laal rang"),
        Color::Green => println!("Hara rang"),
        Color::Blue => println!("Neela rang"),
    }
}

// 7. Vectors and Arrays (List of items)
fn vectors_arrays() {
    let arr = [1, 2, 3, 4]; // fixed size array
    let mut vec = vec![10, 20, 30]; // dynamic size vector
    vec.push(40);
    println!("Array: {:?}, Vector: {:?}", arr, vec);
}

// 8. Loops (Repeat karna)
// for, while, loop
fn loops() {
    for i in 0..5 {
        println!("for loop: {}", i);
    }
    let mut n = 0;
    while n < 3 {
        println!("while loop: {}", n);
        n += 1;
    }
    let mut m = 0;
    loop {
        if m == 2 { break; }
        println!("infinite loop: {}", m);
        m += 1;
    }
}

// 9. Pattern Matching (match, if let)
// Alag alag pattern pe kaam karna
fn pattern_matching(x: Option<i32>) {
    match x {
        Some(val) => println!("Value: {}", val),
        None => println!("Kuch nahi mila"),
    }

    if let Some(val) = x {
        println!("if let: {}", val);
    }
}

// 10. Error Handling (Result, Option)
// Error ko handle karne ka tarika
fn error_handling() {
    let res: Result<i32, &str> = Ok(10);
    match res {
        Ok(val) => println!("Value: {}", val),
        Err(e) => println!("Error: {}", e),
    }
}

// 11. Traits (Interface jaisa)
// Common behaviour define karne ke liye
trait Animal {
    fn speak(&self);
}

struct Dog;
impl Animal for Dog {
    fn speak(&self) {
        println!("Bhow Bhow");
    }
}

fn traits_example() {
    let d = Dog;
    d.speak();
}

// 12. Generics (Har type ke liye kaam karne wala code)
fn generic_fn<T: std::fmt::Debug>(x: T) {
    println!("Value: {:?}", x);
}

// 13. Modules (Code ko alag alag file me todna)
// mod my_module; // file ka naam my_module.rs hona chahiye

// 14. Closures (Anonymous function, ek line me function)
fn closures() {
    let add = |a, b| a + b;
    println!("Closure add: {}", add(2, 3));
}

// 15. Iterators (List pe kaam karne ka tarika)
fn iterators() {
    let v = vec![1, 2, 3];
    for x in v.iter() {
        println!("Iterator: {}", x);
    }
}

// 16. Smart Pointers (Box, Rc, RefCell)
// Advanced memory management
fn smart_pointers() {
    let b = Box::new(5); // heap pe value
    println!("Box: {}", b);
}

// 17. Concurrency (Threads, async/await)
// Multi-threading ka support
fn concurrency() {
    use std::thread;
    let handle = thread::spawn(|| {
        println!("Dusre thread me kaam ho raha hai");
    });
    handle.join().unwrap();
}

// Main function sab kuch call karne ke liye
fn main() {
    variables_mutability();
    data_types();
    println!("Add: {}", add(2, 3));
    control_flow(2);
    ownership();
    structs_enums();
    vectors_arrays();
    loops();
    pattern_matching(Some(42));
    error_handling();
    traits_example();
    generic_fn("Hello");
    closures();
    iterators();
    smart_pointers();
    concurrency();
}


// 18. Macros (Code ko generate karne ka tarika)
// Macros se hum code ko repeat hone se bacha sakte hain ya boilerplate kam kar sakte hain.
// Rust me sabse common macro hai println! ya vec! jaise built-in macros.
// Apna khud ka macro banane ke liye macro_rules! ka use hota hai.

// Without macro: Agar hume bar bar same tarah ka code likhna ho
fn print_hello() {
    println!("Hello from function!");
}

fn print_sum_fn(a: i32, b: i32) {
    println!("Sum is: {}", a + b);
}

// With macro: Code ko repeat hone se bachane ke liye
macro_rules! say_hello {
    () => {
        println!("Hello from macro!");
    };
}

macro_rules! print_sum {
    ($a:expr, $b:expr) => {
        println!("Sum is: {}", $a + $b);
    };
}

fn macros_example() {
    // Without macro
    print_hello();
    print_sum_fn(10, 20);

    // With macro
    say_hello!(); // Macro call
    print_sum!(10, 20); // Macro call with arguments

    // Built-in macro ka example
    let v = vec![1, 2, 3]; // vec! macro se vector banta hai
    println!("Vector from macro: {:?}", v);
}

/*
Difference:
- Agar aapko ek hi tarah ka code bar bar likhna hai (jaise print_hello ya print_sum_fn), toh ya toh har bar function likho ya call karo.
- Macro se aap generic code likh sakte ho jo compile time pe expand ho jata hai, aur aapko bar bar code nahi likhna padta.
- Macros me aap code ko pattern ke hisab se likh sakte ho, jaise print_sum! macro me koi bhi do expression pass kar sakte hain.
*/

// 19. Enum (Alag-alag values ko ek type me rakhna)
// Enum ka use tab hota hai jab kisi cheez ke kuch fixed options ho. Jaise traffic light me sirf Red, Yellow, Green ho sakte hain.

enum TrafficLight {
    Red,
    Yellow,
    Green,
}

fn print_light(light: TrafficLight) {
    match light {
        TrafficLight::Red => println!("Stop!"),
        TrafficLight::Yellow => println!("Get Ready!"),
        TrafficLight::Green => println!("Go!"),
    }
}

// 20. Option & Result (Error ya value ho sakti hai ya nahi)
// Option ka use tab hota hai jab value ho bhi sakti hai ya nahi bhi (jaise search me result mile ya na mile).
// Result ka use error handling ke liye hota hai.

fn find_number(numbers: &[i32], target: i32) -> Option<usize> {
    for (i, &num) in numbers.iter().enumerate() {
        if num == target {
            return Some(i);
        }
    }
    None
}

fn divide(a: i32, b: i32) -> Result<i32, String> {
    if b == 0 {
        Err("Zero se divide nahi kar sakte".to_string())
    } else {
        Ok(a / b)
    }
}

// 21. Struct (Apna khud ka data type banana)
// Struct ka use tab hota hai jab aapko ek se zyada cheezein ek sath rakhni ho (jaise student ka naam, age, marks).

struct Student {
    name: String,
    age: u8,
    marks: u32,
}

fn print_student(student: &Student) {
    println!("Name: {}, Age: {}, Marks: {}", student.name, student.age, student.marks);
}

// 22. Pattern Matching (Alag-alag cases handle karna)
// match ka use alag-alag values ke liye alag code likhne me hota hai.

fn match_example(x: i32) {
    match x {
        1 => println!("One"),
        2 | 3 => println!("Two ya Three"),
        4..=10 => println!("Four se leke Ten ke beech"),
        _ => println!("Kuch aur"),
    }
}

// 23. Iterator (Ek ek karke cheezein process karna)
// Iterator ka use list, vector, array me se ek ek item nikalne ke liye hota hai.

fn iterator_example() {
    let nums = vec![10, 20, 30];
    for n in nums.iter() {
        println!("Number: {}", n);
    }
}

// 24. Ownership, Borrowing, Lifetimes (Memory ka dhyan rakhna)
// Rust me har value ka ek owner hota hai. Jab owner scope se bahar jata hai, value delete ho jati hai.
// Borrowing se aap kisi value ko bina uska owner badle use kar sakte ho (& reference).
// Lifetimes se aap batate ho ki reference kitni der tak valid hai.

fn ownership_example() {
    let s = String::from("Hello");
    let len = calculate_length(&s); // &s se borrow kiya, ownership nahi gayi
    println!("Length: {}", len);
}

fn calculate_length(s: &String) -> usize {
    s.len()
}

// 25. Closure (Function ko variable ki tarah use karna)
// Closure ek chota function hota hai jo dusre function me ya variable me store ho sakta hai.

fn closure_example() {
    let add = |a: i32, b: i32| a + b;
    println!("5 + 3 = {}", add(5, 3));
}
