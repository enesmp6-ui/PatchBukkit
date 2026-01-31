use std::ffi::c_void;

#[unsafe(no_mangle)]
pub extern "C" fn rust_register_event(
    _listener_ptr: *const c_void, // opaque pointer to Java object
    _plugin_ptr: *const c_void,
) {
    // Handle event registration
}
