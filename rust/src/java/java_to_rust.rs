use j4rs::prelude::*;
use j4rs_derive::*;

#[call_from_java("io.github.astonbitecode.j4rs.example.RustSimpleFunctionCall.fnnoargs")]
fn my_function_with_no_args() {
    println!("Hello from the Rust world!");
    // If you need to have a Jvm here, you need to attach the thread
    let _jvm = Jvm::attach_thread().unwrap();
    // Now you may further call Java classes and methods as usual!
}
